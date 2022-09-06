package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import content.Cloth;
import util.Constant;
import util.GameUtil;
/**
 * ���࣬��������С���������
 * @author ��Զ
 * @version jdk1.8.0
 */
public class Bird {
	//�Ƿ�Ե�����
	public boolean anotherLife=false;
	public static boolean isInRush=false;
	public static boolean readyEndRush=false;
	//С����ζ���
//	BufferedImage imgProtection=GameUtil.loadBufferedImage("img/protection.png");
	BufferedImage imgFlying=GameUtil.loadBufferedImage("img/flying.png");
	public int num=0;
	private Rectangle rect;
	//С������
	public boolean life=true;
	//С����ٶ�
	private int acceleration;
	private BufferedImage[] images;
	private BufferedImage[] images_protection;
	private int state;
	public static final int STATE_NORMAL=0;//ƽ�ŷ�
	public static final int STATE_UP=1;//���Ϸ�
	public static final int STATE_DOWN=2;//���·�
	
	private boolean up=false,down=false;
	private int speed=4;//Ĭ���ƶ��ٶ�
	private int x=200,y=200;//λ��
	public Bird() {
		images=new BufferedImage[3];//����ͼƬ
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
	 * ����С��
	 * @param g ����
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

		//����С�����
		rect.x=this.x;
		rect.y=this.y;
	}

	/**
	 * ����С����и߶�����ֱ�ٶ�
	 */
	public void flyLogic() {
		if(up) {
			acceleration--;
			if(acceleration<-10) {
				acceleration=-10;
			}
			y=y+acceleration;
			if(y<20){//���ɳ��߽磬��ɫ��Ȳ��20
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
	 * �ı�С�����״̬����ֱ����
	 * @param fly ����״̬����ֵ
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
	 * ����С�����ھ���
	 * @return С�����ھ���
	 */
	public Rectangle getRect() {
		return rect;
	}

	/**
	 * ���»���С��λ��
	 */
	public void restartBirdPosition() {
		life=true;
		x=200;
		y=200;
	}
}
