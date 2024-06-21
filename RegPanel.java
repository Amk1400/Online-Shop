import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RegPanel extends JPanel implements ActionListener{
    JTextField passwordAgainField;
    JTextField passwordField;
    JTextField usernameField;
    JButton registerButton = new JButton();
    JButton backButton = new JButton();
    JPanel headerPanel;
    JPanel lastPanel;
    ArrayList<String> errors = new ArrayList<>();

    public RegPanel(JPanel lastPanel){
        super();
        this.lastPanel = lastPanel;
        createThis();
    }

    RegPanel(JPanel lastPanel, ArrayList<String> errors){
        this.errors = errors;
        this.lastPanel = lastPanel;
        createThis();
    }

    private void createThis(){
        this.setSize(1000,700);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.pink);

        createHeaderPanel();
        createBodyPanel();

        this.repaint();
        this.revalidate();
    }

    private void createButton(JButton button, String path, int width, int height, String name) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(path));
            ImageIcon imageIcon = new ImageIcon(bufferedImage.getScaledInstance(width, height, 0));
            button.setIcon(imageIcon);
            button.setBackground(Color.pink);
            button.setBorderPainted(false);
        } catch (IOException e) {
            button.setName(name);
            throw new RuntimeException("couldn't find " + name + " icon in: " + path);
        }
        button.addActionListener(this);
    }

    private void createHeaderPanel() {
        headerPanel = new JPanel();
        this.add(headerPanel,BorderLayout.NORTH);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(Color.pink);

        createButton(backButton,"pictures\\backButton.png",50,50, "Back");
        headerPanel.add(backButton,BorderLayout.WEST);
    }

    private void createBodyPanel() {
        JPanel bodyPanel = new JPanel();
        bodyPanel.setSize(500,600);
        bodyPanel.setLayout(new GridBagLayout());
        bodyPanel.setBackground(Color.pink);
        GridBagConstraints gbcLayout = new GridBagConstraints();
        gbcLayout.insets = new Insets(5,0,5,0);
        gbcLayout.fill = GridBagConstraints.HORIZONTAL;
        gbcLayout.gridx = 0;
        gbcLayout.gridy = 0;
        bodyPanel.add(new JLabel("                         "),gbcLayout);
        gbcLayout.gridx = 1;
        gbcLayout.gridy = 0;
        bodyPanel.add(new JLabel("                         "),gbcLayout);
        gbcLayout.gridx = 2;
        gbcLayout.gridy = 0;
        bodyPanel.add(new JLabel("                         "),gbcLayout);
        gbcLayout.gridx = 3;
        gbcLayout.gridy = 0;
        bodyPanel.add(new JLabel("                         "),gbcLayout);
        gbcLayout.fill = GridBagConstraints.HORIZONTAL;
        gbcLayout.gridx = 0;
        gbcLayout.gridy = 1;
        gbcLayout.gridwidth= 1;
        bodyPanel.add(new JLabel("Username: "),gbcLayout);
        gbcLayout.gridx = 1;
        gbcLayout.gridy = 1;
        gbcLayout.gridwidth = 3;
        gbcLayout.ipady = 30;
        usernameField = new JTextField();
        usernameField.setBorder(new LineBorder(Color.BLACK,3));
        bodyPanel.add(usernameField,gbcLayout);
        gbcLayout.fill = GridBagConstraints.HORIZONTAL;
        gbcLayout.gridx = 0;
        gbcLayout.gridy = 2;
        gbcLayout.gridwidth= 1;
        bodyPanel.add(new JLabel("Password: "),gbcLayout);
        gbcLayout.gridx = 1;
        gbcLayout.gridy = 2;
        gbcLayout.gridwidth = 3;
        gbcLayout.ipady = 30;
        passwordField = new JTextField();
        passwordField.setBorder(new LineBorder(Color.BLACK,3));
        bodyPanel.add(passwordField,gbcLayout);
        gbcLayout.fill = GridBagConstraints.HORIZONTAL;
        gbcLayout.gridx = 0;
        gbcLayout.gridy = 3;
        gbcLayout.gridwidth= 1;
        bodyPanel.add(new JLabel("Repeat password: "),gbcLayout);
        gbcLayout.gridx = 1;
        gbcLayout.gridy = 3;
        gbcLayout.gridwidth = 3;
        gbcLayout.ipady = 30;
        passwordAgainField = new JTextField();
        passwordAgainField.setBorder(new LineBorder(Color.BLACK,3));
        bodyPanel.add(passwordAgainField,gbcLayout);
        int errorsNum = errors.size();
        for (int i=0; i<=errorsNum; i++) {
            gbcLayout.fill = GridBagConstraints.HORIZONTAL;
            gbcLayout.gridx = 1;
            gbcLayout.gridy = 4+i;
            gbcLayout.ipady = 5;
            gbcLayout.gridwidth = 2;
            try {/*     commented due to error of STR
                   JLabel label = new JLabel(STR."*\{errors.get(i)}");
                   label.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,12));
                   panel.add(label, gbc);*/
            }
            catch (Exception e){
                bodyPanel.add(new JLabel(""), gbcLayout);
            }
        }
        gbcLayout.fill = GridBagConstraints.HORIZONTAL;
        gbcLayout.gridx = 1;
        gbcLayout.gridy = errorsNum+5;
        gbcLayout.ipady = 15;
        gbcLayout.gridwidth= 3;
        createButton(registerButton,"pictures\\regButton.png",150,50, "Register");
        bodyPanel.add(registerButton,gbcLayout);
        this.add(bodyPanel,BorderLayout.CENTER);
    }

        @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(registerButton)){

            assignErrors();

            if (errors.isEmpty()) {
                //new buyPanel(lastPanel);
                //TODO
            } else {
                //new RegPanel(lastPanel,errors);
                //TODO
            }

        }
        else if(e.getSource().equals(backButton)){
            Main.setCurrentPanel(lastPanel);
        }
    }

    private void assignErrors() {
        errors = Validators.passwordValidator(passwordField.getText());
        if (!repeatedPassMatches()){
            errors.add("You have repeated your password wrongly");
        }
    }

    private boolean repeatedPassMatches() {
        String inputPass = passwordField.getText();
        String repeatPass = passwordAgainField.getText();
        return inputPass.equals(repeatPass);
    }
}

