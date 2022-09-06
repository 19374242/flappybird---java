package content;

import util.Constant;
import util.GameUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * 关于我们类，用于初始化关于我们界面
 * @author 宫建文
 * @version jdk1.8.0
 */
public class AboutUs extends JFrame implements MouseListener {
    JFrame about;
    JLabel back;
    public static void main(String[] args) {
        new AboutUs();
    }
    public AboutUs() {
        about = new JFrame("关于我们");
        about.setBounds(Constant.FRAM_X, Constant.FRAM_Y, Constant.FRAM_WIDTH, Constant.FRAM_HEIGHT);
        about.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        back=new JLabel();
        ImageIcon icon=new ImageIcon("img/back.png");
        //改变图片大小
        //可以用Image中的getScaledInstance方法得到一个按照指定宽度和高度缩放以后的Image实例，然后再用setImage方法设置ImageIcon所显示的图像。
        icon.setImage(icon.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
        back.setIcon(icon);
        back.setBounds(530,5,50,50);
        back.addMouseListener(this);
        //鼠标移入显示文字
        back.setToolTipText("返回主页");
        //鼠标移入显示手型
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        about.add(back);

        AboutPanel aboutPanel=new AboutPanel();
        about.add(aboutPanel);
        about.setVisible(true);


    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
}

/**
 * 关于我们面板类，用于在关于我们界面上引入图片
 */
class AboutPanel  extends JPanel{
    public Image image1,image2;
    public AboutPanel()
    {


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Constant.BK_COLOR);
        g.fillRect(0,0,600,500);
        g.setColor(Color.gray);
        String word1="本游戏是北京航空航天大学20届面向对象的程序设计课大作业";
        String word2="本游戏仿编flappybird横板过关小游戏，完全由java语言编写";
        String word3="编写人员：队长：高远，队员：张迈方，宫建文";
        String word4="如有建议可发送邮件至3394045013@qq.com";
        g.setFont(new Font("微软雅黑", 1, 20));
        g.drawString(word1,30, 100);
        g.drawString(word2,30, 170);
        g.drawString(word3,30, 240);
        g.drawString(word4,30, 310);
        BufferedImage bkimg= GameUtil.loadBufferedImage(Constant.BK_IMG_PATH);
        int height=bkimg.getHeight();
        int width=bkimg.getWidth();
        //循环次数
        int count=Constant.FRAM_WIDTH/width+1;
        for(int i=0;i<count;i++) {
            g.drawImage(bkimg,width*i,Constant.FRAM_HEIGHT-height,null);//左上角位置
        }
    }

}
