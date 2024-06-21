import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class MyPanel extends JPanel implements ActionListener {
    public MyPanel(){
        super();
    }

    void createButton(JButton button, String path, int width, int height, String name) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(path));
            ImageIcon imageIcon = new ImageIcon(bufferedImage.getScaledInstance(width, height, 0));
            button.setIcon(imageIcon);
            button.setBackground(Color.pink);
            button.setBorderPainted(false);
        } catch (IOException e) {
            button.setName(name);
            throw new RuntimeException("couldn't find " + name + "Button icon in: " + path);
        }
        button.addActionListener(this);
    }
}
