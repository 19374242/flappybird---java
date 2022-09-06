package content;

import login.User;
import util.Constant;
import util.GameUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 * 排行榜类，用于初始化排行榜界面
 * @author 高远
 * @version jdk1.8.0
 */
public class Ranking extends JFrame implements MouseListener{
    JFrame ranking;
    public static Long rankList[]=new Long [100];
    public static String name[]=new String [100];
    public static int top=0;
    public static void main(String[] args) {
        new Ranking();
    }
    public JLabel back;
    public Ranking(){
        top=0;
        for(int i=0;i<User.UserList.size();i++){
            name[top]=User.UserList.get(i).name;
            rankList[top++]=Long.parseLong(User.UserList.get(i).time);
        }
        for(int i=0;i<top;i++){
            System.out.println(name[i]+" "+rankList[i]);
        }
        //排序
        for(int i=top-1;i>0;i--){
            for(int j=i;j>0;j--){
                if(rankList[j-1]<rankList[j]){
                    Long tmp=rankList[j-1];
                    rankList[j-1]=rankList[j];
                    rankList[j]=tmp;
                    String s=name[j-1];
                    name[j-1]=name[j];
                    name[j]=s;
                }
            }
        }
        for(int i=0;i<top;i++){
            System.out.println(name[i]+" "+rankList[i]);
        }

        ranking=new JFrame("排行榜");
        ranking.setBounds(Constant.FRAM_X,Constant.FRAM_Y,Constant.FRAM_WIDTH,Constant.FRAM_HEIGHT);
        ranking.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
        ranking.add(back);

        ranking.add(new RankingPanel());
        ranking.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ranking.setVisible(false);
        ranking.dispose();
        new MainFrame();
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
 * 排行榜面板类，用于在排行榜界面引入图片
 */
class RankingPanel  extends JPanel {
    public Image image1,image2;
    public RankingPanel()
    {


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Constant.BK_COLOR);
        g.fillRect(0,0,600,500);
        BufferedImage bkimg= GameUtil.loadBufferedImage(Constant.BK_IMG_PATH);
        int height=bkimg.getHeight();
        int width=bkimg.getWidth();
        //循环画
        int count=Constant.FRAM_WIDTH/width+1;
        for(int i=0;i<count;i++) {
            g.drawImage(bkimg,width*i,Constant.FRAM_HEIGHT-height,null);//左上角位置
        }
        g.setColor(Color.red);
        g.setFont(new Font("微软雅黑", 1, 20));
        g.drawString("排名",100,100);
        g.drawString("用户名",250,100);
        g.drawString("分数",400,100);
        for(int i=0;i<5;i++){
            if(i>=Ranking.top) break;
            else{
                int j=i+1;
                g.setColor(Color.gray);
                g.drawString(new String(String.valueOf(j)),100,100+j*50);
                g.drawString(new String(Ranking.name[i]),250,100+j*50);
                g.drawString(new String(String.valueOf(Ranking.rankList[i])),400,100+j*50);
            }
        }
        return;
    }
}
