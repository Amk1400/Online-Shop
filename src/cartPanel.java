import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class cartPanel extends AfterLoginPanel{

    JPanel productsPanel;
    JPanel payPanel;
    JButton productButton;
    JPanel[][] panelHolder;
    HashMap<Product, Integer> userCart;
    int maxPageNumber;
    int pageNumber = 1;

    public cartPanel(JPanel lastPanel) throws SQLException, IOException {
        super(lastPanel);
    }

    protected void createFooterPanel() {
        super.createFooterPanel();

        payPanel = new JPanel();
        payPanel.setBackground(Color.pink);
        payPanel.setLayout(new BorderLayout());
        JLabel sumCost = new JLabel("//TODO");
        JButton payButton = new JButton("Pay");
        payPanel.add(sumCost,BorderLayout.EAST);
        payPanel.add(sumCost,BorderLayout.WEST);
        footerPanel.add(payPanel,BorderLayout.CENTER);
    }

    protected void createBodyPanel() throws SQLException, IOException {
        super.createBodyPanel();
        bodyPanel.setLayout(new BorderLayout());

        productsPanel = new JPanel();
        productsPanel.setLayout(new GridLayout(2,4));
        productsPanel.setBackground(Color.pink);
        bodyPanel.add(productsPanel,BorderLayout.CENTER);

        int i = 2;
        int j = 4;
        panelHolder = new JPanel[i][j];
        for(int m = 0; m < i; m++) {
            for(int n = 0; n < j; n++) {
                JPanel panel = new JPanel();
                panel.setBackground(Color.pink);
                panelHolder[m][n] = panel;
                productsPanel.add(panelHolder[m][n]);
            }
        }

        fillProducts(0);

        this.add(bodyPanel,BorderLayout.CENTER);
    }

    public JPanel createProduct(ImageIcon imageIcon, String name, int number , double price) throws SQLException, IOException {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.pink);
        panel.setBorder(new LineBorder(Color.LIGHT_GRAY,1));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 5;

        Image image = imageIcon.getImage().getScaledInstance(100,120,0);
        productButton  = new JButton(new ImageIcon());
        productButton.setIcon(new ImageIcon(image));
        productButton.setBackground(Color.pink);
        productButton.setBorderPainted(false);
        productButton.addActionListener(this);
        panel.add(productButton,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel nameLabel = new JLabel(name,SwingConstants.CENTER);
        nameLabel.setBorder(new LineBorder(Color.LIGHT_GRAY,1));
        nameLabel.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,12));
        panel.add(nameLabel,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel priceLabel = new JLabel(String.valueOf(price),SwingConstants.CENTER);
        priceLabel.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,12));
        priceLabel.setBorder(new LineBorder(Color.LIGHT_GRAY,1));
        panel.add(priceLabel,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        JPanel addPanel = new JPanel();
        addPanel.setBackground(Color.pink);
        addPanel.setLayout(new BorderLayout());
        JLabel numberLabel = new JLabel("x"+(number));
        JLabel costLabel = new JLabel(String.valueOf(number*price));

        //countLabel.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,12));
        addPanel.add(costLabel,BorderLayout.WEST);
        addPanel.add(numberLabel,BorderLayout.EAST);
        panel.add(addPanel,gbc);

        return panel;
    }

    protected void fillProducts(int start) throws SQLException, IOException {

        ArrayList<Product> products = new ArrayList<>(userCart.keySet());
        int i = start;

        for(int a=0; a<2; a++) {
            for (int b = 0; b < 4; b++) {
                if(i < userCart.size()) {
                    Product product = products.get(i);
                    panelHolder[a][b].removeAll();
                    panelHolder[a][b].add(createProduct(product.imageIcon, product.name, userCart.get(product), product.price));
                    panelHolder[a][b].repaint();
                    panelHolder[a][b].revalidate();
                }
            }
        }
    }

    private int maxPageNumber() throws SQLException {
        return (userCart.size()/8) + 1;
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(nextPageButton)){
            if(pageNumber+1 <= maxPageNumber) {
                pageNumber++;
            }
            try {
                fillProducts((pageNumber-1)*8);
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource().equals(prevPageButton)){
            if(pageNumber-1 >= 1) {
                pageNumber--;
            }
            try {
                fillProducts((pageNumber-1)*8);
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource().equals(price)){

        }
        else if(e.getSource().equals(name)){

        }
        else if(e.getSource().equals(searchButton)){

        }

    }

}
