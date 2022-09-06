package app;

import login.LoginFrame;
import login.User;
import main.GameFrame;

/**
 * 程序入口类
 * @author 高远
 * @version jdk1.8.0
 */
public class GameApp {
	/**
	 * 程序主入口
	 * @param args 参数
	 */
	public static void main(String[] args) {
		//new GameFrame();
		LoginFrame login=new LoginFrame();
		User.readDatebase();

	}

}
