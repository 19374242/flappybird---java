package login;
import util.Constant;
import util.GameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
/**
 * ע���࣬���ڳ�ʼ��ע�����
 * @author ��Զ
 * @version jdk1.8.0
 */
public class RegisterFrame extends JFrame implements ActionListener,MouseListener {
    JTextField usernameText,phoneNum;
    JPasswordField passwordText1,passwordText2;
    JButton loginButton;
    JButton registerButton,backButton;
    JLabel usernameLabel;
    JLabel passwordLabel1,passwordLabel2;
    JFrame registerWindow;
    JLabel phone;
    LoginFrame loginFrame;
    JFrame loginWindow;
    private BufferedImage title2;
    
    public RegisterFrame() {
    	registerWindow=new JFrame("ע��");
        registerWindow.setSize(Constant.FRAM_WIDTH,Constant.FRAM_HEIGHT);
        registerWindow.setLocation(Constant.FRAM_X,Constant.FRAM_Y);
    	//registerWindow.setSize(600, 500);
    	//registerWindow.setLocationRelativeTo(null);//�������������루д���������setbound��������ڸñ߿�
    	registerWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        usernameLabel = new JLabel("�û���");
        usernameLabel.setBounds(120, 120, 90, 35);
        usernameLabel.setForeground(Color.GRAY);//ǰ��ɫ
        usernameLabel.setFont(new Font("΢���ź�", Font.PLAIN, 21));
        registerWindow.add(usernameLabel);

        usernameText = new JTextField();
        usernameText.setBounds(230, 120, 250, 35);
        usernameText.setFont(new Font("΢���ź�",Font.PLAIN,16));
        usernameText.setOpaque(false);//�ڲ�͸��
        //usernameText.setBorder(null);//�߽�͸��
        usernameText.addMouseListener(this);
        usernameText.addActionListener(this);
        registerWindow.add(usernameText);

        passwordLabel1 = new JLabel("����");
        passwordLabel1.setBounds(120, 170, 90, 35);
        passwordLabel1.setForeground(Color.GRAY);
        passwordLabel1.setFont(new Font("΢���ź�", Font.PLAIN, 21));
        registerWindow.add(passwordLabel1);
        
        passwordText1 = new JPasswordField();
        passwordText1.setBounds(230, 170, 250, 35);
        passwordText1.setOpaque(false);
        //passwordText.setBorder(null);
        passwordText1.addMouseListener(this);
        passwordText1.addActionListener(this);
        registerWindow.add(passwordText1);
        
        passwordLabel2 = new JLabel("ȷ������");
        passwordLabel2.setBounds(120, 220, 90, 35);
        passwordLabel2.setForeground(Color.GRAY);
        passwordLabel2.setFont(new Font("΢���ź�", Font.PLAIN, 21));
        registerWindow.add(passwordLabel2);

        passwordText2 = new JPasswordField();
        passwordText2.setBounds(230, 220, 250, 35);
        passwordText2.setOpaque(false);
        //passwordText.setBorder(null);
        passwordText2.addMouseListener(this);
        passwordText2.addActionListener(this);
        registerWindow.add(passwordText2);
        
        phone = new JLabel("�ֻ���");
        phone.setBounds(120, 270, 90, 35);
        phone.setForeground(Color.GRAY);//ǰ��ɫ
        phone.setFont(new Font("΢���ź�", Font.PLAIN, 21));
        registerWindow.add(phone);
        
        phoneNum = new JTextField();
        phoneNum.setBounds(230, 270, 250, 35);
        phoneNum.setFont(new Font("΢���ź�",Font.PLAIN,16));
        phoneNum.setOpaque(false);//�ڲ�͸��
        phoneNum.addMouseListener(this);
        phoneNum.addActionListener(this);
        registerWindow.add(phoneNum);

        registerButton = new JButton("ע��");
        registerButton.setFont(new Font("΢���ź�",Font.PLAIN,21));
        registerButton.setBounds(170, 350, 100, 35);
        registerButton.setOpaque(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setForeground(Color.gray);
        registerWindow.add(registerButton);
        registerButton.addActionListener(this);

        backButton = new JButton("����");
        backButton.setFont(new Font("΢���ź�",Font.PLAIN,21));
        backButton.setBounds(320, 350, 100, 35);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setForeground(Color.gray);
        registerWindow.add(backButton);
        backButton.addActionListener(this);

        JLabel back=new JLabel();
        ImageIcon icon=new ImageIcon(Constant.TITLE_IMG_PATH);
        back.setIcon(icon);
        back.setBounds(200,40,icon.getIconWidth(),icon.getIconHeight());
        registerWindow.add(back);

        LoginPanel LP2=new LoginPanel();
        registerWindow.add(LP2);
        registerWindow.setVisible(true);
//        Graphics g=LP2.getGraphics();
//        title2=GameUtil.loadBufferedImage(Constant.TITLE_IMG_PATH);
//        g.drawImage(title2,200,70,null);
    }

    /**
     * ���°�ť��Ӧ�¼�
     * @param e ���°�ť
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	String name=usernameText.getText();
        String pas1=passwordText1.getText();
        String pas2=passwordText2.getText();
        String phone=phoneNum.getText();
        if(e.getSource()==backButton){
            registerWindow.setVisible(false);
            registerWindow.dispose();
            new LoginFrame();
        }
        else if (e.getSource() == registerButton) {
            if(name.equals("")) {
            	JOptionPane.showMessageDialog(registerWindow, "�������û���");
            	return;
            }
            if(pas1.equals("")) {
            	JOptionPane.showMessageDialog(registerWindow,"����������");
            	return;
            }
            if(pas2.equals("")) {
            	JOptionPane.showMessageDialog(registerWindow,"��ȷ������");
            	return;
            }
            if(phone.equals("")) {
            	JOptionPane.showMessageDialog(registerWindow,"�ֻ��Ų���Ϊ��");
            	return;
            }
            if(!pas1.equals(pas2)) {
            	JOptionPane.showMessageDialog(registerWindow,"�������벻��ͬ");
            	return;
            }
            if(User.UserNameToPassword.containsKey(name)) {
            	JOptionPane.showMessageDialog(registerWindow,"�û����Ѵ���");
            	return;
    		}
            User user=new User(name,pas1,phone,"0",false);
            JOptionPane.showMessageDialog(registerWindow,"ע��ɹ�");
            loginFrame=new LoginFrame();
        	registerWindow.setVisible(false);
        	registerWindow.dispose();
        }
        return;
    }

    /**
     * ��������Ӧ�¼�
     * @param e �������
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    	if(e.getSource()==usernameText){
		    usernameText.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
		}
    	else if(e.getSource()==passwordText1){
		    passwordText1.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
		}
    	else if(e.getSource()==passwordText2){
		    passwordText2.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
		}
    	else if(e.getSource()==phoneNum){
		    phoneNum.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
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
    	else if(e.getSource()==passwordText1){
		    passwordText1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}
    	else if(e.getSource()==passwordText2){
		    passwordText2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}
    	else if(e.getSource()==phoneNum){
		    phoneNum.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}
    }
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
}
