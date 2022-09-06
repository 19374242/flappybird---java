package content;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * 加图片类，用于在界面引入图片
 * @author 高远
 * @version jdk1.8.0
 */
public class AddPicture  extends JPanel {
	private Image image;
	private int x,y,length,height;
	private String path;
	public AddPicture(int x,int y,int length,int height,String path)
	{
		this.x=x;
		this.y=y;
		this.length=length;
		this.height=height;
		this.path=path;
	    try {
	        image= ImageIO.read(new File(path));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}

	@Override
	public void paint(Graphics g) {
	    super.paint(g);
	    g.drawImage(image,x,y,length,height,null);
	}
}
