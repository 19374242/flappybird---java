package main;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.swing.*;

import content.MainFrame;
import login.User;
import util.Constant;
/**
 * 游戏主界面类，用于初始化游戏界面
 * @author 高远
 * @version jdk1.8.0
 */
public class GameFrame extends Frame {//用Frame是因为JFrame repaint时不会调用update
	private GameBackGround gameBackGround;//实例化背景
	private Bird bird;//实例化鸟
	private BufferedImage bufferImg=new BufferedImage(Constant.FRAM_WIDTH,Constant.FRAM_HEIGHT,BufferedImage.TYPE_4BYTE_ABGR);//解决屏幕闪烁所用
	//实例化游戏前景类
	private GameFrontGround gameFrontGround;
	private GameBarrier gameBarrier;
	private Protection protection;
	private int judge=0;
	int flag=0;
	int over=0;
	
	public GameFrame() {
		over=0;
		flag=0;
		setVisible(true);
		setSize(Constant.FRAM_WIDTH,Constant.FRAM_HEIGHT);
		setTitle(Constant.FRAM_TITLE);
		setLocation(Constant.FRAM_X,Constant.FRAM_Y);//窗口位置
		setResizable(false);//窗口大小不可变
		addWindowListener(new WindowAdapter() {//正常关闭窗口，与JFrame不同
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		initGame();//初始化
		new run().start();//启动线程	
		//Java中定义了一些适配器(adapter)类，适配器类实现了相应接口中的所有方法，但方法内部没有做任何事情。可以通过扩展适配器类来实现接口，而不必实现接口中的所有方法
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				add(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				minu(e);
			}
		});
	}

	/**
	 * 初始化游戏
	 */
	public void initGame() {
		gameBackGround=new GameBackGround();
		bird=new Bird();
		gameFrontGround=new GameFrontGround();
		gameBarrier=new GameBarrier();
	}

	/**
	 * 开启线程的内部类
	 */
	class run extends Thread{
		/**
		 * 运行线程
		 */
		@Override
		public void run() {
			while(flag==0) {
				repaint();//使用repaint会调用update()方法中的清屏操作,因此下方重写了update方法，从清屏改为了画图
				try {
					Thread.sleep(30);
					if(over==1){
						this.interrupt();
						System.out.println("该线程结束");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 每隔30ms进行重画
	 * @param g the specified Graphics window
	 */
	@Override
	public void update(Graphics g) {
		if(bird.life) {
			Graphics graphics=bufferImg.getGraphics();//得到图片的画笔
			gameBackGround.draw(graphics);
			bird.draw(graphics);
			gameFrontGround.draw(graphics);
			gameBarrier.draw(graphics,bird);
			//一次性将图片绘制在屏幕中防止闪烁
			g.drawImage(bufferImg,0,0,null);
		}
		else {
//			String over="游戏结束";
//			g.setColor(Color.red);
//			g.setFont(new Font("微软雅黑", 1, 60));
//			g.drawString(over,180, 250);
//			String restart="按空格键重新开始";
//			g.drawString(restart, 60, 350);
			if(judge==1) return;
			else if(judge==0){
				judge=1;
			}
			Graphics graphics=bufferImg.getGraphics();//得到图片的画笔
			gameBackGround.drawGameOver(graphics);
			g.drawImage(bufferImg,0,0,null);
			String now="你坚持的时间是：";
			String most="你的记录是：";
			String next="按空格键继续";
			g.setColor(Color.orange);
			g.setFont(new Font("微软雅黑", 1, 30));
			g.drawString(now,150, 200);
			g.drawString(most,150, 250);
			g.setColor(Color.red);
			g.drawString(next,200, 350);
			g.setColor(Color.white);
			g.drawString(String.valueOf(GameTime.end)+"秒",400, 200);
			Long max=Long.parseLong(Constant.nowLogin.time);
			if(max>GameTime.end){
				g.drawString(String.valueOf(max)+"秒",400, 250);
			}
			else{
				Constant.nowLogin.time=String.valueOf(GameTime.end);
				g.drawString(String.valueOf(String.valueOf(GameTime.end))+"秒",400, 250);
				User.putBack();
			}
		}
	}

	/**
	 * 按下键盘时做出的相应反应
	 * @param e 按下键盘事件
	 */
	public void add(KeyEvent e) {
		switch(e.getKeyCode()){//获取键
			case KeyEvent.VK_UP: 
				bird.fly(1);
				break;
			case KeyEvent.VK_SPACE:
				if(bird.life==false) {
					restart();
				}
				break;
			case KeyEvent.VK_Q:
				if(bird.life==false){
					flag=1;
					over=1;
					this.setVisible(false);
					this.dispose();
					new MainFrame();
				}
				break;
		}
	}

	/**
	 * 松开键盘时做出的相应反应
	 * @param e 松开键盘事件
	 */
	public void minu(KeyEvent e) {
		switch(e.getKeyCode()){//获取键
			case KeyEvent.VK_UP: 
				bird.fly(5);
		}
	}

	/**
	 * 游戏重新开始，初始化资源
	 */
	public void restart() {
		gameBarrier.restart();
		bird.restartBirdPosition();
		judge=0;
	}

}
