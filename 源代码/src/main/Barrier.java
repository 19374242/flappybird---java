package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import util.Constant;
import util.GameUtil;
/**
 * �ϰ��࣬���������ϰ���������
 * @author ��Զ
 * @version jdk1.8.0
 */
public class Barrier {
	private static BufferedImage imgs[];
	private Rectangle rect;

	private boolean move=true;
	private int speed=3;
	//�ϰ���״̬
	private boolean visible;
	//��̬�����
	static {
		imgs=new BufferedImage[3];
		for(int i=0;i<3;i++) {
			imgs[i]=GameUtil.loadBufferedImage(Constant.BARRIER_IMG_PATH[i]);
		}
	}
	private int x,y;
	private int width,height;
	private int type;
	public static final int TYPE_TOP_BOTTOM=0;
	public static final int TYPE_BOTTOM_TOP=2;
	public static final int TYPE_BOTTOM=4;
	public static final int TYPE_MOVE=6;
	//����ϰ���Ŀ�Ⱥ͸߶�
	public static final int BARRIER_WIDTH=imgs[0].getWidth();
	public static final int BARRIER_HEIGHT=imgs[0].getHeight();
	public static final int BARRIER_HEAD_WIDTH=imgs[1].getWidth();
	public static final int BARRIER_HEAD_HEIGHT=imgs[1].getHeight();//��������ͷ�����ȫһ��
	public static boolean isRushing=false;
	public Barrier() {
		rect=new Rectangle();
	}
	public Barrier(int x,int y,int height,int type) {
		this.x=x;
		this.y=y;
		this.width=BARRIER_WIDTH;
		this.height=height;
		this.type=type;
	}

	/**
	 * �����ϰ�x����
	 * @param x ������
	 */
	public void setX(int x) {
		this.x=x;
	}

	/**
	 * �����ϰ�y����
	 * @param y ������
	 */
	public void setY(int y) {
		this.y=y;
	}

	/**
	 * �����ϰ��߶�
	 * @param height �ϰ��߶�
	 */
	public void setHeight(int height) {
		this.height=height;
	}

	/**
	 * �����ϰ�����
	 * @param type �ϰ�����
	 */
	public void setType(int type) {
		this.type=type;
	}

	/**
	 * �ж��ϰ��Ƿ�ɼ�
	 * @return �Ƿ�ɼ�
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * �����ϰ��ɼ����
	 * @param visible �ɼ�����
	 */
	public void setVisible(boolean visible) {
		this.visible=visible;
	}

	/**
	 * �����ϰ�
	 * @param g ����
	 */
	public void draw(Graphics g) {
		if(Bird.isInRush&&Bird.readyEndRush==false){
			speed=30;
			isRushing=true;
		}
		else {
			speed=3;
			isRushing=false;
		}
		switch(type) {
			case TYPE_TOP_BOTTOM:
				drawTopToBottom(g);
				break;
			case TYPE_BOTTOM_TOP:
				drawBottomToTop(g);
				break;	
			case TYPE_BOTTOM:
				drawMiddle(g);
				break;
			case TYPE_MOVE:
				drawMove(g);
				break;
		}
	}

	/**
	 * ���ƴ��ϵ��µ��ϰ���
	 * @param g ����
	 */
	private void drawTopToBottom(Graphics g) {
		int count=(height-BARRIER_HEAD_HEIGHT)/BARRIER_HEIGHT+1;
		for(int i=0;i<count;i++) {
			g.drawImage(imgs[0],x,y+i*BARRIER_HEIGHT,null);
		}
		//����ͷ
		int y=height-BARRIER_HEAD_HEIGHT;
		g.drawImage(imgs[2],x-(BARRIER_HEAD_WIDTH-BARRIER_WIDTH)/2,y,null);
		x=x-speed;
		if(x<-50) {
			visible=false;
		}
		rect(g);
	}

	/**
	 * ���ƴ��µ��ϵ��ϰ���
	 * @param g ����
	 */
	private void drawBottomToTop(Graphics g) {
		int count=(height-BARRIER_HEAD_HEIGHT)/BARRIER_HEIGHT+1;
		for(int i=0;i<count;i++) {
			g.drawImage(imgs[0],x,Constant.FRAM_HEIGHT-(i+1)*BARRIER_HEIGHT,null);
		}
		//����ͷ
		int y=Constant.FRAM_HEIGHT-height;
		g.drawImage(imgs[1],x-(BARRIER_HEAD_WIDTH-BARRIER_WIDTH)/2,y,null);
		x=x-speed;
		if(x<-50) {
			visible=false;
		}
		rect(g);
	}

	/**
	 * �����м�ĵ��ϰ���
	 * @param g ����
	 */
	private void drawMiddle(Graphics g) {
		int count=(height-2*BARRIER_HEAD_HEIGHT)/BARRIER_HEIGHT+1;
		//������ͷ
		g.drawImage(imgs[1],x,y,null);
		for(int i=0;i<count;i++) {
			g.drawImage(imgs[0],x,y+BARRIER_HEAD_HEIGHT+i*BARRIER_HEIGHT,null);
		}
		//������ͷ
		int y1=y+height-BARRIER_HEAD_HEIGHT;
		g.drawImage(imgs[2],x,y1,null);
		x=x-speed;
		if(x<-50) {
			visible=false;
		}
		rect(g);
	}

	/**
	 * ���ƿ����ƶ��ĵ��ϰ���
	 * @param g ����
	 */
	private void drawMove(Graphics g) {
		int count=(height-2*BARRIER_HEAD_HEIGHT)/BARRIER_HEIGHT+1;
		//������ͷ
		g.drawImage(imgs[1],x,y,null);
		for(int i=0;i<count;i++) {
			g.drawImage(imgs[0],x,y+BARRIER_HEAD_HEIGHT+i*BARRIER_HEIGHT,null);
		}
		//������ͷ
		int y1=y+height-BARRIER_HEAD_HEIGHT;
		g.drawImage(imgs[2],x,y1,null);
		x=x-speed;
		if(x<-50) {
			visible=false;
		}
		rect(g);
		if(move) {
			y=y+5;
			if(y>=250) {
				move=false;
			}
		}
		else {
			y=y-5;
			if(y<=100) {
				move=true;
			}
		}
	}

	/**
	 * �ж��Ƿ���Ի�����һ���ϰ���
	 * @return �Ƿ���������ϰ�
	 */
	public boolean isInFrame() {
		return 600-x>150;
	}

	/**
	 * �ж��Ƿ���Ի�����һ������
	 * @return �Ƿ�������ɵ���
	 */
	public boolean isLifeOK(){
		if(600-x>75){
			if(600-x<100){
				return true;
			}
		}
		return false;
	}

	/**
	 * �����ϰ�����ײ����
	 * @param g ����
	 */
	public void rect(Graphics g) {
		int x1=this.x;
		int y1=this.y;
		int w=imgs[0].getWidth();
		g.setColor(Color.red);
		setRectangle(x1, y1, w, height);
	}

	/**
	 * �����ϰ������
	 * @param x ������
	 * @param y ������
	 * @param width ���
	 * @param height �߶�
	 */
	public void setRectangle(int x,int y,int width,int height) {
		rect.x=x;
		rect.y=y;
		rect.width=width;
		rect.height=height;
	}

	/**
	 * �õ��ϰ����Ӧ����
	 * @return ����
	 */
	public Rectangle getRect() {
		return rect;
	}
	
}
