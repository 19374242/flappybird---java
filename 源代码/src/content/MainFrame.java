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
 * ������������࣬���ڳ�ʼ�������������棨�û����ģ�
 * @author ��Զ
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
		readyFrame=new JFrame("׼��");
		readyFrame.setBounds(Constant.FRAM_X,Constant.FRAM_Y,Constant.FRAM_WIDTH,Constant.FRAM_HEIGHT);
//		readyFrame.setSize(Constant.FRAM_WIDTH,Constant.FRAM_HEIGHT);
//		readyFrame.setLocation(Constant.FRAM_X,Constant.FRAM_Y);
		readyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//		ImageIcon image = new ImageIcon("image/b1.png"); // ʵ������ť���󣬲������ð�ť����ʾͼƬ
//		JButton start = new JButton(image);

		start=new JButton("��ʼ��Ϸ");
		list=new JButton("�ɼ���");
		cloth=new JButton("Ƥ������");
//		cloth=new JButton("Ƥ������");
		start.setFont(new Font("΢���ź�",Font.PLAIN,21));
		start.setBackground(Color.orange);
		list.setFont(new Font("΢���ź�",Font.PLAIN,21));
		list.setBackground(Color.orange);
		cloth.setFont(new Font("΢���ź�",Font.PLAIN,21));
		cloth.setBackground(Color.orange);
//		cloth.setFont(new Font("΢���ź�",Font.PLAIN,21));
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
//		about=new JButton("��������");
//		about.setBackground(Color.orange);
//		about.setBounds(210,360,150,50);
//		about.setFont(new Font("΢���ź�",Font.PLAIN,21));
//		about.setForeground(Color.gray);
//		readyFrame.add(about);
//		about.setCursor(new Cursor(Cursor.HAND_CURSOR));

		back=new JLabel();
		ImageIcon icon=new ImageIcon("img/back.png");
		//�ı�ͼƬ��С
		//������Image�е�getScaledInstance�����õ�һ������ָ����Ⱥ͸߶������Ժ��Imageʵ����Ȼ������setImage��������ImageIcon����ʾ��ͼ��
		icon.setImage(icon.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		back.setIcon(icon);
		back.setBounds(530,5,50,50);
		back.addMouseListener(this);
		//���������ʾ����
		back.setToolTipText("�˳���¼");
		//���������ʾ����
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		readyFrame.add(back);


		readyFrame.add(new MainPanel());
		//������
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
 * ����������࣬�����ڵ��������������ͼƬ
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
		//ѭ������
		int count=Constant.FRAM_WIDTH/width+1;
		for(int i=0;i<count;i++) {
			g.drawImage(bkimg,width*i,Constant.FRAM_HEIGHT-height,null);//���Ͻ�λ��
		}
	}
}
