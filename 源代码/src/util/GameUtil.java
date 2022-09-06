package util;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameUtil {
	//装载图片
	//imageio.read读取文件，返回图片方式如下
	//File sourceimage = new File("c:\\mypic.jpg");
	//Image image = ImageIO.read(sourceimage);
	public static BufferedImage loadBufferedImage(String imgPath) {//传入路径，得到图片
		try {
			return ImageIO.read(new FileInputStream(imgPath));//括号内意思是以文件形式读入
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
