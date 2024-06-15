import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegPanel implements ActionListener{
        JTextField passwordAgainField;
        JTextField passwordField;
        JTextField usernameField;
        JButton registerButton;
        JButton backButton;
        JPanel headerPanel;
        JPanel p;
        ArrayList<String> errors = new ArrayList<>();

        public RegPanel(JPanel p){
            this.p = p;
            setPanel();
        }

        RegPanel(JPanel p, ArrayList<String> errors){
            this.errors = errors;
            this.p = p;
            setPanel();
        }

        public void setPanel(){
            p.removeAll();

            p.setSize(1000,700);
            p.setLayout(new BorderLayout());
            p.setBackground(Color.GREEN);

            headerPanel = new JPanel();
            p.add(headerPanel,BorderLayout.NORTH);
            headerPanel.setLayout(new BorderLayout());
            headerPanel.setBackground(Color.GREEN);

            backButton=new JButton();
            ImageIcon imageIcon = new ImageIcon("54716.png");
            Image image = imageIcon.getImage().getScaledInstance(50,50,0);
            backButton = new JButton(new ImageIcon());
            backButton.setIcon(new ImageIcon(image));
            backButton.setBackground(Color.GREEN);
            backButton.setBorderPainted(false);
            backButton.addActionListener(this);
            headerPanel.add(backButton,BorderLayout.WEST);

            p.add(infoPanel(p),BorderLayout.CENTER);

            p.repaint();
            p.revalidate();
            p.setVisible(true);
        }

        private JPanel infoPanel(JPanel p) {
            JPanel panel = new JPanel();
            panel.setSize(500,600);
            panel.setLayout(new GridBagLayout());
            panel.setBackground(Color.GREEN);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,0,5,0);

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(new JLabel("                         "),gbc);
            gbc.gridx = 1;
            gbc.gridy = 0;
            panel.add(new JLabel("                         "),gbc);
            gbc.gridx = 2;
            gbc.gridy = 0;
            panel.add(new JLabel("                         "),gbc);
            gbc.gridx = 3;
            gbc.gridy = 0;
            panel.add(new JLabel("                         "),gbc);

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth= 1;
            panel.add(new JLabel("Username: "),gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 3;
            gbc.ipady = 30;
            usernameField = new JTextField();
            usernameField.setBorder(new LineBorder(Color.BLACK,3));
            panel.add(usernameField,gbc);

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth= 1;
            panel.add(new JLabel("Password: "),gbc);
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 3;
            gbc.ipady = 30;
            passwordField = new JTextField();
            passwordField.setBorder(new LineBorder(Color.BLACK,3));
            panel.add(passwordField,gbc);

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth= 1;
            panel.add(new JLabel("Repeat password: "),gbc);
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.gridwidth = 3;
            gbc.ipady = 30;
            passwordAgainField = new JTextField();
            passwordAgainField.setBorder(new LineBorder(Color.BLACK,3));
            panel.add(passwordAgainField,gbc);

            int errorsNum = errors.size();
            for (int i=0; i<=errorsNum; i++) {
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridx = 1;
                gbc.gridy = 4+i;
                gbc.ipady = 5;
                gbc.gridwidth = 2;
                try {
                    JLabel label = new JLabel(STR."*\{errors.get(i)}");
                    label.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,12));
                    panel.add(label, gbc);
                }
                catch (Exception e){
                    panel.add(new JLabel(""), gbc);
                }

            }

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 1;
            gbc.gridy = errorsNum+5;
            gbc.ipady = 15;
            gbc.gridwidth= 3;
            ImageIcon imageIcon = new ImageIcon("kindpng_3112748.png");
            Image image = imageIcon.getImage().getScaledInstance(150,50,0);
            registerButton = new JButton(new ImageIcon());
            registerButton.setIcon(new ImageIcon(image));
            registerButton.setBackground(Color.GREEN);
            registerButton.setBorderPainted(false);
            registerButton.addActionListener(this);
            panel.add(registerButton,gbc);

            return panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(registerButton)){
                errors = Validators.passwordValidator(passwordField.getText());
                if (!passwordField.getText().equals(passwordAgainField.getText())){
                    errors.add("You have repeated your password wrongly");
                }
                if (errors.isEmpty()) {
                    //TODO
                } else {
                    new RegPanel(p,errors);
                }
            }
            else if(e.getSource().equals(backButton)){
                p.removeAll();
                p.add(new SignRegPanel());
                p.repaint();
                p.revalidate();
            }
        }
    }

