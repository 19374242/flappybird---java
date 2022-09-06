package main;
//��Ϸǰ����

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.GameUtil;
/**
 * ��Ϸǰ�����棬���������Ʋ�
 * @author ��Զ
 * @version jdk1.8.0
 */
public class GameFrontGround {
	private static final int CLOUD_COUNT=2;
	/**
	 * ����Ʋʵ�����
	 */
	private List<Cloud> cloud;
	/**
	 * �Ʋʵķ����ٶ�
	 */
	private static final int CLOUD_SPEED=1;
	/**
	 * ͼƬ��Դ
	 */
	private BufferedImage[] img;
	/**
	 * ���������
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
	 * �����Ʋ�
	 * @param g ����
	 */
	public void draw(Graphics g) {
		logic();
		for(int i=0;i<cloud.size();i++) {
			cloud.get(i).draw(g);
		}
	}

	/**
	 * �����Ʋʸ���
	 */
	private void logic() {
		//����0~500�������������С��100
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
