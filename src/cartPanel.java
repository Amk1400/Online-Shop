import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class cartPanel extends AfterLoginPanel implements ActionListener {

    JPanel productsPanel;
    JPanel payPanel;
    JButton productButton;
    JButton payButton;
    JPanel[][] panelHolder;
    JLabel sumCost;
    HashMap<Product, Integer> userCart;
    ArrayList<Product> products;
    ArrayList<Product> searchedProducts;
    int maxPageNumber = maxPageNumber();
    int pageNumber = 1;

    public cartPanel(JPanel lastPanel) throws SQLException, IOException {
        super(lastPanel);
    }

    protected void createFooterPanel() {
        super.createFooterPanel();

        payPanel = new JPanel();
        payPanel.setBackground(Color.pink);
        payPanel.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setBackground(Color.pink);
        panel.setLayout(new FlowLayout());
        sumCost = new JLabel(String.valueOf(calculateCost()));
        payButton = new JButton("Pay");
        payButton.setSize(100,20);
        payPanel.add(panel,BorderLayout.CENTER);
        panel.add(payButton,BorderLayout.EAST);
        panel.add(sumCost,BorderLayout.WEST);
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

        User user = Main.PROFILE_PANEL.currentUser;
        userCart = user.cart;
        products = new ArrayList<>(userCart.keySet());
        searchedProducts = new ArrayList<>(userCart.keySet());
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

        int i = start;

        for(int a=0; a<2; a++) {
            for (int b = 0; b < 4; b++) {
                if(i < products.size()) {
                    Product product = products.get(i);
                    panelHolder[a][b].removeAll();
                    panelHolder[a][b].add(createProduct(product.imageIcon, product.name, userCart.get(product), product.price));
                    panelHolder[a][b].repaint();
                    panelHolder[a][b].revalidate();
                }
                else {
                    panelHolder[a][b].removeAll();
                    panelHolder[a][b].repaint();
                    panelHolder[a][b].revalidate();
                }
                i++;
            }
        }
    }

    private int maxPageNumber() throws SQLException {
        return (products.size()/8) + 1;
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
            double temp;
            int index;
            for(int i=0; i<products.size(); i++){
                temp=products.get(i).price;
                index=i;
                for (int j=i; j<products.size(); j++){
                    if(products.get(j).price < temp){
                        temp = products.get(j).price;
                        index=j;
                    }
                }
                Product product = products.get(i);
                products.set(i,products.get(index));
                products.set(index,product);
            }
            try {
                fillProducts((pageNumber-1)*8);
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource().equals(name)){
            String temp;
            int index;
            for(int i=0; i<products.size(); i++){
                temp=products.get(i).name;
                index=i;
                for (int j=i; j<products.size(); j++){
                    if(products.get(j).name.compareTo(temp) < 0){
                        temp = products.get(j).name;
                        index=j;
                    }
                }
                Product product = products.get(i);
                products.set(i,products.get(index));
                products.set(index,product);
                try {
                    fillProducts((pageNumber-1)*8);
                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(e.getSource().equals(searchButton)){
            if(searchField.getText().isEmpty()){
                products = searchedProducts;
            }
            else {
                products.clear();
                String key = searchField.getText();
                for(Product product : searchedProducts){
                    if(product.name.contains(key)){
                        products.add(product);
                    }
                }
            }
            try {
                fillProducts((pageNumber-1)*8);
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource().equals(payButton)){
            User user = Main.PROFILE_PANEL.currentUser;
            if(user.wallet >= calculateCost()){
                try {
                    user.deposit(-calculateCost());
                    for(Product product : userCart.keySet()){
                        DataBase.updateProductStock(product,userCart.get(product));
                    }
                    user.cart.clear();
                    userCart.clear();
                    products.clear();
                    searchedProducts.clear();
                    sumCost.setText("0");
                    fillProducts(0);
                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else {
                sumCost.setText("Not enough money     "+calculateCost());
            }
        }

    }

    private double calculateCost(){
        double sumCost=0;
        for(Product product : userCart.keySet()){
            sumCost += (product.price)*(userCart.get(product));
        }
        return sumCost;
    }

}
