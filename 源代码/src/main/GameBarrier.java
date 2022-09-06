package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 游戏障碍类，用来绘制障碍和道具
 * @author 高远
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
	 * 绘制障碍和道具
	 * @param g 画笔
	 * @param bird 飞行的鸟
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
	 * 随机生成障碍和道具
	 * @param g 画笔
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
			insert(600,500-numberDown, numberDown,2);//对象池节省内存
		}
		else {
			long differ=gameTime.differ();
			g.setColor(Color.white);
			g.setFont(new Font("微软雅黑", 1, 20));
			g.drawString("坚持了："+differ+"秒", 30, 50);
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
	 * 从对象池获取对象
	 * @param x 横坐标
	 * @param y 纵坐标
	 * @param height 高度
	 * @param type 障碍类型
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
	
	
	
	//上方障碍物高度
	private int numberTop;
	//下方障碍物高度
	private int numberDown;
	//随机数
	private int number;

	/**
	 * 产生100~500随即高度
	 */
	public void createNum() {
		numberTop=random.nextInt(400)+100;
		numberDown=random.nextInt(400)+100;
		number=random.nextInt(500);
		//如果高度不合理，重新随机
		if(numberTop+numberDown>410) {
			createNum();
		}
	}

	/**
	 * 判断小鸟与障碍物碰撞
	 * @param bird 飞行的小鸟
	 * @return 是否碰撞
	 */
	public boolean isCollision(Bird bird) {
		//碰到保护罩
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
		//碰到冲刺
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
			if(bar.getRect().intersects(bird.getRect())) {//如果两个矩形碰撞
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
				System.out.println("碰撞");
				bird.life=false;
				return true;
			}
		}
		return false;
	}

	/**
	 * 按下空格键重新开始游戏
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
