package com.HIM.client;

import com.HIM.common.*;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/*
 * 实现文件的在线传送和接收、离线传送、进度显示、暂停、中止功能
 * 依赖:后端
 * @Author:Zhangt2333
 */

public class FileTransfer
{
	private String serverIP;
	private int serverPort;
	private Socket socket;
	//输入输出流
	private OutputStream os = null;
	private InputStream is = null;
	private DataOutputStream dos = null;
	private DataInputStream dis = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	private FileInputStream fis = null;
	private FileOutputStream fos = null;
	//外网端口
	private String InternetIP;
	private int InternetPort;
	
	private TransferInfoFrame transferInfoFrame;
	private Runnable senderThread;
	private Runnable receiverThread;
	
	//传输信息
	private int sender;
	private int receiver;
	private boolean onlineTransfer;
	private Bean_fileinfo fileinfo;
	
	//文件编号
	private int infoindex = -1;
	
	//工作进行时
	private boolean working = true;
	private boolean InterruptedStop = false;
	
	//一些魔法值
	public static final String splitChars = "-分_隔_符-"; 
	
	public FileTransfer(String serverIP,int serverPort)
	{
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		//连上服务端
		this.LinkToServer();
	}
	
	public void sendFile(File file,int sender,int receiver,boolean onlineTransfer)
	{
		this.sender = sender;
		this.receiver = receiver;
		this.onlineTransfer = onlineTransfer;
		this.fileinfo = new Bean_fileinfo(Bean_fileinfo.TYPE_File,file);
		//新建传输信息窗
		this.transferInfoFrame = new TransferInfoFrame(sender,receiver,onlineTransfer);
		this.transferInfoFrame.LaunchFrame();
		//离线发送
		if (!onlineTransfer)
		{
			this.senderThread = new SenderThread(fileinfo);
			new Thread(this.senderThread).start();
		}else //在线发送(请求间接穿透)
		{
			try
			{
				dos.writeUTF("sendMessage");
				oos.writeObject(new Bean_message(sender, receiver, 
								Bean_message.TYPE_SendOnLineFile, 
								tools.get_nowtime(true), fileinfo.getName() + splitChars 
								+ fileinfo.getSize() + splitChars + InternetIP + ":" + InternetPort));
				boolean echo = dis.readBoolean();
				if (!echo)
				{
					//文件被拒绝
					System.out.println("对方拒绝了文件");
				}else
				{
					String ipinfo = dis.readUTF();
					dos.writeUTF("peer2peer_FileTransfer");
					dos.writeUTF("sendFile");
					dos.writeUTF(ipinfo);//之后的所有转发到这里
					this.senderThread = new SenderThread(fileinfo);
					new Thread(this.senderThread).start();
				}
			}catch (Exception e) 
			{
				logger.writelog("FileTransfer", e.getMessage());
			}
		}
	}
	
	private void LinkToServer()
	{
		try
		{
			socket = new Socket();
			socket.setReuseAddress(true);
			socket.connect(new InetSocketAddress(serverIP,serverPort));
			//服务器满员退出时间
			socket.setSoTimeout(3000);
			//服务端和客户端的流建立要逆序，比如服务端建立i,客户端建立o
			os = socket.getOutputStream();
			is = socket.getInputStream();
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(os);
			ois = new ObjectInputStream(is);
			//双端验证
			dos.writeUTF("HIM");
			//获取外网IP和端口
			dos.writeUTF("getInternetIP");
			this.InternetIP = dis.readUTF();
			dos.writeUTF("getInternerPort");
			this.InternetPort = dis.readInt();
			socket.setSoTimeout(0);
			logger.writelog("FileTransfer", "连接服务器成功！");
		}catch (UnknownHostException e)
		{
			logger.writelog("FileTransfer", "连接服务器失败！");
		}catch (IOException e)
		{
			logger.writelog("FileTransfer", "获取io失败！");
		}
	}
	
	private void closeSocket()
	{
		try
		{
			if (os!=null) os.close();
			if (is!=null) is.close();
			if (dos!=null) dos.close();
			if (dis != null) dis.close();
			if (oos!=null) oos.close();
			if (ois!=null) ois.close();
			if (socket!=null) socket.close();
		}catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void SendOfflineFileInfo()
	{
		try
		{
			dos.writeUTF("sendMessage");
			Bean_message message = new Bean_message(sender, 
									receiver, Bean_message.TYPE_SendOffLineFile, 
									tools.get_nowtime(true), infoindex + splitChars
									+ fileinfo.getName() + splitChars + fileinfo.getSize());
			oos.writeObject(message);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void downloadFile(Bean_message message,File directory)
	{
		this.sender = message.getSender();
		this.receiver = message.getReceiver();
		this.onlineTransfer = false;
		try
		{
			dos.writeUTF("downloadFile");
			//下面的是预先商量好的魔法值了(当然是很差的实现)，0.文件编号,1.文件名,2.文件大小
			dos.writeInt(Integer.parseInt(message.getContent().split(splitChars)[0]));
			this.fileinfo = (Bean_fileinfo) ois.readObject();
		}catch (IOException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		fileinfo.setPath(directory.getAbsolutePath() + File.separatorChar + fileinfo.getName());
		//新建传输信息窗
		this.transferInfoFrame = new TransferInfoFrame(sender,receiver,onlineTransfer);
		this.transferInfoFrame.LaunchFrame();
		//接收离线文件
		this.receiverThread = new ReceiverThread(fileinfo);
		new Thread(this.receiverThread).start();
	}
	
	public void receiveFile(Bean_message message,File directory)
	{
		this.sender = message.getSender();
		this.receiver = message.getReceiver();
		this.onlineTransfer = true;
		try
		{
			dos.writeUTF("peer2peer_FileTransfer");
			dos.writeUTF("receiveFile");
			//下面的是预先商量好的魔法值了(当然是很差的实现)，0.文件名,1.文件大小,2.ipinfo
			dos.writeUTF(message.getContent().split(splitChars)[2]);
			fileinfo = (Bean_fileinfo)ois.readObject();
		}catch (IOException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		fileinfo.setPath(directory.getAbsolutePath() + File.separatorChar + fileinfo.getName());
		//新建传输信息窗
		this.transferInfoFrame = new TransferInfoFrame(sender,receiver,onlineTransfer);
		this.transferInfoFrame.LaunchFrame();
		//接收在线文件
		this.receiverThread = new ReceiverThread(fileinfo);
		new Thread(this.receiverThread).start();
	}
	
	public void refuseFile(Bean_message message)
	{
		try
		{
			dos.writeUTF("peer2peer_FileTransfer");
			dos.writeUTF("refuseFile");
			//下面的是预先商量好的魔法值了(当然是很差的实现)，0.文件名,1.文件大小,2.ipinfo
			dos.writeUTF(message.getContent().split(splitChars)[2]);
			closeSocket();
		}catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	class SenderThread implements Runnable
	{
		private Bean_fileinfo bean;
		
		public SenderThread(Bean_fileinfo bean)
		{
			this.bean = bean;
		}
		
		@Override
		public void run()
		{
			try
			{
				File file = new File(bean.getPath());
				if (file.exists())
				{
					dos.writeUTF("uploadFile");
					fis = new FileInputStream(file);
					//传文件bean
					oos.writeObject(bean);
					//判断文件在服务端已经存在
					if (!onlineTransfer) infoindex = dis.readInt();
					else infoindex = -1;
					if (infoindex == -1)
					{
						byte[] bytes = new byte[1024];
						int length = 0;
						long sumlenth = bean.getLength();
						int percent = 0;
						long progress = 0;
						int temp = 0;
						while( (length = fis.read(bytes,0,bytes.length)) != -1 )
						{
							while(!working)
							{
								Thread.sleep(1000);
							}
							if (InterruptedStop) 
							{
								throw new InterruptedException("中断传输");
							}
							dos.write(bytes,0,length);
							progress += length;
							/*需要被重写的进度显示部分
							------------------------------------------------------*/ 
							temp = (int)(100*progress/sumlenth);
							if (temp > percent)
							{
								percent = temp;
								transferInfoFrame.setProgress(percent);		
							}
							//-------------------------------------------------------
						}
						//获取新的文件编号
						if (!onlineTransfer) infoindex = dis.readInt();
					}
					logger.writelog("FileTransfer", "文件传输完毕!");
					transferInfoFrame.setProgress(100);
					if (!onlineTransfer) SendOfflineFileInfo();
				}
			} catch (SocketException e) 
			{
				logger.writelog("FileTransfer", "连接中断(uploadFile)!");
			} catch (IOException e) 
			{
				e.printStackTrace();
			} catch (InterruptedException e)
			{
				System.out.println(e.getMessage());
			} finally 
			{
				try 
				{
					if (fis!=null) fis.close();
					closeSocket();
					transferInfoFrame.stop();
				}catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	class ReceiverThread implements Runnable
	{
		private Bean_fileinfo bean;
		private File file;
		
		public ReceiverThread(Bean_fileinfo bean)
		{
			this.bean = bean;
			this.file = new File(bean.getPath());
		}
		
		@Override
		public void run()
		{
			try
			{
				if (bean != null)
				{
					fos = new FileOutputStream(file);
					//接受文件
					logger.writelog("FileTransfer", "接受文件中：" + file.getName());
					byte[] bytes = new byte[1024];
					int length = 0;
					long sumlenth = bean.getLength();
					int percent = 0;
					long progress = 0;
					int temp = 0;
					while(true)
					{
						while(!working)
						{
							Thread.sleep(1000);
						}
						if (InterruptedStop) 
						{
							throw new InterruptedException("中断传输");
						}
						if (progress >= sumlenth || length == -1) break;
						length = dis.read(bytes,0,bytes.length);
						fos.write(bytes,0,length);
						progress += length;
						temp = (int)(100*progress/sumlenth);
						if (temp > percent)
						{
							percent = temp;
							transferInfoFrame.setProgress(percent);						
						}
					}
					logger.writelog("FileTransfer", "接受文件完毕：" + file.getName());
				}
			} catch (SocketException | IndexOutOfBoundsException e) 
			{
				logger.writelog("FileTransfer", "连接中断(reveiveFile)!");
				file.delete();
			} catch (IOException | InterruptedException  e) 
			{
				logger.writelog("FileTransfer", e.getMessage());
			} catch (Exception e)
			{
				logger.writelog("FileTransfer", e.getMessage());
			} finally 
			{
				bean = null;
				try
				{
					if(fos != null) fos.close();
					closeSocket();
					transferInfoFrame.stop();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	
	class TransferInfoFrame
	{
		private String info;
		private JFrame frame;
		private JProgressBar jpb;
		private JButton button_pause;
		private JButton button_stop;
		
		public TransferInfoFrame(int sender,int receiver,boolean onlineTransfer)
		{
			
			//依次展示，传输者->接受者，离线/在线，文件名，文件路径，文件大小，进度条，暂停，中止按钮
			this.info = "<html>传输者：" + sender + "->" + receiver + "<br />" 
					    + "发送方式：" + (onlineTransfer?"在线":"离线") + "<br />"
						+ "文件名：" + fileinfo.getName() + "<br />"
						+ "文件路径：" + fileinfo.getPath() + "<br />"
					    + "文件大小：" + fileinfo.getSize() + "<html>";
		}
		
		public void LaunchFrame()
		{
			this.frame = new JFrame("TransferInfoFrame");
			this.frame.setLocationRelativeTo(null);
			
			JLabel jl = new JLabel(info);
			this.jpb = new JProgressBar(0,100);
			this.jpb.setValue(0);
			this.jpb.setStringPainted(true);
			
			JPanel buttonPanel = new JPanel();
			this.button_pause = new JButton("pause");
			this.button_stop = new JButton("stop");
			this.button_pause.addActionListener( e -> pause() );
			this.button_stop.addActionListener( e -> stop() );
			buttonPanel.setLayout(new GridLayout(1,2));
			buttonPanel.add(button_pause);
			buttonPanel.add(button_stop);
			
			this.frame.setLayout(new BorderLayout());
			this.frame.add(jl, BorderLayout.NORTH);
			this.frame.add(this.jpb,BorderLayout.CENTER);
			this.frame.add(buttonPanel,BorderLayout.SOUTH);
			
			this.frame.setResizable(false);
			this.frame.pack();
			this.frame.setVisible(true);
			this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		}
		
		public void setProgress(int percent)
		{
			this.jpb.setValue(percent);
		}
		
		private void pause()
		{
			this.button_pause.setEnabled(false);
			if (this.button_pause.getText().equals("pause"))
			{
				this.button_pause.setText("restart");
				working = false;
			}else
			{
				this.button_pause.setText("pause");
				working = true;
			}
			this.button_pause.setEnabled(true);
		}
		
		private void stop()
		{
			this.button_stop.setEnabled(false);
			InterruptedStop = true;
			working = true;
			this.frame.dispose();
			closeSocket();
		}
		
		
	}
	
	
	
	
	
}
