import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

public abstract class ParentPanel extends JPanel implements ActionListener {

    JPanel lastPanel, headerPanel, bodyPanel;
    JButton backButton = new JButton();
    static GridBagConstraints gridConstraints = new GridBagConstraints();

    public ParentPanel(JPanel lastPanel){
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
        bodyPanel = new JPanel();
        bodyPanel.setSize(500,600);
        bodyPanel.setBackground(Color.pink);

        bodyPanel.setLayout(new GridBagLayout());
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
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
            throw new RuntimeException("Put yourButton = new JButton(); before calling this method to fix!");
        }
        button.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(backButton)){
            Main.setCurrentPanel(lastPanel);
        }
    }
}
