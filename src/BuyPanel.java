import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BuyPanel extends AfterLoginPanel implements ActionListener {

    JPanel productsPanel;
    JButton productButton;
    JPanel[][] panelHolder;
    JPanel searchPanel;
    JTextField searchField;
    JButton searchButton;
    int pageNumber = 1;
    int maxPageNumber = maxPageNumber();
    Statement statement;
    ResultSet rs;
    HashMap<Product, Integer> userCart;


    public BuyPanel(JPanel lastPanel) throws SQLException, IOException {
        super(lastPanel);
        try {
            rs.close();
        } catch (NullPointerException e) {

        }
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
    }

    protected void fetchDBProducts() throws SQLException {
        statement = connectDB();
        rs = statement.executeQuery(sql);
        rs.beforeFirst();
        fillProducts();

        this.add(bodyPanel,BorderLayout.CENTER);
    }

    public JPanel createProduct(Blob blob, String name, int stock ,double price) throws SQLException, IOException {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.pink);
        panel.setBorder(new LineBorder(Color.LIGHT_GRAY,1));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 5;
        InputStream in = blob.getBinaryStream();
        BufferedImage bufferedImage = ImageIO.read(in);
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
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
        addPanel.setLayout(new BorderLayout());
        JButton minusButton = new JButton("-");
        JButton plusButton = new JButton("+");
        JLabel countLabel = new JLabel(String.valueOf(productNumberInCart(name)),SwingConstants.CENTER);

        plusButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int number = Integer.parseInt(countLabel.getText());
                if(number+1 <= stock) {
                    number++;
                    countLabel.setText(String.valueOf(number));
                    addInCart(new Product(name,stock,price,imageIcon));
                }
                else {
                    plusButton.setEnabled(false);
                }
            }
        });

        minusButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int number = Integer.parseInt(countLabel.getText());
                if(number-1 >= 0) {
                    number--;
                    countLabel.setText(String.valueOf(number));
                    plusButton.setEnabled(true);
                    removeFromCart(new Product(name,stock,price,imageIcon));
                }
            }
        });

        countLabel.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,12));
        addPanel.add(minusButton,BorderLayout.WEST);
        addPanel.add(plusButton,BorderLayout.EAST);
        addPanel.add(countLabel,BorderLayout.CENTER);
        panel.add(addPanel,gbc);

        return panel;
    }

    public static Statement connectDB() throws SQLException {
        String host = "jdbc:derby://localhost:1527/Shop";
        String username="shopadmin", password="shopadmin";
        Connection con = DriverManager.getConnection( host, username, password );
        return con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        try {
            rs = statement.executeQuery(sql);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        if(e.getSource().equals(nextPageButton)){
            if(pageNumber+1 <= maxPageNumber) {
                pageNumber++;
            }
            try {
                rs.absolute(((pageNumber-1)*8));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            fillProducts();
        }
        else if (e.getSource().equals(prevPageButton)){
            if(pageNumber-1 >= 1) {
                pageNumber--;
            }
            try {
                rs.absolute(((pageNumber-1)*8));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            fillProducts();
        }
        else if(e.getSource().equals(priceASC)){
            sql = "select * from Products Order by Price ASC";
            try {
                updateQuery();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource().equals(priceDESC)){
            sql = "select * from Products Order by Price DESC";
            try {
                updateQuery();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource().equals(nameA_Z)){
            sql = "select * from Products Order by Name ASC";
            try {
                updateQuery();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource().equals(nameZ_A)){
            sql = "select * from Products Order by Name DESC";
            try {
                updateQuery();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource().equals(searchButton)){
            sql = "select * from Products where Name like '%" + searchField.getText()+ "%'";
            try {
                updateQuery();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        try {
            rs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected void fillProducts(){
        for(int a=0; a<2; a++){
            for (int b=0; b<4; b++){
                try {
                    if(rs.next()) {
                        panelHolder[a][b].removeAll();
                        panelHolder[a][b].add(createProduct(rs.getBlob(4) , rs.getString(1), rs.getInt(2), rs.getDouble(3)));
                        panelHolder[a][b].repaint();
                        panelHolder[a][b].revalidate();
                    }
                    else {
                        panelHolder[a][b].removeAll();
                        panelHolder[a][b].add(newProduct());
                        panelHolder[a][b].repaint();
                        panelHolder[a][b].revalidate();
                    }
                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    protected JPanel newProduct() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.pink);
        return panel;
    }

    private int maxPageNumber() throws SQLException {
        Statement statement = connectDB();
        ResultSet resultSet = statement.executeQuery("select count(*) from Products");
        resultSet.next();
        int rowsNumber = resultSet.getInt(1);
        return (rowsNumber/8) + 1;
    }

    protected void updateQuery() throws SQLException {
        try {
            rs = statement.executeQuery(sql);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        try {
            rs.absolute((pageNumber-1)*8);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        fillProducts();
    }

    private int productNumberInCart(String name){
        for(Product product : userCart.keySet()){
            if(product.name.equals(name)){
                return userCart.get(product);
            }
        }
        return 0;
    }

    private void addInCart(Product p){
        try {
            for (Product product : userCart.keySet()) {
                if (product.name.equals(p.name)) {
                    userCart.replace(product, (userCart.get(product)) + 1);
                    return;
                }
            }
            userCart.put(p, 1);
        }
        catch (Exception e){
            userCart.put(p, 1);
        }
    }

    private void removeFromCart(Product p){
        for(Product product : userCart.keySet()){
            if(product.name.equals(p.name)){
                userCart.replace(product,(userCart.get(product))-1);
            }
        }
    }

    protected void createSearchSection() {
        searchPanel = new JPanel();
        searchPanel.setBackground(Color.pink);
        searchPanel.setLayout(new FlowLayout());
        headerCenterButtonsPanel.add(searchPanel,BorderLayout.EAST);
        searchButton = new JButton();
        createButton(searchButton,"pictures\\search.png",30,30,"search");
        searchField = new JTextField();
        searchField.setSize(100,20);
        searchField.setColumns(10);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
    }
}