import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Main {

    static JFrame jFrame = new JFrame("Online Shop");
    static IntroPanel SIGN_REG_PANEL;
    static RegPanel REG_PANEL;
    static SignPanel SIGN_PANEL;
    static buyPanel BUY_PANEL;
    static ManagerBuyPanel MANAGER_BUY_PANEL;

    public static void main(String[] args) throws SQLException {
        createJFrame();
    }

    private static void createJFrame() throws SQLException {
        createBackend();
        createFrontend();
        DataBase db = new DataBase();
    }

    private static void createFrontend() throws SQLException {
        jFrame.setBackground(Color.CYAN);//Color is different from panels to figure possible bugs
        UIManager.put("Label.font", new Font("Arial Rounded MT Bold",Font.BOLD,18));
        UIManager.put("Button.font", new Font("Arial Rounded MT Bold",Font.BOLD,18));
        UIManager.put("TextField.font", new Font("Arial Rounded MT Bold",Font.PLAIN,18));

        SIGN_REG_PANEL = new IntroPanel();
        REG_PANEL = new RegPanel(SIGN_REG_PANEL);
        SIGN_PANEL = new SignPanel(SIGN_REG_PANEL);
        BUY_PANEL = new buyPanel(SIGN_REG_PANEL);
        MANAGER_BUY_PANEL = new ManagerBuyPanel(SIGN_REG_PANEL);
        setCurrentPanel(SIGN_REG_PANEL);
        jFrame.setVisible(true);
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
