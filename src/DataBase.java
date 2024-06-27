import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class DataBase {

    static ArrayList<User> users;
    static ArrayList<Product> products;
    static final String SQL_USERS = "select * from USERS";
    static final String SQL_PRODUCTS = "select * from PRODUCTS";
    static final String HOST = "jdbc:derby://localhost:1527/Shop";
    static final String USERNAME = "shopadmin";
    static final String PASSWORD = "shopadmin";
    static Statement STMT;
    static ResultSet rs;


    public DataBase() throws SQLException{
        try {
            Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD );
            Statement stmt = con.createStatement();
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
            ImageIcon image = (ImageIcon) rs.getBlob("IMAGE");
            returned.add(new Product(name,stock,price,image));
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


    private static void insertUser(User user) throws SQLException {
        rs.moveToInsertRow();
        rs.updateString("USERNAME", user.userName);
        rs.updateString("PASSWORD", user.userName);

        rs.insertRow();
        rs.close();
        rs = STMT.executeQuery(SQL_USERS);
    }

    private static void insertProduct(Product product) throws SQLException {
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
