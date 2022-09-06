package main;

/**
 * 坚持时间类，计算玩家在游戏中坚持的时间
 */
public class GameTime {
	//开始
	private long beginTime;
	//结束
	private long endTime;
	//时间差
	private long differ;
	public static long end;
	public GameTime() {
		
	}

	/**
	 * 初始化时间戳
	 */
	public void begin() {
		//该值表示的是当前时间与1970年1月1日0时0分0秒之间的时间差,单位是毫秒,习惯上被称为时间戳
		beginTime=System.currentTimeMillis();//返回long类型
		
	}

	/**
	 * 计算坚持时间
	 * @return 坚持的时间
	 */
	public long differ() {
		endTime=System.currentTimeMillis();
		differ=(endTime-beginTime)/1000;//秒
		end=differ;
		return differ;
	}
}
