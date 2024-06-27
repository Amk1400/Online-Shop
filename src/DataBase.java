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
    public DataBase(){
        users       = getUsers();
        products    = getProducts();

        try {
            Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD );
            Statement stmt = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<Product> getProducts() {
        ArrayList<Product> returned;
        return null;//TODO
    }

    private static ArrayList<User> getUsers() {
        ArrayList<Product> returned;
        return null;//TODO
    }

    private static void insertUser(User user){
            //TODO
    }

    private static void insertProduct(Product product){
            //TODO
    }
}
