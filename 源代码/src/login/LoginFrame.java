package login;
import javax.imageio.ImageIO;
import javax.swing.*;

import content.AddPicture;
import content.MainFrame;
import main.GameFrame;
import util.Constant;
import util.GameUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * �����࣬���ڳ�ʼ���������
 * @author ��Զ
 * @version jdk1.8.0
 */
public class LoginFrame extends JFrame implements ActionListener,MouseListener {
    JTextField usernameText;
    JPasswordField passwordText;
    JButton loginButton;
    JButton registerButton;
    JLabel usernameLabel;
    JLabel passwordLabel;
    RegisterFrame registerFrame;
    JFrame loginWindow;
    private BufferedImage title1;

    /**
     * ��ʼ���������
     */
    public LoginFrame() {
    	loginWindow=new JFrame("����");
    	//loginWindow.setSize(600, 400);
        loginWindow.setSize(Constant.FRAM_WIDTH,Constant.FRAM_HEIGHT);
        loginWindow.setLocation(Constant.FRAM_X,Constant.FRAM_Y);

    	//loginWindow.setLocationRelativeTo(null);//�������������루д���������setbound��������ڸñ߿�
    	loginWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        usernameLabel = new JLabel("�û���");
        usernameLabel.setBounds(120, 170, 90, 35);
        usernameLabel.setForeground(Color.GRAY);//ǰ��ɫ
        usernameLabel.setFont(new Font("΢���ź�", Font.PLAIN, 21));
        loginWindow.add(usernameLabel);

        usernameText = new JTextField();
        usernameText.setBounds(230, 170, 250, 35);
        usernameText.setFont(new Font("΢���ź�",Font.PLAIN,16));
        usernameText.setOpaque(false);//�ڲ�͸��
        //usernameText.setBorder(null);//�߽�͸��
        usernameText.addMouseListener(this);
        loginWindow.add(usernameText);

        passwordLabel = new JLabel("����");
        passwordLabel.setBounds(120, 220, 90, 35);
        passwordLabel.setForeground(Color.GRAY);
        passwordLabel.setFont(new Font("΢���ź�", Font.PLAIN, 21));
        loginWindow.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(230, 220, 250, 35);
        passwordText.setOpaque(false);
        //passwordText.setBorder(null);
        passwordText.addMouseListener(this);
        loginWindow.add(passwordText);

        loginButton = new JButton("����");
        loginButton.setFont(new Font("΢���ź�",Font.PLAIN,21));
        loginButton.setBounds(170, 300, 100, 35);
        loginButton.setForeground(Color.gray);
        loginButton.setOpaque(false);
        loginButton.setContentAreaFilled(false);//��ť͸��
        loginButton.addActionListener(this);
        loginWindow.add(loginButton);

        registerButton = new JButton("ע��");
        registerButton.setFont(new Font("΢���ź�",Font.PLAIN,21));
        registerButton.setBounds(320, 300, 100, 35);
        registerButton.setOpaque(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setForeground(Color.gray);
        loginWindow.add(registerButton);
        registerButton.addActionListener(this);

        JLabel back=new JLabel();
        ImageIcon icon=new ImageIcon(Constant.TITLE_IMG_PATH);
        back.setIcon(icon);
        back.setBounds(200,70,icon.getIconWidth(),icon.getIconHeight());
        loginWindow.add(back);

        LoginPanel LP1=new LoginPanel();
        loginWindow.add(LP1);
        loginWindow.setVisible(true);
//        Graphics g1=LP1.getGraphics();
//        title1=GameUtil.loadBufferedImage(Constant.TITLE_IMG_PATH);
//        g1.drawImage(title1,200,70,null);
    }

    /**
     * ���ɰ���ע��/���밴ť��Ӧ�¼�
     * @param e ��ť�¼�
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	String name=usernameText.getText();
    	String pas=passwordText.getText();
        if (e.getSource() == registerButton) {
        	registerFrame=new RegisterFrame();
        	loginWindow.setVisible(false);
        	loginWindow.dispose();
        }
        if (e.getSource() == loginButton) {
        	if(name.equals("")) {
            	JOptionPane.showMessageDialog(loginWindow, "�������û���");
            	return;
            }
            if(pas.equals("")) {
            	JOptionPane.showMessageDialog(loginWindow,"����������");
            	return;
            }
            if(!User.UserNameToPassword.containsKey(name)) {
            	JOptionPane.showMessageDialog(loginWindow,"�û���������");
            	return;
            }
            if(!User.UserNameToPassword.get(name).equals(pas)) {
            	JOptionPane.showMessageDialog(loginWindow,"�������");
            	return;
            }
            Constant.nowLogin=User.UserNameToUser.get(name);
            //new MainFrame();
            new MainFrame();
            loginWindow.setVisible(false);
            loginWindow.dispose();
        	return;
        }
    }

    /**
     * ��������Ӧ�¼�
     * @param e ����¼�
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    	if(e.getSource()==usernameText){
		    usernameText.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
		}
    	else if(e.getSource()==passwordText){
		    passwordText.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
		}
	}

    /**
     * ����Ƴ���Ӧ�¼�
     * @param e ����Ƴ�
     */
    @Override
	public void mouseExited(MouseEvent e) {
    	if(e.getSource()==usernameText){
		    usernameText.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}
    	else if(e.getSource()==passwordText){
		    passwordText.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}
    }
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
}
