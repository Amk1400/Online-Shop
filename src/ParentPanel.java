import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;

public abstract class ParentPanel extends JPanel implements ActionListener {

    JPanel lastPanel, headerPanel, bodyPanel, footerPanel;
    JButton backButton = new JButton();
    JButton cartButton = new JButton();
    JButton profileButton = new JButton();
    JButton nextPageButton = new JButton();
    JButton prevPageButton = new JButton();
    static GridBagConstraints gridConstraints = new GridBagConstraints();

    public ParentPanel(JPanel lastPanel) throws SQLException, IOException {
        super();
        this.lastPanel = lastPanel;
        createThis();
    }

    private void createThis() throws SQLException, IOException {
        this.setSize(1000,700);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.pink);

        createHeaderPanel();
        createBodyPanel();
        createFooterPanel();

        this.repaint();
        this.revalidate();
    }

    protected void createBodyPanel() throws SQLException, IOException {
        bodyPanel = new JPanel();
        bodyPanel.setSize(500,600);
        bodyPanel.setBackground(Color.pink);
        bodyPanel.setLayout(new GridBagLayout());
    }

    protected void createHeaderPanel() throws SQLException {
        headerPanel = new JPanel();
        this.add(headerPanel,BorderLayout.NORTH);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(Color.pink);

        createButton(backButton,"pictures\\backButton.png",40,40, "Back");
        headerPanel.add(backButton,BorderLayout.WEST);
    }
    protected void createFooterPanel(){
        footerPanel = new JPanel();
        this.add(footerPanel,BorderLayout.SOUTH);
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setBackground(Color.pink);
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
