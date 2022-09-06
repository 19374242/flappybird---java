package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import util.Constant;
import util.GameUtil;
/**
 * 障碍类，用于描述障碍各种属性
 * @author 高远
 * @version jdk1.8.0
 */
public class Barrier {
	private static BufferedImage imgs[];
	private Rectangle rect;

	private boolean move=true;
	private int speed=3;
	//障碍物状态
	private boolean visible;
	//静态代码块
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
	//获得障碍物的宽度和高度
	public static final int BARRIER_WIDTH=imgs[0].getWidth();
	public static final int BARRIER_HEIGHT=imgs[0].getHeight();
	public static final int BARRIER_HEAD_WIDTH=imgs[1].getWidth();
	public static final int BARRIER_HEAD_HEIGHT=imgs[1].getHeight();//上下两个头宽高完全一致
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
	 * 设置障碍x坐标
	 * @param x 横坐标
	 */
	public void setX(int x) {
		this.x=x;
	}

	/**
	 * 设置障碍y坐标
	 * @param y 纵坐标
	 */
	public void setY(int y) {
		this.y=y;
	}

	/**
	 * 设置障碍高度
	 * @param height 障碍高度
	 */
	public void setHeight(int height) {
		this.height=height;
	}

	/**
	 * 设置障碍类型
	 * @param type 障碍类型
	 */
	public void setType(int type) {
		this.type=type;
	}

	/**
	 * 判断障碍是否可见
	 * @return 是否可见
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * 设置障碍可见与否
	 * @param visible 可见参数
	 */
	public void setVisible(boolean visible) {
		this.visible=visible;
	}

	/**
	 * 绘制障碍
	 * @param g 画笔
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
	 * 绘制从上到下的障碍物
	 * @param g 画笔
	 */
	private void drawTopToBottom(Graphics g) {
		int count=(height-BARRIER_HEAD_HEIGHT)/BARRIER_HEIGHT+1;
		for(int i=0;i<count;i++) {
			g.drawImage(imgs[0],x,y+i*BARRIER_HEIGHT,null);
		}
		//绘制头
		int y=height-BARRIER_HEAD_HEIGHT;
		g.drawImage(imgs[2],x-(BARRIER_HEAD_WIDTH-BARRIER_WIDTH)/2,y,null);
		x=x-speed;
		if(x<-50) {
			visible=false;
		}
		rect(g);
	}

	/**
	 * 绘制从下到上的障碍物
	 * @param g 画笔
	 */
	private void drawBottomToTop(Graphics g) {
		int count=(height-BARRIER_HEAD_HEIGHT)/BARRIER_HEIGHT+1;
		for(int i=0;i<count;i++) {
			g.drawImage(imgs[0],x,Constant.FRAM_HEIGHT-(i+1)*BARRIER_HEIGHT,null);
		}
		//绘制头
		int y=Constant.FRAM_HEIGHT-height;
		g.drawImage(imgs[1],x-(BARRIER_HEAD_WIDTH-BARRIER_WIDTH)/2,y,null);
		x=x-speed;
		if(x<-50) {
			visible=false;
		}
		rect(g);
	}

	/**
	 * 绘制中间的的障碍物
	 * @param g 画笔
	 */
	private void drawMiddle(Graphics g) {
		int count=(height-2*BARRIER_HEAD_HEIGHT)/BARRIER_HEIGHT+1;
		//绘制上头
		g.drawImage(imgs[1],x,y,null);
		for(int i=0;i<count;i++) {
			g.drawImage(imgs[0],x,y+BARRIER_HEAD_HEIGHT+i*BARRIER_HEIGHT,null);
		}
		//绘制下头
		int y1=y+height-BARRIER_HEAD_HEIGHT;
		g.drawImage(imgs[2],x,y1,null);
		x=x-speed;
		if(x<-50) {
			visible=false;
		}
		rect(g);
	}

	/**
	 * 绘制可以移动的的障碍物
	 * @param g 画笔
	 */
	private void drawMove(Graphics g) {
		int count=(height-2*BARRIER_HEAD_HEIGHT)/BARRIER_HEIGHT+1;
		//绘制上头
		g.drawImage(imgs[1],x,y,null);
		for(int i=0;i<count;i++) {
			g.drawImage(imgs[0],x,y+BARRIER_HEAD_HEIGHT+i*BARRIER_HEIGHT,null);
		}
		//绘制下头
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
	 * 判断是否可以绘制下一组障碍物
	 * @return 是否可以生成障碍
	 */
	public boolean isInFrame() {
		return 600-x>150;
	}

	/**
	 * 判断是否可以绘制下一个道具
	 * @return 是否可以生成道具
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
	 * 绘制障碍物碰撞矩形
	 * @param g 画笔
	 */
	public void rect(Graphics g) {
		int x1=this.x;
		int y1=this.y;
		int w=imgs[0].getWidth();
		g.setColor(Color.red);
		setRectangle(x1, y1, w, height);
	}

	/**
	 * 设置障碍物矩形
	 * @param x 横坐标
	 * @param y 纵坐标
	 * @param width 宽度
	 * @param height 高度
	 */
	public void setRectangle(int x,int y,int width,int height) {
		rect.x=x;
		rect.y=y;
		rect.width=width;
		rect.height=height;
	}

	/**
	 * 得到障碍物对应矩形
	 * @return 矩形
	 */
	public Rectangle getRect() {
		return rect;
	}
	
}
