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
    JButton registerButton;
    JButton backButton;
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
        createBodyPanel(lastPanel);

        this.repaint();
        this.revalidate();
    }

    private void createBackButton() {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("pictures\\backButton.png"));
            ImageIcon imageIcon = new ImageIcon(bufferedImage.getScaledInstance(50, 50, 0));
            backButton = new JButton(imageIcon);
            backButton.setBackground(Color.pink);
            backButton.setBorderPainted(false);
        } catch (IOException e) {
            backButton = new JButton("Back");
            throw new RuntimeException("couldn't find backButton icon in: pictures\\backButton.png");
        }
        backButton.addActionListener(this);
    }

    private void createHeaderPanel() {
        headerPanel = new JPanel();
        this.add(headerPanel,BorderLayout.NORTH);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(Color.pink);

        createBackButton();
        headerPanel.add(backButton,BorderLayout.WEST);
    }

    private void createBodyPanel(JPanel lastPanel) {
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
        ImageIcon imageIcon = new ImageIcon("pictures\\kindpng_3112748.png");
        Image image = imageIcon.getImage().getScaledInstance(150,50,0);
        registerButton = new JButton(new ImageIcon());
        registerButton.setIcon(new ImageIcon(image));
        registerButton.setBackground(Color.pink);
        registerButton.setBorderPainted(false);
        registerButton.addActionListener(this);
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

