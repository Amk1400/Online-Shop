import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignIn extends JFrame {
    static JFrame frame = new JFrame("SIGN IN");
    static JPanel SignInpanel = new JPanel();

    public static void main(String[] args) {
        frame.setSize(1000, 700);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SignInpanel.setSize(1000, 700);
        SignInpanel.setLayout(null);
        SignInpanel.setBackground(Color.GREEN);
        UIManager.put("Label.font", new Font("Arial Rounded MT Bold",Font.BOLD,18));
        UIManager.put("Button.font", new Font("Arial Rounded MT Bold",Font.BOLD,18));
        UIManager.put("TextField.font", new Font("Arial Rounded MT Bold",Font.PLAIN,14));

        JLabel usernamelable = new JLabel("Username:");
        usernamelable.setBounds(350, 250, 105, 20);
        SignInpanel.add(usernamelable);
        JLabel Passwordlable = new JLabel("Password:");
        Passwordlable.setBounds(350, 350, 105, 20);
        SignInpanel.add(Passwordlable);

        TextField usernamefield = new TextField();
        usernamefield.setBounds(540, 250, 90, 20);
        SignInpanel.add(usernamefield);
        TextField passwordfield = new TextField();
        passwordfield.setBounds(540, 350, 90, 20);
        SignInpanel.add(passwordfield);
        //TODO saving the USERNAME AND PASS FOR FUTURE

        JButton SignInbutton = new JButton("SignIn");
        SignInbutton.setBounds(450, 500, 100, 60);
        SignInbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        SignInpanel.add(SignInbutton);

        frame.add(SignInpanel);
        frame.setVisible(true);
    }
}
