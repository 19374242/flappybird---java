package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * 保护道具对象池类，用于生成和回收保护道具
 */
public class ProtectionPool {
    //管理池中所有对象容器
    private static List<Protection> pool=new ArrayList<>();
    //池中初始化对象个数
    private static final int initpool=3;
    //对象池中最大个数
    private static final int maxpool=6;
    static {
        for(int i=0;i<initpool;i++) {
            Random random=new Random();
            int heightLife= random.nextInt(300)+100;
            pool.add(new Protection(600,heightLife));
        }
    }
    /**
     * 从障碍池中获取保护道具
     * @return 新的保护道具
     */
    public static Protection getPool() {
        int size=pool.size();
        if(size>0) {
            //移除并返回数据
            System.out.println("拿出一个保护罩");
            return pool.remove(size-1);
        }
        else{
            Random random=new Random();
            int heightLife= random.nextInt(300)+100;
            System.out.println("新建一个保护罩");
            return new Protection(600,heightLife);
        }
    }
    /**
     * 将保护对象归还给容器
     * @param bar 需要归还的保护道具
     */
    public static void setPool(Protection bar) {
        int size=pool.size();
        if(size<maxpool) {
            pool.add(bar);
            System.out.println("归还保护罩");
        }
    }
}