package main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import util.Constant;
import util.GameUtil;

import javax.swing.*;

/**
 * 游戏背景类，用于初始化游戏背景界面
 * @author 高远
 * @version jdk1.8.0
 */
public class GameBackGround{
	private BufferedImage bkimg;//抽象类image的实现类
	private BufferedImage gameOver,rank;
	public GameBackGround() {
		bkimg=GameUtil.loadBufferedImage(Constant.BK_IMG_PATH);//传入图片路径
	}
	public ImageIcon icon;
	Graphics now;

	/**
	 * 画游戏背景
	 * @param g 画笔
	 */
	public void draw(Graphics g) {    //画图片
		//第一步填充背景色、
		g.setColor(Constant.BK_COLOR);//画笔颜色
		g.fillRect(0, 0,Constant.FRAM_WIDTH,Constant.FRAM_HEIGHT);//填充
		//第二部画图片（底部）
		int height=bkimg.getHeight();
		int width=bkimg.getWidth();
		//循环次数
		int count=Constant.FRAM_WIDTH/width+1;
		for(int i=0;i<count;i++) {
			g.drawImage(bkimg,width*i,Constant.FRAM_HEIGHT-height,null);//左上角位置
		}
	}

	/**
	 * 画游戏结束后背景
	 * @param g 画笔
	 */
	public void drawGameOver(Graphics g) {    //画图片
		//第一步填充背景色、
		g.setColor(Constant.BK_COLOR);//画笔颜色
		g.fillRect(0, 0,Constant.FRAM_WIDTH,Constant.FRAM_HEIGHT);//填充
		//第二部画图片（底部）
		int height=bkimg.getHeight();
		int width=bkimg.getWidth();
		//循环次数
		int count=Constant.FRAM_WIDTH/width+1;
		for(int i=0;i<count;i++) {
			g.drawImage(bkimg,width*i,Constant.FRAM_HEIGHT-height,null);//左上角位置
		}
		gameOver=GameUtil.loadBufferedImage(Constant.OVER_IMG_PATH);
		g.drawImage(gameOver,200,70,null);
	}

	/**
	 * 画游戏初始化背景
	 * @param g 画笔
	 */
	public void drawInit(Graphics g) {    //画图片
		//第一步填充背景色、
		g.setColor(Constant.BK_COLOR);//画笔颜色
		g.fillRect(0, 0,Constant.FRAM_WIDTH,Constant.FRAM_HEIGHT);//填充
		//第二部画图片（底部）
		int height=bkimg.getHeight();
		int width=bkimg.getWidth();
		//循环次数
		int count=Constant.FRAM_WIDTH/width+1;
		for(int i=0;i<count;i++) {
			g.drawImage(bkimg,width*i,Constant.FRAM_HEIGHT-height,null);//左上角位置
		}
		BufferedImage gameInit=GameUtil.loadBufferedImage("img/start.png");
		g.drawImage(gameInit,200,70,null);
	}
}
