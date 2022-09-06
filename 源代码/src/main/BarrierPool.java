package main;
//��Ϊÿ��newһ�������ռ�ô����ڴ棬������Ҫ�����

import java.util.ArrayList;
import java.util.List;

/**
 * �ϰ�������࣬�������ɺͻ����ϰ�
 */
public class BarrierPool {
	//����������ж�������
	private static List<Barrier> pool=new ArrayList<>();
	//���г�ʼ���������
	private static final int initpool=16;
	//�������������
	private static final int maxpool=20;
	static {
		for(int i=0;i<initpool;i++) {
			pool.add(new Barrier());
		}
	}

	/**
	 * ���ϰ����л�ȡ����
	 * @return �µ��ϰ�
	 */
	public static Barrier getPool() {
		int size=pool.size();
		if(size>0) {
			//�Ƴ�����������
			System.out.println("�ó�һ��");
			return pool.remove(size-1);
		}
		else return new Barrier();
	}

	/**
	 * ������黹������
	 * @param bar ��Ҫ�黹���ϰ�
	 */
	public static void setPool(Barrier bar) {
		int size=pool.size();
		if(size<maxpool) {
			pool.add(bar);
			System.out.println("�黹");
		}
	}
}
