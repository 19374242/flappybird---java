package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import content.Cloth;
import util.Constant;
import util.GameUtil;
/**
 * 鸟类，用于描述小鸟各种属性
 * @author 高远
 * @version jdk1.8.0
 */
public class Bird {
	//是否吃到复活
	public boolean anotherLife=false;
	public static boolean isInRush=false;
	public static boolean readyEndRush=false;
	//小鸟矩形对象
//	BufferedImage imgProtection=GameUtil.loadBufferedImage("img/protection.png");
	BufferedImage imgFlying=GameUtil.loadBufferedImage("img/flying.png");
	public int num=0;
	private Rectangle rect;
	//小鸟生命
	public boolean life=true;
	//小鸟加速度
	private int acceleration;
	private BufferedImage[] images;
	private BufferedImage[] images_protection;
	private int state;
	public static final int STATE_NORMAL=0;//平着飞
	public static final int STATE_UP=1;//向上飞
	public static final int STATE_DOWN=2;//向下飞
	
	private boolean up=false,down=false;
	private int speed=4;//默认移动速度
	private int x=200,y=200;//位置
	public Bird() {
		images=new BufferedImage[3];//三张图片
//		images_protection=new BufferedImage[3];
//		for(int i=0;i<3;i++) {
//			images_protection[i]=GameUtil.loadBufferedImage(Constant.BIRD_PROTECTION_IMG[i]);
//		}
		for(int i=0;i<3;i++) {
			images[i]=GameUtil.loadBufferedImage(Constant.BIRD_IMG[i]);
		}
		if(Cloth.which==1){
			for(int i=0;i<3;i++) {
				images[i]=GameUtil.loadBufferedImage(Constant.BIRD_IMG_purple[i]);
			}
		}
		else if(Cloth.which==2){
			for(int i=0;i<3;i++) {
				images[i]=GameUtil.loadBufferedImage(Constant.BIRD_IMG_orange[i]);
			}
		}
		int w=images[0].getWidth();
		int h=images[0].getHeight();
		rect=new Rectangle(w,h);
	}

	/**
	 * 绘制小鸟
	 * @param g 画笔
	 */
	public void draw(Graphics g) {
		flyLogic();

		if(num<100&&isInRush&&readyEndRush==false){
			num++;
		}
		else if(num==100&&isInRush&&readyEndRush==false){
			readyEndRush=true;
			num++;
		}
		else if(num<130&&num>100&&readyEndRush&&isInRush){
			num++;
		}
		else if(num==130&&readyEndRush&&isInRush){
			num=0;
			isInRush=false;
			readyEndRush=false;
		}

		if(isInRush&&!readyEndRush) g.drawImage(imgFlying,x,y,images[0].getWidth(),images[0].getHeight()+5,null);
		else {
//			if(anotherLife){
//				g.drawImage(images_protection[state],x,y,images[0].getWidth()+10,images[0].getHeight()+20,null);
//			}
//			else{
//				g.drawImage(images[state],x,y,null);
//			}
			g.drawImage(images[state],x,y,null);
		}

		if(isInRush){
			g.setColor(Color.orange);
			g.drawRect(x,y,rect.width,rect.height);
		}
		else if(anotherLife){
			g.setColor(Color.red);
			g.drawRect(x,y,rect.width,rect.height);
			//g.drawImage(imgProtection,x,y,rect.width,rect.height,null);
		}

		//绘制小鸟矩形
		rect.x=this.x;
		rect.y=this.y;
	}

	/**
	 * 控制小鸟飞行高度与竖直速度
	 */
	public void flyLogic() {
		if(up) {
			acceleration--;
			if(acceleration<-10) {
				acceleration=-10;
			}
			y=y+acceleration;
			if(y<20){//不飞出边界，白色宽度差不多20
				y=20;
				acceleration=0;
			}
		}
		if(!up) {
			acceleration++;
			if(acceleration>10) {
				acceleration=10;
			}
			y=y+acceleration;
			if(y>Constant.FRAM_HEIGHT-25) {
				y=Constant.FRAM_HEIGHT-25;
				acceleration=0;
			}
		}
	}

	/**
	 * 改变小鸟飞行状态与竖直方向
	 * @param fly 飞行状态代表值
	 */
	public void fly(int fly) {
		switch(fly) {
			case 1:
				state=1;
				up=true;
				break;
			case 5:
				state=2;
				up=false;
				break;
		}
	}

	/**
	 * 返回小鸟所在矩形
	 * @return 小鸟所在矩形
	 */
	public Rectangle getRect() {
		return rect;
	}

	/**
	 * 重新绘制小鸟位置
	 */
	public void restartBirdPosition() {
		life=true;
		x=200;
		y=200;
	}
}
