import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SignPanel extends MyPanel {

    JButton signInButton;

    public SignPanel(JPanel lastPanel){
        super();
        createFrontend();
        createUsernameSection();
        createPasswordSection();
        createSignInButton();

    }

    private void createSignInButton() {
        //TODO asssign path,width,height of sign in icon
        createButton(signInButton,"",0,0,"Sign-in");
        this.add(signInButton);
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
