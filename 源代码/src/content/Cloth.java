package content;

import util.Constant;
import util.GameUtil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
/**
 * Ƥ���࣬���ڳ�ʼ��Ƥ������
 * @author ������
 * @version jdk1.8.0
 */
public class Cloth extends JFrame implements MouseListener, ActionListener {
    JFrame about;
    JLabel back;
    JButton bt1,bt2,bt3;
    String str1="",str2="",str3="";
    Boolean flag1=true,flag2=true,flag3=true;
    public static int which;
    public static void main(String[] args) {
        new Cloth();
    }
    public Cloth() {
        which=Constant.nowLogin.which;
        flag1=true;
        flag2=true;
        flag3=true;
        str1="";
        str2="";
        str3="";
        about = new JFrame("Ƥ������");
        about.setBounds(Constant.FRAM_X, Constant.FRAM_Y, Constant.FRAM_WIDTH, Constant.FRAM_HEIGHT);
        about.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        back=new JLabel();
        ImageIcon icon=new ImageIcon("img/back.png");
        //�ı�ͼƬ��С
        //������Image�е�getScaledInstance�����õ�һ������ָ����Ⱥ͸߶������Ժ��Imageʵ����Ȼ������setImage��������ImageIcon����ʾ��ͼ��
        icon.setImage(icon.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
        back.setIcon(icon);
        back.setBounds(530,5,50,50);
        back.addMouseListener(this);
        //���������ʾ����
        back.setToolTipText("������ҳ");
        //���������ʾ����
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        about.add(back);
        //��ť
        if(which==0){
            str1="Ĭ ��";
            flag1=false;
        }
        else{
            str1="ʹ ��";
        }
        bt1=new JButton(str1);
        bt1.setBounds(100,290,100,50);
        bt1.setFont(new Font("΢���ź�",Font.PLAIN,19));
        bt1.setEnabled(flag1);
        bt1.setForeground(Color.gray);
        bt1.setFocusPainted(false);//ȥ������������
        if(flag1) bt1.setBackground(Color.orange);
        else bt1.setBackground(Color.gray);
        bt1.addActionListener(this);
        bt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        about.add(bt1);

        if(which==1){
            str2="Ĭ ��";
            flag2=false;
        }
        else{
            str2="ʹ ��";
        }
        bt2=new JButton(str2);
        bt2.setBounds(250,290,100,50);
        bt2.setFont(new Font("΢���ź�",Font.PLAIN,19));
        bt2.setEnabled(flag2);
        bt2.setForeground(Color.gray);
        bt2.setFocusPainted(false);//ȥ������������
        if(flag2) bt2.setBackground(Color.orange);
        else bt2.setBackground(Color.gray);
        bt2.addActionListener(this);
        bt2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        about.add(bt2);

        if(which==2){
            str3="Ĭ ��";
            flag3=false;
        }
        else{
            str3="ʹ ��";
        }
        bt3=new JButton(str3);
        bt3.setBounds(400,290,100,50);
        bt3.setFont(new Font("΢���ź�",Font.PLAIN,19));
        bt3.setEnabled(flag3);
        bt3.setForeground(Color.gray);
        bt3.setFocusPainted(false);//ȥ������������
        if(flag3) bt3.setBackground(Color.orange);
        else bt3.setBackground(Color.gray);
        bt3.addActionListener(this);
        bt3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        about.add(bt3);

        if(Integer.parseInt(Constant.nowLogin.time)<50){
            bt2.setText("����");
        }
        if(Integer.parseInt(Constant.nowLogin.time)<100){
            bt3.setText("����");
        }

        ClothPanel clothPanel=new ClothPanel();
        about.add(clothPanel);
        about.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Constant.nowLogin.which=which;
        new MainFrame();
        about.setVisible(false);
        about.dispose();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bt2&&Integer.parseInt(Constant.nowLogin.time)<50){
            JOptionPane.showMessageDialog(about,"����ʱ��ﵽ50�뷽�ɽ���");
            return;
        }
        if(e.getSource()==bt3&&Integer.parseInt(Constant.nowLogin.time)<100){
            JOptionPane.showMessageDialog(about,"����ʱ��ﵽ100�뷽�ɽ���");
            return;
        }
        JOptionPane.showMessageDialog(about,"�����ɹ�");
        bt1.setForeground(Color.gray);
        bt2.setForeground(Color.gray);
        bt3.setForeground(Color.gray);
        if(e.getSource()==bt1){
            which=0;
            bt1.setText("Ĭ ��");
            bt2.setText("ʹ ��");
            bt3.setText("ʹ ��");
            flag1=false;
            flag2=true;
            flag3=true;
        }
        else if(e.getSource()==bt2){
            which=1;
            bt1.setText("ʹ ��");
            bt2.setText("Ĭ ��");
            bt3.setText("ʹ ��");
            flag1=true;
            flag2=false;
            flag3=true;
        }
        else if(e.getSource()==bt3){
            which=2;
            bt1.setText("ʹ ��");
            bt2.setText("ʹ ��");
            bt3.setText("Ĭ ��");
            flag1=true;
            flag2=true;
            flag3=false;
        }
        bt1.setEnabled(flag1);
        bt2.setEnabled(flag2);
        bt3.setEnabled(flag3);
        if(flag1) bt1.setBackground(Color.orange);
        else bt1.setBackground(Color.gray);
        if(flag2) bt2.setBackground(Color.orange);
        else bt2.setBackground(Color.gray);
        if(flag3) bt3.setBackground(Color.orange);
        else bt3.setBackground(Color.gray);

        if(Integer.parseInt(Constant.nowLogin.time)<50){
            bt2.setText("����");
        }
        if(Integer.parseInt(Constant.nowLogin.time)<100){
            bt3.setText("����");
        }
    }
}
/**
 * ����Ƥ������࣬������Ƥ������������ͼƬ
 */
class ClothPanel  extends JPanel{
    public Image image1,image2;
    public ClothPanel()
    {


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Constant.BK_COLOR);
        g.fillRect(0,0,600,500);
        g.setColor(Color.gray);
        BufferedImage bkimg= GameUtil.loadBufferedImage(Constant.BK_IMG_PATH);
        int height=bkimg.getHeight();
        int width=bkimg.getWidth();
        //ѭ������
        int count=Constant.FRAM_WIDTH/width+1;
        for(int i=0;i<count;i++) {
            g.drawImage(bkimg,width*i,Constant.FRAM_HEIGHT-height,null);//���Ͻ�λ��
        }
        BufferedImage ready= GameUtil.loadBufferedImage("img/start.png");
        g.drawImage(ready,210,70,null);

//        g.setColor(new Color(0xFAEBD7));
//        g.fillRect(100,170,100,150);
//        BufferedImage bird1= GameUtil.loadBufferedImage("img/bird_normal.png");
//        g.drawImage(bird1,135,220,null);

        g.setColor(new Color(0xFAEBD7));
        BufferedImage circle1= GameUtil.loadBufferedImage("img/circle1.png");
        g.drawImage(circle1,100,170,100,100,null);
        BufferedImage bird1= GameUtil.loadBufferedImage("img/bird_normal.png");
        g.drawImage(bird1,135,210,null);

//        g.fillRect(250,170,100,150);
//        BufferedImage bird2= GameUtil.loadBufferedImage("img/bird_normal_purple.png");
//        g.drawImage(bird2,285,220,null);
        BufferedImage circle2= GameUtil.loadBufferedImage("img/circle.png");
        g.drawImage(circle2,250,170,100,100,null);
        BufferedImage bird2= GameUtil.loadBufferedImage("img/bird_normal_purple.png");
        g.drawImage(bird2,285,210,null);

//        g.fillRect(400,170,100,150);
//        BufferedImage bird3= GameUtil.loadBufferedImage("img/bird_normal_orange.png");
//        g.drawImage(bird3,435,220,null);
        BufferedImage circle3= GameUtil.loadBufferedImage("img/circle2.png");
        g.drawImage(circle3,410,180,80,80,null);
        BufferedImage bird3= GameUtil.loadBufferedImage("img/bird_normal_orange.png");
        g.drawImage(bird3,435,210,null);


    }

}
