import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        createJFrame();
    }

    private static void createJFrame() {
        JFrame jFrame = new JFrame("Online Shop");
        jFrame.setSize(1000,700);
        jFrame.setBackground(Color.CYAN);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.add(new SignRegPanel());
        jFrame.setVisible(true);
    }
}