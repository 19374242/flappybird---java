package login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
/**
 * �û��࣬���ڶ�ȡ�ʹ洢�û���Ϣ
 * @author ��Զ
 * @version jdk1.8.0
 */
public class User {
	public static ArrayList<User> UserList=new ArrayList<>();
	public static HashMap<String,String> UserNameToPassword=new HashMap<>();
	public static HashMap<String,User> UserNameToUser=new HashMap<>();
	public String name,password,phone,time;
	public int which=0;

	/**
	 * ��ȡ���ݿ�����Ϣ��Ϊÿ���û�����һ���û���
	 */
	public static void readDatebase() {
		String filePath="datebase/record.csv";
		File record = new File(filePath);
		record.setReadable(true);
		InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(new FileInputStream(record), "UTF-8");
            br = new BufferedReader(isr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String line="";
		int x=0;
        try {
            while ((line = br.readLine()) != null) {
				if(x==0){
					x=1;
					continue;
				}
            	String a[]=line.split(",");
            	User newUser=new User(a[0],a[1],a[2],a[3],true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	/**
	 * �����û���
	 * @param name �û���
	 * @param password ����
	 * @param phone �绰����
	 * @param time ����ʱ��
	 * @param isReadDatebase �Ƿ����ʼ�����ݿ�ʱ����
	 */
	public User(String name,String password,String phone,String time,boolean isReadDatebase) {
		this.name=name;
		this.password=password;
		this.phone=phone;
		this.time=time;
		User.UserList.add(this);
		User.UserNameToPassword.put(name, password);
		User.UserNameToUser.put(name,this);
		if(isReadDatebase==false) updateDatebase();
	}

	/**
	 * �������ݿ���Ϣ
	 */
	public void updateDatebase() {
		String filePath="datebase/record.csv";
		File record = new File(filePath);
		record.setReadable(true);
		record.setWritable(true);
        BufferedWriter out;
        try {
        	//�ڶ���������ʾ��ĩβ��ʼ��
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(record, true)));
		    out.write(name+","+password+","+phone+","+time);
		    out.newLine();
		    out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}

	/**
	 * ����Ϣд�����ݿ�
	 */
	public static void putBack(){
		String filePath="datebase/record.csv";
		File record = new File(filePath);
		record.setReadable(true);
		record.setWritable(true);
		BufferedWriter out= null;
		for(int i=0;i<UserList.size();i++){
			try {
				if(i==0){
					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(record, false)));
					out.write("�˺�"+","+"����"+","+"�ֻ���"+","+"ʱ��");
					out.newLine();
				}
				else out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(record, true)));
				out.write(UserList.get(i).name+","+UserList.get(i).password+","+UserList.get(i).phone+","+UserList.get(i).time);
				out.newLine();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
}
