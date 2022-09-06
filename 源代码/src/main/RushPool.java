package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * 冲刺道具对象池类，用于生成和回收冲刺道具
 */
public class RushPool {
    //管理池中所有对象容器
    private static List<Rush> pool=new ArrayList<>();
    //池中初始化对象个数
    private static final int initpool=3;
    //对象池中最大个数
    private static final int maxpool=6;
    static {
        for(int i=0;i<initpool;i++) {
            Random random=new Random();
            int heightLife= random.nextInt(300)+100;
            pool.add(new Rush(600,heightLife));
        }
    }
    /**
     * 从障碍池中获取冲刺道具
     * @return 新的冲刺道具
     */
    public static Rush getPool() {
        int size=pool.size();
        if(size>0) {
            //移除并返回数据
            System.out.println("拿出一个火箭");
            return pool.remove(size-1);
        }
        else{
            Random random=new Random();
            int heightLife= random.nextInt(300)+100;
            System.out.println("新建一个火箭");
            return new Rush(600,heightLife);
        }
    }
    /**
     * 将对象归还给容器
     * @param bar 需要归还的冲刺道具
     */
    public static void setPool(Rush bar) {
        int size=pool.size();
        if(size<maxpool) {
            pool.add(bar);
            System.out.println("归还火箭");
        }
    }
}
