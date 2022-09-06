package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ��Ϸ�ϰ��࣬���������ϰ��͵���
 * @author ��Զ
 * @version jdk1.8.0
 */
public class GameBarrier {
	private List<Barrier> barrier;
	private Random random=new Random();
	private GameTime gameTime;
	public static boolean isLife=false;
	public Protection pro;
	public static boolean isRush=false;
	public Rush rush;

	public GameBarrier() {
		barrier=new ArrayList<>();
		gameTime=new GameTime();

		int heightLife= random.nextInt(300)+100;
		pro=ProtectionPool.getPool();
		pro.y=heightLife;
		pro.x=600;

//		rush=new Rush(600,heightLife);
		int heightRush= random.nextInt(300)+100;
		rush=RushPool.getPool();
		rush.y=heightRush;
		rush.x=600;
	}

	/**
	 * �����ϰ��͵���
	 * @param g ����
	 * @param bird ���е���
	 */
	public void draw(Graphics g,Bird bird) {
		if(isLife){
			pro.draw(g);
		}
		if(isRush){
			rush.draw(g);
		}
		for(int i=0;i<barrier.size();i++) {
			Barrier bar=barrier.get(i);
			if(bar.isVisible()) {
				bar.draw(g);
			}
			else {
				Barrier remove=barrier.remove(i);
				BarrierPool.setPool(remove);
				i--;
			}
			
		}
		isCollision(bird);
		logic(g);
	}

	/**
	 * ��������ϰ��͵���
	 * @param g ����
	 */
	public void logic(Graphics g) {
		if(barrier.size()==0) {
			createNum();
			gameTime.begin();
//			Barrier top=new Barrier(600,0, numberTop,0);
//			barrier.add(top);
//			Barrier down=new Barrier(600,500-numberDown, numberDown,2);
//			barrier.add(down);
			insert(600,0, numberTop,0);
			insert(600,500-numberDown, numberDown,2);//����ؽ�ʡ�ڴ�
		}
		else {
			long differ=gameTime.differ();
			g.setColor(Color.white);
			g.setFont(new Font("΢���ź�", 1, 20));
			g.drawString("����ˣ�"+differ+"��", 30, 50);
			Barrier last=barrier.get(barrier.size()-1);

			if(last.isLifeOK()&&isRush==false&&Barrier.isRushing==false){
				int numRush= random.nextInt(500);
				if(numRush>120&&numRush<130){
					isRush=true;
				}
			}
			if(isRush&&rush.x<50){
				isRush=false;
				RushPool.setPool(rush);
				int heightRush= random.nextInt(300)+100;
				rush=RushPool.getPool();
				rush.y=heightRush;
				rush.x=600;
//				rush=new Rush(600,heightRush);
			}

			if(last.isLifeOK()&&isLife==false&&Barrier.isRushing==false){
				int numLife= random.nextInt(500);
				if(numLife>120&&numLife<130){
					isLife=true;
				}
			}
			if(isLife&&pro.x<50){
				isLife=false;
				ProtectionPool.setPool(pro);
				int heightLife= random.nextInt(300)+100;
				pro=ProtectionPool.getPool();
				pro.y=heightLife;
				pro.x=600;
//				pro=new Protection(600,heightLife,30,30);
			}

			if(last.isInFrame()) {
				createNum();
//				Barrier top=new Barrier(600,0, numberTop,0);
//				barrier.add(top);
//				Barrier down=new Barrier(600,500-numberDown, numberDown,2);
//				barrier.add(down);
				if(number<100) {
					insert(600,180,200,4);
				}
				else if(number>400){
					insert(600,125,170,6);
				}
				else {
					insert(600,0, numberTop,0);
					insert(600,500-numberDown, numberDown,2);
				}
			}
		}
	}

	/**
	 * �Ӷ���ػ�ȡ����
	 * @param x ������
	 * @param y ������
	 * @param height �߶�
	 * @param type �ϰ�����
	 */
	public void insert(int x,int y,int height,int type) {
		Barrier pool=BarrierPool.getPool();
		pool.setX(x);
		pool.setY(y);
		pool.setHeight(height);
		pool.setType(type);
		pool.setVisible(true);
		barrier.add(pool);
	}
	
	
	
	//�Ϸ��ϰ���߶�
	private int numberTop;
	//�·��ϰ���߶�
	private int numberDown;
	//�����
	private int number;

	/**
	 * ����100~500�漴�߶�
	 */
	public void createNum() {
		numberTop=random.nextInt(400)+100;
		numberDown=random.nextInt(400)+100;
		number=random.nextInt(500);
		//����߶Ȳ������������
		if(numberTop+numberDown>410) {
			createNum();
		}
	}

	/**
	 * �ж�С�����ϰ�����ײ
	 * @param bird ���е�С��
	 * @return �Ƿ���ײ
	 */
	public boolean isCollision(Bird bird) {
		//����������
		if(isLife&&pro.getRect().intersects(bird.getRect())){
			bird.anotherLife=true;
			isLife=false;
			ProtectionPool.setPool(pro);
			int heightLife= random.nextInt(300)+100;
			pro=ProtectionPool.getPool();
			pro.y=heightLife;
			pro.x=600;
//			pro=new Protection(600,heightLife,30,30);
		}
		//�������
		if(isRush&&rush.getRect().intersects(bird.getRect())){
			bird.isInRush=true;
			isRush=false;
			RushPool.setPool(rush);
			int heightRush= random.nextInt(300)+100;
			rush=RushPool.getPool();
			rush.y=heightRush;
			rush.x=600;
//			rush=new Rush(600,heightLife);
		}
		for(int i=0;i<barrier.size();i++) {
			Barrier bar=barrier.get(i);
			if(bar.getRect().intersects(bird.getRect())) {//�������������ײ
				if(Bird.isInRush==true){
					Barrier remove=barrier.remove(i);
					BarrierPool.setPool(remove);
					return false;
				}
				if(bird.anotherLife==true){
					bird.anotherLife=false;
					Barrier remove=barrier.remove(i);
					BarrierPool.setPool(remove);
					return false;
				}
				System.out.println("��ײ");
				bird.life=false;
				return true;
			}
		}
		return false;
	}

	/**
	 * ���¿ո�����¿�ʼ��Ϸ
	 */
	public void restart() {
		barrier.clear();
		isLife=false;
		int heightLife= random.nextInt(300)+100;
		pro=ProtectionPool.getPool();
		pro.y=heightLife;
		pro.x=600;
//		pro=new Protection(600,heightLife,30,30);
		isRush=false;
		Bird.isInRush=false;
		Bird.readyEndRush=false;

		RushPool.setPool(rush);
		int heightRush= random.nextInt(300)+100;
		rush=RushPool.getPool();
		rush.y=heightRush;
		rush.x=600;
//		rush=new Rush(600,heightRush);
	}
}
