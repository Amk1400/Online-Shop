import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegPanel implements ActionListener{
        JTextField passwordAgainField;
        JTextField passwordField;
        JTextField usernameField;
        JButton registerButton;

        public RegPanel(JPanel p){
            p.removeAll();

            p.setSize(1000,700);
            p.setLayout(new BorderLayout());
            p.setBackground(Color.GREEN);


            p.add(infoPanel(),BorderLayout.CENTER);


            p.repaint();
            p.revalidate();
            p.setVisible(true);
        }

        private JPanel infoPanel() {
            JPanel panel = new JPanel();
            panel.setSize(500,600);
            panel.setLayout(new GridBagLayout());
            panel.setBackground(Color.GREEN);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,0,5,0);

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(new JLabel("                              "),gbc);
            gbc.gridx = 1;
            gbc.gridy = 0;
            panel.add(new JLabel("                              "),gbc);
            gbc.gridx = 2;
            gbc.gridy = 0;
            panel.add(new JLabel("                              "),gbc);
            gbc.gridx = 3;
            gbc.gridy = 0;
            panel.add(new JLabel("                              "),gbc);

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth= 1;
            panel.add(new JLabel("username: "),gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 3;
            gbc.ipady = 30;
            usernameField = new JTextField();
            panel.add(usernameField,gbc);

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth= 1;
            panel.add(new JLabel("password: "),gbc);
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 3;
            gbc.ipady = 30;
            passwordField = new JTextField();
            panel.add(passwordField,gbc);

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth= 1;
            panel.add(new JLabel("password again: "),gbc);
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.gridwidth = 3;
            gbc.ipady = 30;
            passwordAgainField = new JTextField();
            panel.add(passwordAgainField,gbc);

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.ipady = 15;
            gbc.gridwidth= 2;
            panel.add(new JLabel(""),gbc);

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 1;
            gbc.gridy = 5;
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
                //TODO
            }
        }
    }

