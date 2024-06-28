import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public abstract class DataBase {

    public static ArrayList<User> users;
    public static ArrayList<Product> products;
    private static final String SQL_USERS = "select * from USERS";
    private static final String SQL_PRODUCTS = "select * from PRODUCTS";
    private static final String HOST = "jdbc:derby://localhost:1527/Shop";
    private static final String USERNAME = "shopadmin";
    private static final String PASSWORD = "shopadmin";
    private static Statement STMT;
    private static ResultSet rs;

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

        return returned;//TODO
    }

    private static ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> returned = new ArrayList<>();
        rs = STMT.executeQuery(SQL_USERS);

        while(rs.next()){
            String userName = rs.getString("USERNAME");
            String password = rs.getString("PASSWORD");
            returned.add(new User(userName,password));
        }

        return returned;//TODO
    }


    public static void insertUser(User user) throws SQLException {
        rs.moveToInsertRow();
        rs.updateString("USERNAME", user.userName);
        rs.updateString("PASSWORD", user.userName);

        rs.insertRow();
        rs.close();
        rs = STMT.executeQuery(SQL_USERS);
    }

    public static void insertProduct(Product product) throws SQLException {
        rs.moveToInsertRow();
        rs.updateString("NAME", product.name);
        rs.updateInt("STOCK", product.stock);
        rs.updateDouble("PRICE", product.price);
        rs.updateBlob("IMAGE",(Blob) product.imageIcon);

        rs.insertRow();
        rs.close();
        rs = STMT.executeQuery(SQL_PRODUCTS);
    }
}
