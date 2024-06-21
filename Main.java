import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    static JFrame jFrame = new JFrame("Online Shop");
    static final SignRegPanel SIGN_REG_PANEL = new SignRegPanel();
    static final RegPanel REG_PANEL = new RegPanel(SIGN_REG_PANEL);
    static final SignPanel SIGN_PANEL = new SignPanel(SIGN_REG_PANEL);

    public static void main(String[] args) {
        createJFrame();
    }

    private static void createJFrame() {
        createBackend();
        createFrontend();
        setCurrentPanel(SIGN_REG_PANEL);
        jFrame.setVisible(true);
    }

    private static void createFrontend() {
        jFrame.setBackground(Color.CYAN);//Color is different from panels to figure possible bugs
        UIManager.put("Label.font", new Font("Arial Rounded MT Bold",Font.BOLD,18));
        UIManager.put("Button.font", new Font("Arial Rounded MT Bold",Font.BOLD,18));
        UIManager.put("TextField.font", new Font("Arial Rounded MT Bold",Font.PLAIN,18));
    }

    private static void createBackend() {
        jFrame.setSize(1000,700);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void setCurrentPanel(JPanel inputPanel){
        try {
            jFrame.remove(jFrame.getContentPane().getComponent(0));
        } catch (ArrayIndexOutOfBoundsException e) {
            //first time running program and there is no added panel
        }
        jFrame.add(inputPanel);
        jFrame.revalidate();
        jFrame.repaint();
        System.out.println();
    }


}
