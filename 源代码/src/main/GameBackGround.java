package main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import util.Constant;
import util.GameUtil;

import javax.swing.*;

/**
 * ��Ϸ�����࣬���ڳ�ʼ����Ϸ��������
 * @author ��Զ
 * @version jdk1.8.0
 */
public class GameBackGround{
	private BufferedImage bkimg;//������image��ʵ����
	private BufferedImage gameOver,rank;
	public GameBackGround() {
		bkimg=GameUtil.loadBufferedImage(Constant.BK_IMG_PATH);//����ͼƬ·��
	}
	public ImageIcon icon;
	Graphics now;

	/**
	 * ����Ϸ����
	 * @param g ����
	 */
	public void draw(Graphics g) {    //��ͼƬ
		//��һ����䱳��ɫ��
		g.setColor(Constant.BK_COLOR);//������ɫ
		g.fillRect(0, 0,Constant.FRAM_WIDTH,Constant.FRAM_HEIGHT);//���
		//�ڶ�����ͼƬ���ײ���
		int height=bkimg.getHeight();
		int width=bkimg.getWidth();
		//ѭ������
		int count=Constant.FRAM_WIDTH/width+1;
		for(int i=0;i<count;i++) {
			g.drawImage(bkimg,width*i,Constant.FRAM_HEIGHT-height,null);//���Ͻ�λ��
		}
	}

	/**
	 * ����Ϸ�����󱳾�
	 * @param g ����
	 */
	public void drawGameOver(Graphics g) {    //��ͼƬ
		//��һ����䱳��ɫ��
		g.setColor(Constant.BK_COLOR);//������ɫ
		g.fillRect(0, 0,Constant.FRAM_WIDTH,Constant.FRAM_HEIGHT);//���
		//�ڶ�����ͼƬ���ײ���
		int height=bkimg.getHeight();
		int width=bkimg.getWidth();
		//ѭ������
		int count=Constant.FRAM_WIDTH/width+1;
		for(int i=0;i<count;i++) {
			g.drawImage(bkimg,width*i,Constant.FRAM_HEIGHT-height,null);//���Ͻ�λ��
		}
		gameOver=GameUtil.loadBufferedImage(Constant.OVER_IMG_PATH);
		g.drawImage(gameOver,200,70,null);
	}

	/**
	 * ����Ϸ��ʼ������
	 * @param g ����
	 */
	public void drawInit(Graphics g) {    //��ͼƬ
		//��һ����䱳��ɫ��
		g.setColor(Constant.BK_COLOR);//������ɫ
		g.fillRect(0, 0,Constant.FRAM_WIDTH,Constant.FRAM_HEIGHT);//���
		//�ڶ�����ͼƬ���ײ���
		int height=bkimg.getHeight();
		int width=bkimg.getWidth();
		//ѭ������
		int count=Constant.FRAM_WIDTH/width+1;
		for(int i=0;i<count;i++) {
			g.drawImage(bkimg,width*i,Constant.FRAM_HEIGHT-height,null);//���Ͻ�λ��
		}
		BufferedImage gameInit=GameUtil.loadBufferedImage("img/start.png");
		g.drawImage(gameInit,200,70,null);
	}
}
