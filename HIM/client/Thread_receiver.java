package com.HIM.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.*;

import com.HIM.common.Bean_message;
import com.HIM.common.Bean_picture;
import com.HIM.common.logger;

import javax.swing.*;

public class Thread_receiver implements Runnable {
	private Socket socket = null;
	//输入输出流
	private InputStream is = null;
	private OutputStream os = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	//private FileOutputStream fos = null;
	//private FileInputStream fis = null;
	//工作与否
	private boolean working = true;
	private String serverIP;
	private int serverPort;
	private int InternetPort;


	public Thread_receiver(String serverIP, int serverPort) {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		this.Init();
	}

	private void Init()//使用前必须要初始化
	{
		try {
			socket = new Socket(serverIP, serverPort);
			//服务器满员退出时间
			socket.setSoTimeout(3000);
			//服务端和客户端的流建立要逆序，比如服务端建立i,客户端建立o
			os = socket.getOutputStream();
			is = socket.getInputStream();
			dos = new DataOutputStream(os);
			dis = new DataInputStream(is);
			oos = new ObjectOutputStream(os);
			ois = new ObjectInputStream(is);
			//双端验证
			this.Authenticate();
			//读入外网端口
			dos.writeUTF("getInternerPort");
			this.InternetPort = dis.readInt();
			logger.writelog("ClientReceiver", "连接服务器成功！");
			socket.setSoTimeout(0);
		} catch (UnknownHostException e) {
			logger.writelog("ClientReceiver", "连接服务器失败！");
		} catch (IOException e) {
			logger.writelog("ClientReceiver", "获取io失败！");
		}
	}

	private void Authenticate() throws IOException {
		dos.writeUTF("HIM");
	}

	public final int getInternetPort() {
		return InternetPort;
	}

	public void close() {
		try {
			if (!socket.isClosed()) dos.writeUTF("end");
			if (os != null) os.close();
			if (is != null) is.close();
			if (dos != null) dos.close();
			if (dis != null) dis.close();
			if (oos != null) oos.close();
			if (ois != null) ois.close();
			if (socket != null) socket.close();
			logger.writelog("ClientReceiver", "断开连接成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void run() {
		Object message = null;
		try {
			while (working) {
				System.out.println("接受信息中...");
				message = ois.readObject();
				if (message != null && message instanceof Bean_message) {
					handleMessage((Bean_message) message);
				}
			}
		} catch (IOException e) {
			logger.writelog("ClientReceiver", e.getMessage());
		} catch (Exception e) {
			logger.writelog("ClientReceiver", e.getMessage());
		} finally {
			close();
		}
	}

	public Socket getSocket() {
		return this.socket;
	}


	public void handleMessage(Bean_message message) {
		//待实现
//		fileTransfer.refuseFile(message);
		try {
			System.out.println(message);
			if (message.getType().equals(Bean_message.TYPE_SendMessage)) {
				FriendsFrame friendsFrame = FriendsFrameManager.getFriPane(message.getSender(), message.getReceiver());
				friendsFrame.music_GetMessage();
				if (friendsFrame != null) {
					friendsFrame.frameFriends.setVisible(true);
				}
				String content = message.getSender() + "   "+ message.getTime() + '\n' + message.getContent() + '\n';
				friendsFrame.OutputMessage(content);
			} else if (message.getType().equals(Bean_message.TYPE_Send2Qun)) {
				System.out.println(message);
				GroupsFrame groupFrame = GroupFrameManager.getGroupPane(message.getQunnum(), GroupInfoManager.getQunInfo(message.getQunnum()).getQunName(), message.getSender());
				groupFrame.music_GetMessage();
				String content =message.getSender() + "   " + message.getTime() + '\n' + message.getContent() + '\n';
				groupFrame.OutputMessage(content);
			} else if (message.getType().equals(Bean_message.TYPE_SendOnLineFile) || message.getType().equals(Bean_message.TYPE_SendOffLineFile)) {
				System.out.println(1);


				JFrame frameConfirm = new JFrame("确认");


				JPanel panelConfirm = new JPanel();
				panelConfirm.setLayout(new FlowLayout(FlowLayout.CENTER));
				frameConfirm.setBounds(700, 450, 400, 100);
				frameConfirm.setVisible(true);
				JButton buttonOK = new JButton("另存为");

				buttonOK.setFont(new Font("微软雅黑", Font.BOLD, 25));
				buttonOK.setActionCommand("1");
				JButton buttonNo = new JButton("拒绝");
				buttonNo.setActionCommand("2");
				buttonNo.setFont(new Font("微软雅黑", Font.BOLD, 25));
				frameConfirm.add(panelConfirm);
				panelConfirm.add(buttonOK);
				panelConfirm.add(buttonNo);

				buttonOK.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Main.miniQQ.chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						int result = Main.miniQQ.chooser.showDialog(Main.miniQQ, "另存为");
						String filename = Main.miniQQ.chooser.getSelectedFile().getPath();
						FileTransfer fileTransfer = new FileTransfer(config.SEVER_IP, config.port);
						frameConfirm.dispose();
						if (message.getType().equals(Bean_message.TYPE_SendOnLineFile)) {
							fileTransfer.receiveFile(message, new File(filename));
						} else {
							fileTransfer.downloadFile(message, new File(filename));
						}
					}
				});
				buttonNo.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						FileTransfer fileTransfer = new FileTransfer(config.SEVER_IP, config.port);
						fileTransfer.refuseFile(message);
						frameConfirm.dispose();
					}
				});

			} else if (message.getType().equals(Bean_message.TYPE_Jitter)) {
				FriendsFrame friendsFrame = FriendsFrameManager.getFriPane(message.getSender(), message.getReceiver());
				friendsFrame.frameFriends.setVisible(true);
				for (int i = 0; i < 4; i++) {
					friendsFrame.frameFriends.setLocation(friendsFrame.frameFriends.getX() + 4, friendsFrame.frameFriends.getY());
					friendsFrame.frameFriends.setLocation(friendsFrame.frameFriends.getX(), friendsFrame.frameFriends.getY() - 4);
					try {
						Thread.sleep(30);
						friendsFrame.frameFriends.setLocation(friendsFrame.frameFriends.getX() - 8, friendsFrame.frameFriends.getY());
						friendsFrame.frameFriends.setLocation(friendsFrame.frameFriends.getX(), friendsFrame.frameFriends.getY());
						Thread.sleep(30);
						friendsFrame.frameFriends.setLocation(friendsFrame.frameFriends.getX(), friendsFrame.frameFriends.getY());
						friendsFrame.frameFriends.setLocation(friendsFrame.frameFriends.getX(), friendsFrame.frameFriends.getY() + 4);
						Thread.sleep(30);
						friendsFrame.frameFriends.setLocation(friendsFrame.frameFriends.getX() + 4, friendsFrame.frameFriends.getY());
						friendsFrame.frameFriends.setLocation(friendsFrame.frameFriends.getX(), friendsFrame.frameFriends.getY());
						Thread.sleep(30);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			} else if (message.getType().equals(Bean_message.TYPE_YouDrawMeGuess) || message.getType().equals(Bean_message.TYPE_Send2Qun_YouDrawMeGuess)) {
				System.out.println(1);
				JFrame frameConfirm = new JFrame("请求");
				JPanel panelConfirm = new JPanel();
				panelConfirm.setLayout(new FlowLayout(FlowLayout.CENTER));
				frameConfirm.setBounds(700, 450, 400, 100);
				frameConfirm.setAlwaysOnTop(true);
				frameConfirm.setVisible(true);
				frameConfirm.setAlwaysOnTop(false);
				JButton buttonOK = new JButton("接受");

				buttonOK.setFont(new Font("微软雅黑", Font.BOLD, 25));
				buttonOK.setActionCommand("1");
				JButton buttonNo = new JButton("拒绝");
				buttonNo.setActionCommand("2");
				buttonNo.setFont(new Font("微软雅黑", Font.BOLD, 25));
				frameConfirm.add(panelConfirm);
				panelConfirm.add(buttonOK);
				panelConfirm.add(buttonNo);
				buttonOK.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
//						DrawPlayerFrame drawPlayerFrame = DrawPlayerFrameManager.getDrawPane(message.getReceiver());
						DrawPlayerFrame drawPlayerFrame = new DrawPlayerFrame();
						System.out.println(drawPlayerFrame);
						YouDrawMeGuessComm drawer = new YouDrawMeGuessComm(config.SEVER_IP, config.port,drawPlayerFrame);
						drawer.AcceptInvitation(message);
						frameConfirm.dispose();
					}
				});
				buttonNo.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frameConfirm.dispose();
					}
				});

			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


	
	
	

