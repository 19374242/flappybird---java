package main;
//游戏前景类

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.GameUtil;
/**
 * 游戏前景界面，用来绘制云层
 * @author 高远
 * @version jdk1.8.0
 */
public class GameFrontGround {
	private static final int CLOUD_COUNT=2;
	/**
	 * 存放云彩的容器
	 */
	private List<Cloud> cloud;
	/**
	 * 云彩的飞行速度
	 */
	private static final int CLOUD_SPEED=1;
	/**
	 * 图片资源
	 */
	private BufferedImage[] img;
	/**
	 * 制造随机数
	 */
	private Random random;
	
	public GameFrontGround() {
		cloud=new ArrayList<>();
		img=new BufferedImage[2];
		for(int i=0;i<2;i++) {
			img[i]=GameUtil.loadBufferedImage("img/cloud"+i+".png");
		}
		random=new Random();
	}

	/**
	 * 绘制云彩
	 * @param g 画笔
	 */
	public void draw(Graphics g) {
		logic();
		for(int i=0;i<cloud.size();i++) {
			cloud.get(i).draw(g);
		}
	}

	/**
	 * 绘制云彩个数
	 */
	private void logic() {
		//产生0~500的随机数，若其小于100
		if(((int)500*Math.random())<5) {
			//[0,2)
			Cloud cl=new Cloud(img[random.nextInt(2)],CLOUD_SPEED,600,random.nextInt(150));
			cloud.add(cl);
		}
		for(int i=0;i<cloud.size();i++) {
			Cloud cl=cloud.get(i);
			if(cl.isOutFrame()) {
				cloud.remove(i);
				i--;
			}
		}
	}
}
