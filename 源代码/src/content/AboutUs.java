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
 * ���������࣬���ڳ�ʼ���������ǽ���
 * @author ������
 * @version jdk1.8.0
 */
public class AboutUs extends JFrame implements MouseListener {
    JFrame about;
    JLabel back;
    public static void main(String[] args) {
        new AboutUs();
    }
    public AboutUs() {
        about = new JFrame("��������");
        about.setBounds(Constant.FRAM_X, Constant.FRAM_Y, Constant.FRAM_WIDTH, Constant.FRAM_HEIGHT);
        about.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        back=new JLabel();
        ImageIcon icon=new ImageIcon("img/back.png");
        //�ı�ͼƬ��С
        //������Image�е�getScaledInstance�����õ�һ������ָ����Ⱥ͸߶������Ժ��Imageʵ����Ȼ������setImage��������ImageIcon����ʾ��ͼ��
        icon.setImage(icon.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
        back.setIcon(icon);
        back.setBounds(530,5,50,50);
        back.addMouseListener(this);
        //���������ʾ����
        back.setToolTipText("������ҳ");
        //���������ʾ����
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
 * ������������࣬�����ڹ������ǽ���������ͼƬ
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
        String word1="����Ϸ�Ǳ������պ����ѧ20���������ĳ�����ƿδ���ҵ";
        String word2="����Ϸ�±�flappybird������С��Ϸ����ȫ��java���Ա�д";
        String word3="��д��Ա���ӳ�����Զ����Ա����������������";
        String word4="���н���ɷ����ʼ���3394045013@qq.com";
        g.setFont(new Font("΢���ź�", 1, 20));
        g.drawString(word1,30, 100);
        g.drawString(word2,30, 170);
        g.drawString(word3,30, 240);
        g.drawString(word4,30, 310);
        BufferedImage bkimg= GameUtil.loadBufferedImage(Constant.BK_IMG_PATH);
        int height=bkimg.getHeight();
        int width=bkimg.getWidth();
        //ѭ������
        int count=Constant.FRAM_WIDTH/width+1;
        for(int i=0;i<count;i++) {
            g.drawImage(bkimg,width*i,Constant.FRAM_HEIGHT-height,null);//���Ͻ�λ��
        }
    }

}
