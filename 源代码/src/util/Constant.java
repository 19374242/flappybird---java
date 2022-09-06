package util;

import login.User;

import java.awt.Color;

public class Constant {//参数类
	public static final int FRAM_WIDTH=600;
	public static final int FRAM_HEIGHT=500;
	public static final String FRAM_TITLE="飞翔的小鸟";
	public static final int FRAM_X=200;
	public static final int FRAM_Y=200;
	//图片路径
	public static final String BK_IMG_PATH="img/bird_bk.png";
	public static final String OVER_IMG_PATH="img/over.png";
	public static final String TITLE_IMG_PATH="img/title.png";
	//颜色参数,color类
	public static final Color BK_COLOR=new Color(0x4B4CF);
	//小鸟图片路径
	public static final String[] BIRD_IMG= {"img/bird_normal.png","img/bird_up.png","img/bird_down.png"};
	public static final String[] BIRD_IMG_purple= {"img/bird_normal_purple.png","img/bird_up_purple.png","img/bird_down_purple.png"};
	public static final String[] BIRD_IMG_orange= {"img/bird_normal_orange.png","img/bird_up_orange.png","img/bird_down_orange.png"};
	public static final String[] BIRD_PROTECTION_IMG= {"img/bird_normal_protection.png","img/bird_up_protection.png","img/bird_down_protection.png"};
	public static final String[] BARRIER_IMG_PATH={"img/barrier.png","img/barrier_up.png","img/Barrier_down.png"};
	public static User nowLogin=null;
}
