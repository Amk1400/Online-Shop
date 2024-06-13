import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        createJFrame();
    }

    private static void createJFrame() {
        JFrame jFrame = new JFrame("Online Shop");
        jFrame.setSize(1000,700);
        jFrame.setBackground(Color.CYAN);//Color is different from panels to figure possible bugs
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        UIManager.put("Label.font", new Font("Arial Rounded MT Bold",Font.BOLD,18));
        UIManager.put("Button.font", new Font("Arial Rounded MT Bold",Font.BOLD,18));
        UIManager.put("TextField.font", new Font("Arial Rounded MT Bold",Font.PLAIN,14));
        jFrame.add(new SignRegPanel());
        jFrame.setVisible(true);
    }
}
