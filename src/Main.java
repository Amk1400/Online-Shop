import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Main {

    static final JFrame JFRAME = new JFrame("Online Shop");
    static IntroPanel INTRO_PANEL;
    static RegPanel REG_PANEL;
    static SignPanel SIGN_PANEL;
    static BuyPanel BUY_PANEL;
    static ManagerBuyPanel MANAGER_BUY_PANEL;
    static ProfilePanel PROFILE_PANEL;

    public static void main(String[] args) throws SQLException {
        createJFrame();
    }

    private static void createJFrame() throws SQLException {
        createBackend();
        createFrontend();
        DataBase db = new DataBase();
    }

    private static void createFrontend() throws SQLException {
        JFRAME.setBackground(Color.CYAN);//Color is different from panels to figure possible bugs
        UIManager.put("Label.font", new Font("Arial Rounded MT Bold",Font.BOLD,18));
        UIManager.put("Button.font", new Font("Arial Rounded MT Bold",Font.BOLD,18));
        UIManager.put("TextField.font", new Font("Arial Rounded MT Bold",Font.PLAIN,18));

        INTRO_PANEL = new IntroPanel();
        REG_PANEL = new RegPanel(INTRO_PANEL);
        SIGN_PANEL = new SignPanel(INTRO_PANEL);
        //BUY_PANEL = new BuyPanel(INTRO_PANEL);
        MANAGER_BUY_PANEL = new ManagerBuyPanel(SIGN_PANEL);

        setCurrentPanel(INTRO_PANEL);
        JFRAME.setVisible(true);
    }

    private static void createBackend() {
        JFRAME.setSize(1000,700);
        JFRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void setCurrentPanel(JPanel inputPanel){
        try {
            JFRAME.remove(JFRAME.getContentPane().getComponent(0));
        } catch (ArrayIndexOutOfBoundsException e) {
            //first time running program and there is no added panel
        }
        JFRAME.add(inputPanel);
        JFRAME.revalidate();
        JFRAME.repaint();
    }


}
