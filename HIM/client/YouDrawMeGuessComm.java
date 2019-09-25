package com.HIM.client;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import com.HIM.common.Bean_message;
import com.HIM.common.Bean_picture;
import com.HIM.common.logger;
import com.HIM.common.tools;

import javax.swing.*;

/*
 * YouDrawMeGuessCommunicator
 * 你画我猜通讯
 * 依赖:后端
 * @Author:Zhangt2333
 */

public class YouDrawMeGuessComm
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
	//外网端口
	private String InternetIP;
	private int InternetPort;
	
	private SenderThread senderThread;
	private SenderEchoThread senderEchoThread;
	private ReceiverThread receiverThread;
	private DrawPlayerFrame drawPlayerFrame;
	
	//生成实例
	public YouDrawMeGuessComm(String serverIP,int serverPort)
	{
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		//连上服务端
		this.LinkToServer();
	}

	public YouDrawMeGuessComm(String serverIP,int serverPort,DrawPlayerFrame drawPlayerFrame)
	{
		this.drawPlayerFrame = drawPlayerFrame;
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		//连上服务端
		this.LinkToServer();
	}
	//连上服务器
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
			logger.writelog("YouDrawMeGuess", "连接服务器成功！");
		}catch (UnknownHostException e)
		{
			logger.writelog("YouDrawMeGuess", "连接服务器失败！");
		}catch (IOException e)
		{
			logger.writelog("YouDrawMeGuess", "获取io失败！");
		}
	}
	
	//断开连接
	public void closeSocket()
	{
		try
		{
			stopComm();
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
	
	public void InvitePlayer(int sender,int reveciver)
	{
		//邀请信息传输
		SendInvitation(sender,reveciver,false);
		this.senderThread = new SenderThread();
		this.senderEchoThread = new SenderEchoThread();
		this.senderThread.start();
		this.senderEchoThread.start();
	}
	
	public void InviteQun(int sender,int qunnum)
	{
		//邀请信息传输
		SendInvitation(sender,qunnum,true);
		this.senderThread = new SenderThread();
		this.senderEchoThread = new SenderEchoThread();
		this.senderThread.start();
		this.senderEchoThread.start();
	}
	
	//发送邀请函
	private void SendInvitation(int sender,int receiver,boolean Qun)
	{
		Bean_message message;
		if (!Qun)
		{
			message = new Bean_message(sender, receiver, 
						               Bean_message.TYPE_YouDrawMeGuess, 
						               tools.get_nowtime(true), InternetIP + ":" + InternetPort);
		}else
		{
			message = new Bean_message(sender, receiver, 
									   Bean_message.TYPE_Send2Qun_YouDrawMeGuess, 
						               tools.get_nowtime(true), InternetIP + ":" + InternetPort);
		}
		try
		{
			dos.writeUTF("peer2peers_YouDrawMeGuess");
			dos.writeUTF("sendInvitation");
			oos.writeObject(message);
		}catch (IOException e) 
		{
			logger.writelog("YouDrawMeGuess", e.getMessage());
		}
	}
	
	public void AcceptInvitation(Bean_message message)
	{
		try
		{
			dos.writeUTF("peer2peers_YouDrawMeGuess");
			dos.writeUTF("acceptInvitation");
			oos.writeObject(message);
		}catch (IOException e) 
		{
			logger.writelog("YouDrawMeGuess", e.getMessage());
		}
		this.receiverThread = new ReceiverThread();
		this.receiverThread.start();
	}
	
	public void offerPictrue(Bean_picture picture)
	{
		if (this.senderThread != null)
			this.senderThread.offerPictrue(picture);
	}
	
	private void addPlayer(int player)
	{
		DrawOwnerFrame.plays.add("用户:"+player);
//		DrawOwnerFrame.players.add(player+"");
//		DrawOwnerFrame.plays = new String[DrawOwnerFrame.players.size()];
//		System.out.println(DrawOwnerFrame.players.size());
//		for (int i = 0;i<DrawOwnerFrame.players.size();i++){
//			DrawOwnerFrame.plays[i] ="用户:"+DrawOwnerFrame.players.get(i);
//		}
//		DrawOwnerFrame.listPlayers.setListData(DrawOwnerFrame.plays);
		DrawOwnerFrame.listPlayers.setListData(DrawOwnerFrame.plays);
		//待写，一个展示当前玩家的列表加入玩家
		
		
	}
	
	private void removePlayer(int player)
	{
		DrawOwnerFrame.plays.remove("用户:"+player);
		DrawOwnerFrame.listPlayers.setListData(DrawOwnerFrame.plays);
//		DrawOwnerFrame.players.remove(Main.miniQQ.robot.queryUser_qq(player).getGameNum());
//		DrawOwnerFrame.plays = new String[DrawOwnerFrame.players.size()];
//		for (int i = 0;i<DrawOwnerFrame.players.size();i++){
//			DrawOwnerFrame.plays[i] =DrawOwnerFrame.players.get(i);
//		}
//		DrawOwnerFrame.listPlayers.setListData(DrawOwnerFrame.plays);
		//待写，一个展示当前玩家的列表删除玩家
		
	}
	
	private void handlePicture(Bean_picture picture)
	{
		//待写，一个处理图像的方法
		System.out.println("接收到"+picture);
       drawPlayerFrame.g = drawPlayerFrame.panelSuppose.getGraphics();
		drawPlayerFrame.draw(picture);
		System.out.println(4654656);


	}
	
	private void stopComm()
	{
		//待写，一个客人被断开连接的处理方法
		
	}
	
	
	//发送人用来发送消息的线程
	class SenderThread extends Thread
	{
		private Queue<Bean_picture> queue = new LinkedList<>();
		@Override
		public void run()
		{
			Bean_picture picture;
			try
			{
				while(true)
				{
					while(!queue.isEmpty())
					{
						picture = queue.poll();
						oos.writeObject(picture);
					}
					Thread.sleep(500);
				}
			} catch (IOException e)
			{
				e.printStackTrace();
				closeSocket();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
				closeSocket();
			}
		}
		
		//加入要传输的信息
		public void offerPictrue(Bean_picture picture)
		{
			queue.offer(picture);
		}
	}
	
	//发送人用来接收有人加入的线程
	class SenderEchoThread extends Thread
	{
		
		@Override
		public void run()
		{
			int player;
			String command;
			try
			{
				while(true)
				{
					command = dis.readUTF();
					if (command.equals("addPlayer"))
					{
						player = dis.readInt();
						addPlayer(player);
					}else if (command.equals("removePlayer"))
					{
						player = dis.readInt();
						removePlayer(player);
					}
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	//接收者用来接收信息的线程
	class ReceiverThread extends Thread
	{
		
		@Override
		public void run()
		{
			Object picture;
			try
			{
				while(true)
				{
					picture = ois.readObject();
					if (picture instanceof Bean_picture)
					{
						handlePicture((Bean_picture) picture);
					}
				}
			} catch (ClassNotFoundException | IOException e)
			{
				e.printStackTrace();
				closeSocket();
			}
		}
	}
	
	
	
}
