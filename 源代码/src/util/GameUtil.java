package util;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameUtil {
	//װ��ͼƬ
	//imageio.read��ȡ�ļ�������ͼƬ��ʽ����
	//File sourceimage = new File("c:\\mypic.jpg");
	//Image image = ImageIO.read(sourceimage);
	public static BufferedImage loadBufferedImage(String imgPath) {//����·�����õ�ͼƬ
		try {
			return ImageIO.read(new FileInputStream(imgPath));//��������˼�����ļ���ʽ����
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
