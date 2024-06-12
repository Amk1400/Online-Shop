import javax.swing.*;
import java.awt.*;

public class SignRegPanel extends JPanel {
    public SignRegPanel(){
        super();
        this.setSize(1000,700);
        this.setLayout(new GridLayout(4,3,100,100));
        this.setBackground(Color.GREEN);
        this.add(new JLabel(" "));
        this.add(new JLabel(" "));
        this.add(new JLabel(" "));
        this.add(new JLabel(" "));
        this.add(new JButton("Sign-in"));
        this.add(new JLabel(" "));
        this.add(new JLabel(" "));
        this.add(new JButton("register"));
        this.add(new JLabel(" "));
        this.add(new JLabel(" "));
        this.add(new JLabel(" "));
        this.add(new JLabel(" "));
        this.setVisible(true);
    }
}
