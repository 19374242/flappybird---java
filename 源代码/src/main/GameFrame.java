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
 * ��Ϸ�������࣬���ڳ�ʼ����Ϸ����
 * @author ��Զ
 * @version jdk1.8.0
 */
public class GameFrame extends Frame {//��Frame����ΪJFrame repaintʱ�������update
	private GameBackGround gameBackGround;//ʵ��������
	private Bird bird;//ʵ������
	private BufferedImage bufferImg=new BufferedImage(Constant.FRAM_WIDTH,Constant.FRAM_HEIGHT,BufferedImage.TYPE_4BYTE_ABGR);//�����Ļ��˸����
	//ʵ������Ϸǰ����
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
		setLocation(Constant.FRAM_X,Constant.FRAM_Y);//����λ��
		setResizable(false);//���ڴ�С���ɱ�
		addWindowListener(new WindowAdapter() {//�����رմ��ڣ���JFrame��ͬ
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		initGame();//��ʼ��
		new run().start();//�����߳�	
		//Java�ж�����һЩ������(adapter)�࣬��������ʵ������Ӧ�ӿ��е����з������������ڲ�û�����κ����顣����ͨ����չ����������ʵ�ֽӿڣ�������ʵ�ֽӿ��е����з���
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
	 * ��ʼ����Ϸ
	 */
	public void initGame() {
		gameBackGround=new GameBackGround();
		bird=new Bird();
		gameFrontGround=new GameFrontGround();
		gameBarrier=new GameBarrier();
	}

	/**
	 * �����̵߳��ڲ���
	 */
	class run extends Thread{
		/**
		 * �����߳�
		 */
		@Override
		public void run() {
			while(flag==0) {
				repaint();//ʹ��repaint�����update()�����е���������,����·���д��update��������������Ϊ�˻�ͼ
				try {
					Thread.sleep(30);
					if(over==1){
						this.interrupt();
						System.out.println("���߳̽���");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * ÿ��30ms�����ػ�
	 * @param g the specified Graphics window
	 */
	@Override
	public void update(Graphics g) {
		if(bird.life) {
			Graphics graphics=bufferImg.getGraphics();//�õ�ͼƬ�Ļ���
			gameBackGround.draw(graphics);
			bird.draw(graphics);
			gameFrontGround.draw(graphics);
			gameBarrier.draw(graphics,bird);
			//һ���Խ�ͼƬ��������Ļ�з�ֹ��˸
			g.drawImage(bufferImg,0,0,null);
		}
		else {
//			String over="��Ϸ����";
//			g.setColor(Color.red);
//			g.setFont(new Font("΢���ź�", 1, 60));
//			g.drawString(over,180, 250);
//			String restart="���ո�����¿�ʼ";
//			g.drawString(restart, 60, 350);
			if(judge==1) return;
			else if(judge==0){
				judge=1;
			}
			Graphics graphics=bufferImg.getGraphics();//�õ�ͼƬ�Ļ���
			gameBackGround.drawGameOver(graphics);
			g.drawImage(bufferImg,0,0,null);
			String now="���ֵ�ʱ���ǣ�";
			String most="��ļ�¼�ǣ�";
			String next="���ո������";
			g.setColor(Color.orange);
			g.setFont(new Font("΢���ź�", 1, 30));
			g.drawString(now,150, 200);
			g.drawString(most,150, 250);
			g.setColor(Color.red);
			g.drawString(next,200, 350);
			g.setColor(Color.white);
			g.drawString(String.valueOf(GameTime.end)+"��",400, 200);
			Long max=Long.parseLong(Constant.nowLogin.time);
			if(max>GameTime.end){
				g.drawString(String.valueOf(max)+"��",400, 250);
			}
			else{
				Constant.nowLogin.time=String.valueOf(GameTime.end);
				g.drawString(String.valueOf(String.valueOf(GameTime.end))+"��",400, 250);
				User.putBack();
			}
		}
	}

	/**
	 * ���¼���ʱ��������Ӧ��Ӧ
	 * @param e ���¼����¼�
	 */
	public void add(KeyEvent e) {
		switch(e.getKeyCode()){//��ȡ��
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
	 * �ɿ�����ʱ��������Ӧ��Ӧ
	 * @param e �ɿ������¼�
	 */
	public void minu(KeyEvent e) {
		switch(e.getKeyCode()){//��ȡ��
			case KeyEvent.VK_UP: 
				bird.fly(5);
		}
	}

	/**
	 * ��Ϸ���¿�ʼ����ʼ����Դ
	 */
	public void restart() {
		gameBarrier.restart();
		bird.restartBirdPosition();
		judge=0;
	}

}
