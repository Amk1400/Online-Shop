import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignRegPanel extends JPanel implements ActionListener{

    JButton register    = new JButton("register");
    JButton sign        = new JButton("Sign-in");

    public SignRegPanel(){
        super();

        this.setSize(1000,700);
        this.setLayout(new GridLayout(4,3,100,100));
        this.setBackground(Color.pink);

        addButtonsToPanel();
        this.setVisible(true);
    }

    private void addButtonsToPanel() {
        this.add(new JLabel(" "));//these values are added due to layout work!
        this.add(new JLabel(" "));
        this.add(new JLabel(" "));
        this.add(new JLabel(" "));
        this.add(sign);
        this.add(new JLabel(" "));
        this.add(new JLabel(" "));
        this.add(register);
        this.add(new JLabel(" "));
        this.add(new JLabel(" "));
        this.add(new JLabel(" "));
        this.add(new JLabel(" "));

        sign.addActionListener(this);
        register.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(register)){
            Main.setCurrentPanel(Main.REG_PANEL);
        } else if (e.getSource().equals(sign)) {
            Main.setCurrentPanel(Main.SIGN_PANEL);
        }
    }
}
