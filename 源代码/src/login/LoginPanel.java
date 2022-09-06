package login;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
/**
 * 登入类面板类，用于在登入界面上引入图片
 * @author 高远
 * @version jdk1.8.0
 */
public class LoginPanel  extends JPanel {
    private Image image;
    public LoginPanel()
    {
        try {
            image= ImageIO.read(new File("image/background1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image,0,0,600,500,null);
    }
}
