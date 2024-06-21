import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignPanel extends JPanel {

    public SignPanel(JPanel lastPanel){
        super();
        createFrontend();
        createUsernameSection();
        createPasswordSection();
        createSignInButton();

    }

    private void createSignInButton() {
        JButton SignInbutton = new JButton("SignIn");
        SignInbutton.setBounds(450, 500, 100, 60);
        SignInbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        this.add(SignInbutton);
    }

    private void createPasswordSection() {
        createPasswordLabel();
        createPasswordField();
    }

    private void createUsernameSection() {
        createUserNameLabel();
        createUsernameField();
    }

    private void createPasswordField() {
        TextField passwordfield = new TextField();
        passwordfield.setBounds(540, 350, 90, 20);
        this.add(passwordfield);
        //TODO saving the USERNAME AND PASS FOR FUTURE
    }

    private void createUsernameField() {
        TextField usernamefield = new TextField();
        usernamefield.setBounds(540, 250, 90, 20);
        this.add(usernamefield);
    }

    private void createPasswordLabel() {
        JLabel Passwordlable = new JLabel("Password:");
        Passwordlable.setBounds(350, 350, 105, 20);
        this.add(Passwordlable);
    }

    private void createUserNameLabel() {
        JLabel usernamelable = new JLabel("Username:");
        usernamelable.setBounds(350, 250, 105, 20);
        this.add(usernamelable);
    }

    private void createFrontend() {
        this.setSize(1000, 700);
        this.setLayout(null);
        this.setBackground(Color.pink);
    }
}
