package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
/**
 * �Ʋ��࣬�����Ʋ�����
 * @author ��Զ
 * @version jdk1.8.0
 */
public class Cloud {
	/**
	 * ͼƬ
	 */
	private BufferedImage img;
	/**
	 * �Ʋ��ٶ�
	 */
	private int speed;
	/**
	 * �Ʋ�λ��
	 */
	private int x,y;
	public Cloud(BufferedImage img,int speed,int x,int y){
		this.img=img;
		this.speed=speed;
		this.x=x;
		this.y=y;
	}

	/**
	 * ������Ӧ�Ʋ�λ��
	 * @param g ����
	 */
	public void draw(Graphics g) {
		x=x-speed;
		g.drawImage(img, x, y, null);
		
	}

	/**
	 * �ж��Ʋ��Ƿ�ɳ�����
	 * @return �����Ʋ��Ƿ�ɳ�����
	 */
	public boolean isOutFrame() {
		if(x<10) {
			return true;
		}
		return false;
	}
}
