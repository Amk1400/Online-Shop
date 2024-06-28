import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class DataBase {

    public static ArrayList<User> users;
    public static ArrayList<String> userNames;
    public static ArrayList<Product> products;
    private static final String SQL_USERS = "select * from USERS";
    private static final String SQL_PRODUCTS = "select * from PRODUCTS";
    private static final String HOST = "jdbc:derby://localhost:1527/Shop";
    private static final String USERNAME = "shopadmin";
    private static final String PASSWORD = "shopadmin";
    private static Statement STMT;
    private static ResultSet rs;
    static PreparedStatement ps;

    public static void main() throws SQLException {
        try {
            Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD );
            STMT = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        fetchDB();
    }

    private static void fetchDB() throws SQLException {
        users       = getUsers();
        products    = getProducts();
        userNames   = new ArrayList<>();
        for (User user : users){
            userNames.add(user.userName);
        }
    }

    private static ArrayList<Product> getProducts() throws SQLException {
        ArrayList<Product> returned = new ArrayList<>();
        rs = STMT.executeQuery(SQL_PRODUCTS);

        while(rs.next()){
            String name = rs.getString("NAME");
            int stock = rs.getInt("STOCK");
            Double price = rs.getDouble("PRICE");
            Blob blob = rs.getBlob("IMAGE");
            InputStream in = blob.getBinaryStream();
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(in);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            returned.add(new Product(name,stock,price,imageIcon));
        }

        return returned;
    }

    private static ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> returned = new ArrayList<>();
        rs = STMT.executeQuery(SQL_USERS);

        while(rs.next()){
            String userName = rs.getString("USERNAME");
            String password = rs.getString("PASSWORD");
            String address = rs.getString("ADDRESS");
            String phoneNumber = rs.getString("PHONENUMBER");

            returned.add(new User(userName,password,address,phoneNumber));
        }

        return returned;
    }


    public static void insertUser(User user) throws SQLException, IOException {
        rs = STMT.executeQuery(SQL_USERS);
        rs.moveToInsertRow();
        rs.updateString(1, user.userName);
        rs.updateString(2, user.password);
        rs.updateString(3, user.phoneNumber);
        rs.updateString(4, user.address);
        rs.updateDouble(5, user.wallet);

        rs.insertRow();
        rs.close();
        rs = STMT.executeQuery(SQL_USERS);
        users.add(user);
        userNames.add(user.userName);
        System.out.println(Arrays.toString(users.toArray()));
    }

    public static void updateUserWallet(User user, double money) throws SQLException {
        rs = STMT.executeQuery("select * from USERS Where Username ='%" + user.userName + "%'");
        rs.first();
        rs.updateDouble(5,(user.wallet)+money);
        rs.updateRow();
        rs.close();
    }

    public static void updateProductStock(Product product, int number) throws SQLException {
        rs = STMT.executeQuery("select * from Products Where Name ='%" + product.name + "%'");
        rs.first();
        rs.updateDouble(2,(product.stock)-number);
        rs.updateRow();
        rs.close();
    }

    public static void insertProduct(Product product) throws SQLException {
        rs = STMT.executeQuery(SQL_PRODUCTS);
        rs.moveToInsertRow();
        rs.updateString("NAME", product.name);
        rs.updateInt("STOCK", product.stock);
        rs.updateDouble("PRICE", product.price);
        rs.updateBlob("IMAGE",(Blob) product.imageIcon);

        rs.insertRow();
        rs.close();
        rs = STMT.executeQuery(SQL_PRODUCTS);
        products.add(product);
    }

    public static void fillProductsTable(String imagePath, String name, String stock, String price) throws SQLException, IOException {
        String host = "jdbc:derby://localhost:1527/Shop";
        String username="shopadmin", password="shopadmin";
        Connection con = DriverManager.getConnection( host, username, password );
        ps = con.prepareStatement("insert into Products values(?,?,?,?)");

        ps.setString(1, name);
        ps.setInt(2, Integer.parseInt(stock));
        ps.setDouble(3, Double.parseDouble(price));
        FileInputStream fin = new FileInputStream(imagePath);
        ps.setBinaryStream(4, fin, fin.available());
        ps.executeUpdate();
        ps.close();
    }

    public static void updateProductsTable(String name, String newName, String newStock, String newPrice) throws SQLException, FileNotFoundException {
        String host = "jdbc:derby://localhost:1527/Shop";
        String username="shopadmin", password="shopadmin";
        Connection con = DriverManager.getConnection( host, username, password );
        ps = con.prepareStatement("UPDATE PRODUCTS SET NAME=?,STOCK=?,PRICE=? WHERE NAME=?");

        ps.setString(1,newName);
        ps.setInt(2, Integer.parseInt(newStock));
        ps.setDouble(3, Double.parseDouble((newPrice)));
        ps.setString(4,name);
        ps.executeUpdate();
        ps.close();
    }

    public static void removeProductsTable(String name) throws SQLException, FileNotFoundException {
        String host = "jdbc:derby://localhost:1527/Shop";
        String username="shopadmin", password="shopadmin";
        Connection con = DriverManager.getConnection( host, username, password );
        ps = con.prepareStatement("DELETE FROM PRODUCTS WHERE NAME = ?");

        ps.setString(1,name);
        ps.executeUpdate();
        ps.close();
    }
}
