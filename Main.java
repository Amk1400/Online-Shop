import javax.swing.*;
import java.awt.*;

public class Main {

    static JFrame jFrame= new JFrame("Online Shop");

    public static void main(String[] args) {
        createJFrame();
    }

    private static void createJFrame() {
        createBackend();
        createFrontend();
        jFrame.add(new SignRegPanel());
        jFrame.setVisible(true);
    }

    private static void createFrontend() {
        jFrame.setBackground(Color.CYAN);//Color is different from panels to figure possible bugs
        UIManager.put("Label.font", new Font("Arial Rounded MT Bold",Font.BOLD,18));
        UIManager.put("Button.font", new Font("Arial Rounded MT Bold",Font.BOLD,18));
        UIManager.put("TextField.font", new Font("Arial Rounded MT Bold",Font.PLAIN,14));
    }

    private static void createBackend() {
        jFrame.setSize(1000,700);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
