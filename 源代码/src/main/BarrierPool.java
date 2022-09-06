package main;
//因为每次new一个对象会占用大量内存，所以需要对象池

import java.util.ArrayList;
import java.util.List;

/**
 * 障碍对象池类，用于生成和回收障碍
 */
public class BarrierPool {
	//管理池中所有对象容器
	private static List<Barrier> pool=new ArrayList<>();
	//池中初始化对象个数
	private static final int initpool=16;
	//对象池中最大个数
	private static final int maxpool=20;
	static {
		for(int i=0;i<initpool;i++) {
			pool.add(new Barrier());
		}
	}

	/**
	 * 从障碍池中获取对象
	 * @return 新的障碍
	 */
	public static Barrier getPool() {
		int size=pool.size();
		if(size>0) {
			//移除并返回数据
			System.out.println("拿出一个");
			return pool.remove(size-1);
		}
		else return new Barrier();
	}

	/**
	 * 将对象归还给容器
	 * @param bar 需要归还的障碍
	 */
	public static void setPool(Barrier bar) {
		int size=pool.size();
		if(size<maxpool) {
			pool.add(bar);
			System.out.println("归还");
		}
	}
}
