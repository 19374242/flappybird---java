package app;

import login.LoginFrame;
import login.User;
import main.GameFrame;

/**
 * ���������
 * @author ��Զ
 * @version jdk1.8.0
 */
public class GameApp {
	/**
	 * ���������
	 * @param args ����
	 */
	public static void main(String[] args) {
		//new GameFrame();
		LoginFrame login=new LoginFrame();
		User.readDatebase();

	}

}
