package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
/**
 * 云彩类，描述云彩特性
 * @author 高远
 * @version jdk1.8.0
 */
public class Cloud {
	/**
	 * 图片
	 */
	private BufferedImage img;
	/**
	 * 云彩速度
	 */
	private int speed;
	/**
	 * 云彩位置
	 */
	private int x,y;
	public Cloud(BufferedImage img,int speed,int x,int y){
		this.img=img;
		this.speed=speed;
		this.x=x;
		this.y=y;
	}

	/**
	 * 绘制相应云彩位置
	 * @param g 画笔
	 */
	public void draw(Graphics g) {
		x=x-speed;
		g.drawImage(img, x, y, null);
		
	}

	/**
	 * 判断云彩是否飞出界面
	 * @return 返回云彩是否飞出界面
	 */
	public boolean isOutFrame() {
		if(x<10) {
			return true;
		}
		return false;
	}
}
