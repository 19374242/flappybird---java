package content;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import login.LoginFrame;
import login.LoginPanel;
import main.GameBackGround;
import main.GameFrame;
import main.GameTime;
import util.Constant;
import util.GameUtil;

/**
 * 登入后主界面类，用于初始化登入后的主界面（用户中心）
 * @author 高远
 * @version jdk1.8.0
 */
public class MainFrame extends JFrame implements ActionListener,MouseListener{
	JFrame readyFrame;
	JButton start;
	JButton list;
	JButton about;
	JButton cloth;
	JLabel back;


	public static void main(String[] args) {
		new MainFrame();
	}

	public MainFrame(){
		readyFrame=new JFrame("准备");
		readyFrame.setBounds(Constant.FRAM_X,Constant.FRAM_Y,Constant.FRAM_WIDTH,Constant.FRAM_HEIGHT);
//		readyFrame.setSize(Constant.FRAM_WIDTH,Constant.FRAM_HEIGHT);
//		readyFrame.setLocation(Constant.FRAM_X,Constant.FRAM_Y);
		readyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//		ImageIcon image = new ImageIcon("image/b1.png"); // 实例化按钮对象，并且设置按钮上显示图片
//		JButton start = new JButton(image);

		start=new JButton("开始游戏");
		list=new JButton("成绩榜单");
		cloth=new JButton("皮肤中心");
//		cloth=new JButton("皮肤中心");
		start.setFont(new Font("微软雅黑",Font.PLAIN,21));
		start.setBackground(Color.orange);
		list.setFont(new Font("微软雅黑",Font.PLAIN,21));
		list.setBackground(Color.orange);
		cloth.setFont(new Font("微软雅黑",Font.PLAIN,21));
		cloth.setBackground(Color.orange);
//		cloth.setFont(new Font("微软雅黑",Font.PLAIN,21));
//		cloth.setBackground(Color.orange);
		start.setBounds(210,180,150,50);
		list.setBounds(210,240,150,50);
		cloth.setBounds(210,300,150,50);
//		cloth.setBounds(210,360,150,50);
		start.setForeground(Color.gray);
		list.setForeground(Color.gray);
		cloth.setForeground(Color.gray);
//		cloth.setForeground(Color.gray);
		readyFrame.add(start);
		readyFrame.add(list);
//		readyFrame.add(about);
		readyFrame.add(cloth);
		start.setCursor(new Cursor(Cursor.HAND_CURSOR));
		list.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cloth.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		cloth.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		about=new JButton("关于我们");
//		about.setBackground(Color.orange);
//		about.setBounds(210,360,150,50);
//		about.setFont(new Font("微软雅黑",Font.PLAIN,21));
//		about.setForeground(Color.gray);
//		readyFrame.add(about);
//		about.setCursor(new Cursor(Cursor.HAND_CURSOR));

		back=new JLabel();
		ImageIcon icon=new ImageIcon("img/back.png");
		//改变图片大小
		//可以用Image中的getScaledInstance方法得到一个按照指定宽度和高度缩放以后的Image实例，然后再用setImage方法设置ImageIcon所显示的图像。
		icon.setImage(icon.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		back.setIcon(icon);
		back.setBounds(530,5,50,50);
		back.addMouseListener(this);
		//鼠标移入显示文字
		back.setToolTipText("退出登录");
		//鼠标移入显示手型
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		readyFrame.add(back);


		readyFrame.add(new MainPanel());
		//监听器
		start.addActionListener(this);
		list.addActionListener(this);
		//about.addActionListener(this);
		cloth.addActionListener(this);
		readyFrame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==start){
			new GameFrame();
			readyFrame.setVisible(false);
			readyFrame.dispose();
			return;
		}
		else if(e.getSource()==list){
			new Ranking();
			readyFrame.setVisible(false);
			readyFrame.dispose();
			return;
		}
//		else if(e.getSource()==about){
//			new AboutUs();
//			readyFrame.setVisible(false);
//			readyFrame.dispose();
//			return;
//		}
		else if(e.getSource()==cloth){
			new Cloth();
			readyFrame.setVisible(false);
			readyFrame.dispose();
			return;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Constant.nowLogin=null;
		new LoginFrame();
		readyFrame.setVisible(false);
		readyFrame.dispose();
		return;
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {
		Border greyLine=BorderFactory.createLineBorder(Color.gray);
		back.setBorder(greyLine);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		back.setBorder(null);
	}
}

/**
 * 主界面面板类，用于在登入后主界面引入图片
 */
class MainPanel  extends JPanel {
	public Image image1,image2;
	public MainPanel()
	{
		try {
			image1= ImageIO.read(new File("img/title.png"));
			image2= ImageIO.read(new File("img/bird_normal.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Constant.BK_COLOR);
		g.fillRect(0,0,600,500);
		g.drawImage(image1,200,30,image1.getWidth(null),image1.getHeight(null),null);
		g.drawImage(image2,270,110,image2.getWidth(null),image2.getHeight(null),null);
		BufferedImage bkimg=GameUtil.loadBufferedImage(Constant.BK_IMG_PATH);
		int height=bkimg.getHeight();
		int width=bkimg.getWidth();
		//循环次数
		int count=Constant.FRAM_WIDTH/width+1;
		for(int i=0;i<count;i++) {
			g.drawImage(bkimg,width*i,Constant.FRAM_HEIGHT-height,null);//左上角位置
		}
	}
}
