package main;

import util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * ��̵����࣬������̵�������
 * @author ��Զ
 * @version jdk1.8.0
 */
public class Rush {
    public Rectangle rect;
    public int x,y,width,height,speed=3;
    BufferedImage img;
    public Rush(int x,int y) {
        rect=new Rectangle();
        this.x=x;
        this.y=y;
        this.width=50;
        this.height=50;
        setRectangle(x,y,width,height);
        img= GameUtil.loadBufferedImage("img/rocket.png");
    }

    /**
     * ���Ƴ�̵���
     * @param g ����
     */
    public void draw(Graphics g){
        g.setColor(Color.red);
        g.drawImage(img,x,y,width,height,null);
        setRectangle(x,y,width,height);
        x=x-speed;
    }

    /**
     * ���ó�̵��߶�Ӧ�ľ���
     * @param x ������
     * @param y ������
     * @param width ���
     * @param height �߶�
     */
    public void setRectangle(int x,int y,int width,int height) {
        rect.x=x;
        rect.y=y;
        rect.width=width;
        rect.height=height;
    }

    /**
     * �õ���̵��߶�Ӧ�ľ���
     * @return ��̵��߶�Ӧ�ľ���
     */
    public Rectangle getRect() {
        return rect;
    }
}
