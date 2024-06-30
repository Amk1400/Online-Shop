import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class ManagerBuyPanel extends BuyPanel{

    JFileChooser fileChooser = new JFileChooser();
    File file;

    public ManagerBuyPanel(JPanel lastPanel) throws SQLException, IOException {
        super(lastPanel);
    }

    @Override
    protected void createBodyPanel() throws SQLException, IOException {
        super.createBodyPanel();
        fetchDBProducts();
    }

    @Override
    public JPanel createProduct(Blob blob, String name, int stock , double price, double point, String votedUsers) throws SQLException, IOException {
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
        JTextField nameField = new JTextField(name);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setBorder(new LineBorder(Color.LIGHT_GRAY,1));
        nameField.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,12));
        panel.add(nameField,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        JTextField priceField = new JTextField(String.valueOf(price));
        priceField.setHorizontalAlignment(JTextField.CENTER);
        priceField.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,12));
        priceField.setBorder(new LineBorder(Color.LIGHT_GRAY,1));
        panel.add(priceField,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new BorderLayout());
        JButton OkButton = new JButton();
        createButton(OkButton,"pictures\\tikButton.png",20,30,"Ok");
        JButton removeButton = new JButton();
        createButton(removeButton,"pictures\\crossButton.png",20,30,"remove");
        JTextField countField = new JTextField();
        countField.setText(String.valueOf(stock));
        countField.setHorizontalAlignment(JTextField.CENTER);
        countField.setBackground(Color.pink);
        countField.setBorder(null);

        removeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    DataBase.removeProductsTable(name);
                    updateQuery();
                } catch (SQLException | FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        OkButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    DataBase.updateProductsTable(name,nameField.getText(),countField.getText(),priceField.getText(),point,votedUsers);
                    updateQuery();
                } catch (SQLException | FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        countField.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,12));
        addPanel.add(OkButton,BorderLayout.WEST);
        addPanel.add(removeButton,BorderLayout.EAST);
        addPanel.add(countField,BorderLayout.CENTER);
        panel.add(addPanel,gbc);

        return panel;
    }

    @Override
    protected JPanel newProduct() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.pink);
        panel.setBorder(new LineBorder(Color.LIGHT_GRAY,1));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 5;

        productButton  = new JButton();
        createButton(productButton,"pictures\\addPicButton.png",100,120,"addPic");
        panel.add(productButton,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JTextField nameField = new JTextField();
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setBorder(new LineBorder(Color.LIGHT_GRAY,1));
        nameField.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,12));
        panel.add(nameField,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        JTextField priceField = new JTextField();
        priceField.setHorizontalAlignment(JTextField.CENTER);
        priceField.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,12));
        priceField.setBorder(new LineBorder(Color.LIGHT_GRAY,1));
        panel.add(priceField,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new FlowLayout());
        addPanel.setBackground(Color.pink);
        JButton OkButton = new JButton();
        createButton(OkButton,"pictures\\tikButton.png",20,30,"Ok");
        JTextField countField = new JTextField("0");
        countField.setHorizontalAlignment(JTextField.CENTER);
        countField.setBackground(Color.pink);
        countField.setBorder(null);
        addPanel.add(OkButton);
        addPanel.add(countField);
        panel.add(addPanel,gbc);

        productButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                chooseFileImage();
                if(file != null){
                    createButton(productButton,file.getPath(),100,120,"addPic");
                }
            }
        });

        OkButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    DataBase.fillProductsTable(file.getPath(),nameField.getText(),countField.getText(),priceField.getText(),0,"");
                    updateQuery();
                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        return panel;
    }

    public void chooseFileImage(){

        // create file chooser panel
        fileChooser.setAcceptAllFileFilterUsed(false);

        int option = fileChooser.showOpenDialog(ManagerBuyPanel.this);
        // exception handling for choosing file
        try {
            if (option == JFileChooser.APPROVE_OPTION) {
                File givenFile = fileChooser.getSelectedFile();
                // check the format of file to be image file
                String format = givenFile.getName().substring(givenFile.getName().lastIndexOf('.')+1);
                if(format.equals("jpg") || format.equals("png") || format.equals("jpeg")){
                    file = fileChooser.getSelectedFile();
                }
                else {
                    file = null;
                }
            }
        }
        catch (Exception e){
            System.out.println("No image selected!");
        }

    }

    protected void updateQuery() throws SQLException {
        rs = statement.executeQuery(sql);
        rs.absolute((pageNumber-1)*8);
        fillProducts();
    }

}
