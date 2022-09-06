package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * �������߶�����࣬�������ɺͻ��ձ�������
 */
public class ProtectionPool {
    //����������ж�������
    private static List<Protection> pool=new ArrayList<>();
    //���г�ʼ���������
    private static final int initpool=3;
    //�������������
    private static final int maxpool=6;
    static {
        for(int i=0;i<initpool;i++) {
            Random random=new Random();
            int heightLife= random.nextInt(300)+100;
            pool.add(new Protection(600,heightLife));
        }
    }
    /**
     * ���ϰ����л�ȡ��������
     * @return �µı�������
     */
    public static Protection getPool() {
        int size=pool.size();
        if(size>0) {
            //�Ƴ�����������
            System.out.println("�ó�һ��������");
            return pool.remove(size-1);
        }
        else{
            Random random=new Random();
            int heightLife= random.nextInt(300)+100;
            System.out.println("�½�һ��������");
            return new Protection(600,heightLife);
        }
    }
    /**
     * ����������黹������
     * @param bar ��Ҫ�黹�ı�������
     */
    public static void setPool(Protection bar) {
        int size=pool.size();
        if(size<maxpool) {
            pool.add(bar);
            System.out.println("�黹������");
        }
    }
}