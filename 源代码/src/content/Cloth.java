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
 * 皮肤类，用于初始化皮肤界面
 * @author 张迈方
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
        about = new JFrame("皮肤中心");
        about.setBounds(Constant.FRAM_X, Constant.FRAM_Y, Constant.FRAM_WIDTH, Constant.FRAM_HEIGHT);
        about.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        back=new JLabel();
        ImageIcon icon=new ImageIcon("img/back.png");
        //改变图片大小
        //可以用Image中的getScaledInstance方法得到一个按照指定宽度和高度缩放以后的Image实例，然后再用setImage方法设置ImageIcon所显示的图像。
        icon.setImage(icon.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
        back.setIcon(icon);
        back.setBounds(530,5,50,50);
        back.addMouseListener(this);
        //鼠标移入显示文字
        back.setToolTipText("返回主页");
        //鼠标移入显示手型
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        about.add(back);
        //按钮
        if(which==0){
            str1="默 认";
            flag1=false;
        }
        else{
            str1="使 用";
        }
        bt1=new JButton(str1);
        bt1.setBounds(100,290,100,50);
        bt1.setFont(new Font("微软雅黑",Font.PLAIN,19));
        bt1.setEnabled(flag1);
        bt1.setForeground(Color.gray);
        bt1.setFocusPainted(false);//去掉字体外面框框
        if(flag1) bt1.setBackground(Color.orange);
        else bt1.setBackground(Color.gray);
        bt1.addActionListener(this);
        bt1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        about.add(bt1);

        if(which==1){
            str2="默 认";
            flag2=false;
        }
        else{
            str2="使 用";
        }
        bt2=new JButton(str2);
        bt2.setBounds(250,290,100,50);
        bt2.setFont(new Font("微软雅黑",Font.PLAIN,19));
        bt2.setEnabled(flag2);
        bt2.setForeground(Color.gray);
        bt2.setFocusPainted(false);//去掉字体外面框框
        if(flag2) bt2.setBackground(Color.orange);
        else bt2.setBackground(Color.gray);
        bt2.addActionListener(this);
        bt2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        about.add(bt2);

        if(which==2){
            str3="默 认";
            flag3=false;
        }
        else{
            str3="使 用";
        }
        bt3=new JButton(str3);
        bt3.setBounds(400,290,100,50);
        bt3.setFont(new Font("微软雅黑",Font.PLAIN,19));
        bt3.setEnabled(flag3);
        bt3.setForeground(Color.gray);
        bt3.setFocusPainted(false);//去掉字体外面框框
        if(flag3) bt3.setBackground(Color.orange);
        else bt3.setBackground(Color.gray);
        bt3.addActionListener(this);
        bt3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        about.add(bt3);

        if(Integer.parseInt(Constant.nowLogin.time)<50){
            bt2.setText("解锁");
        }
        if(Integer.parseInt(Constant.nowLogin.time)<100){
            bt3.setText("解锁");
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
            JOptionPane.showMessageDialog(about,"最长坚持时间达到50秒方可解锁");
            return;
        }
        if(e.getSource()==bt3&&Integer.parseInt(Constant.nowLogin.time)<100){
            JOptionPane.showMessageDialog(about,"最长坚持时间达到100秒方可解锁");
            return;
        }
        JOptionPane.showMessageDialog(about,"更换成功");
        bt1.setForeground(Color.gray);
        bt2.setForeground(Color.gray);
        bt3.setForeground(Color.gray);
        if(e.getSource()==bt1){
            which=0;
            bt1.setText("默 认");
            bt2.setText("使 用");
            bt3.setText("使 用");
            flag1=false;
            flag2=true;
            flag3=true;
        }
        else if(e.getSource()==bt2){
            which=1;
            bt1.setText("使 用");
            bt2.setText("默 认");
            bt3.setText("使 用");
            flag1=true;
            flag2=false;
            flag3=true;
        }
        else if(e.getSource()==bt3){
            which=2;
            bt1.setText("使 用");
            bt2.setText("使 用");
            bt3.setText("默 认");
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
            bt2.setText("解锁");
        }
        if(Integer.parseInt(Constant.nowLogin.time)<100){
            bt3.setText("解锁");
        }
    }
}
/**
 * 关于皮肤面板类，用于在皮肤界面上引入图片
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
        //循环次数
        int count=Constant.FRAM_WIDTH/width+1;
        for(int i=0;i<count;i++) {
            g.drawImage(bkimg,width*i,Constant.FRAM_HEIGHT-height,null);//左上角位置
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
