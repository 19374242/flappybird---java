package main;

/**
 * ���ʱ���࣬�����������Ϸ�м�ֵ�ʱ��
 */
public class GameTime {
	//��ʼ
	private long beginTime;
	//����
	private long endTime;
	//ʱ���
	private long differ;
	public static long end;
	public GameTime() {
		
	}

	/**
	 * ��ʼ��ʱ���
	 */
	public void begin() {
		//��ֵ��ʾ���ǵ�ǰʱ����1970��1��1��0ʱ0��0��֮���ʱ���,��λ�Ǻ���,ϰ���ϱ���Ϊʱ���
		beginTime=System.currentTimeMillis();//����long����
		
	}

	/**
	 * ������ʱ��
	 * @return ��ֵ�ʱ��
	 */
	public long differ() {
		endTime=System.currentTimeMillis();
		differ=(endTime-beginTime)/1000;//��
		end=differ;
		return differ;
	}
}
