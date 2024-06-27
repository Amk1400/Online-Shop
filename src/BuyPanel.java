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

public class BuyPanel extends AfterLoginPanel implements ActionListener {

    JPanel productsPanel;
    JButton productButton;
    JPanel[][] panelHolder;
    int pageNumber = 1;
    int maxPageNumber = maxPageNumber();
    static Statement statement;
    static {
        try {
            statement = connectDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    static ResultSet rs;
    static {
        try {
            rs = statement.executeQuery("select * from Products");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public BuyPanel(JPanel lastPanel) throws SQLException {
        super(lastPanel);
    }

    protected void createBodyPanel() throws SQLException {
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
        JLabel countLabel = new JLabel("0",SwingConstants.CENTER);

        plusButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int number = Integer.parseInt(countLabel.getText());
                if(number+1 <= stock) {
                    number++;
                    countLabel.setText(String.valueOf(number));
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
}