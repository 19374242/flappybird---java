package main;

import util.Constant;
import util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * 保护道具类，描述保护道具特性
 * @author 高远
 * @version jdk1.8.0
 */
public class Protection {
    public Rectangle rect;
    public int x,y,width,height,speed=3;
    BufferedImage img;
    public Protection(int x,int y) {
        rect=new Rectangle();
        this.x=x;
        this.y=y;
        this.width=50;
        this.height=50;
        setRectangle(x,y,width,height);
        img=GameUtil.loadBufferedImage("img/protection.png");
    }
    /**
     * 绘制保护道具
     * @param g 画笔
     */
    public void draw(Graphics g){
        g.setColor(Color.red);
        g.drawImage(img,x,y,width,height,null);
        setRectangle(x,y,width,height);
        x=x-speed;
    }
    /**
     * 设置保护道具对应的矩形
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
     * 得到保护道具对应的矩形
     * @return 保护道具对应的矩形
     */
    public Rectangle getRect() {
        return rect;
    }
}
