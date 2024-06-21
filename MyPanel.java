import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class MyPanel extends JPanel implements ActionListener {

    JPanel lastPanel, headerPanel, bodyPanel;

    JTextField usernameField, passwordField;
    JButton backButton = new JButton();
    static GridBagConstraints gridConstraints = new GridBagConstraints();

    public MyPanel(JPanel lastPanel){
        super();
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

    protected void createBodyPanel() {
        bodyPanel = new JPanel(new GridBagLayout());
        bodyPanel.setSize(500,600);
        bodyPanel.setBackground(Color.pink);
        gridConstraints.insets = new Insets(5,0,5,0);
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;

        fillBlankRowOf(0,bodyPanel);
        createUserNameSection();
        createPasswordSection();
        pointToButtonInset();
    }

    private static void pointToButtonInset() {
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 5;
        gridConstraints.ipady = 15;
        gridConstraints.gridwidth= 3;
    }

    private void createPasswordSection() {
        gridConstraints.gridy = 2;
        gridConstraints.gridx = 0;
        gridConstraints.gridwidth = 1;
        bodyPanel.add(new JLabel("Password: "), gridConstraints);
        gridConstraints.gridx = 1;
        gridConstraints.gridwidth = 3;
        gridConstraints.ipady = 30;
        passwordField = new JTextField();
        passwordField.setBorder(new LineBorder(Color.BLACK,3));
        bodyPanel.add(passwordField, gridConstraints);
    }

    private void createUserNameSection() {
        gridConstraints.gridy = 1;
        gridConstraints.gridx = 0;
        gridConstraints.gridwidth= 1;
        bodyPanel.add(new JLabel("Username: "), gridConstraints);
        gridConstraints.gridx = 1;
        gridConstraints.gridwidth = 3;
        gridConstraints.ipady = 30;
        usernameField = new JTextField();
        usernameField.setBorder(new LineBorder(Color.BLACK,3));
        bodyPanel.add(usernameField, gridConstraints);
    }

    private static void fillBlankRowOf(int rowNumber, JPanel bodyPanel) {
        for (int j = 0; j < 4; j++) {
            gridConstraints.gridy = rowNumber;
            gridConstraints.gridx = j;
            bodyPanel.add(new JLabel("                         "), gridConstraints);
        }
    }

    private void createHeaderPanel() {
        headerPanel = new JPanel();
        this.add(headerPanel,BorderLayout.NORTH);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(Color.pink);

        createButton(backButton,"pictures\\backButton.png",50,50, "Back");
        headerPanel.add(backButton,BorderLayout.WEST);
    }

    void createButton(JButton button, String path, int width, int height, String name) {
        ImageIcon imageIcon;

        try {
            BufferedImage bufferedImage = ImageIO.read(new File(path));
            imageIcon = new ImageIcon(bufferedImage.getScaledInstance(width, height, 0));
        } catch (IOException e) {
            throw new RuntimeException("couldn't find " + name + "Button icon in: " + path);
        }

        try {
            button.setIcon(imageIcon);
            button.setBackground(Color.pink);
            button.setBorderPainted(false);
        } catch (NullPointerException e) {
            throw new RuntimeException();//TODO
        }
        button.addActionListener(this);
    }
}
