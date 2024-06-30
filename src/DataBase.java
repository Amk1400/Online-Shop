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

public abstract class DataBase {

    public static ArrayList<User> users;
    public static ArrayList<String> userNames;
    public static ArrayList<Product> products;
    public static ArrayList<History> histories;

    private static final String SQL_USERS = "select * from USERS";
    private static final String SQL_PRODUCTS = "select * from PRODUCTS";
    private static final String SQL_HISTORY = "select * from HISTORY";

    private static final String HOST = "jdbc:derby://localhost:1527/Shop";
    private static final String USERNAME = "shopadmin";
    private static final String PASSWORD = "shopadmin";

    private static Statement STMT;
    private static ResultSet rs;
    static PreparedStatement ps;
    static Connection con;

    public static void main() throws SQLException {
        try {
            con = DriverManager.getConnection(HOST, USERNAME, PASSWORD );
            STMT = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        fetchDB();
    }

    protected static void fetchDB() throws SQLException {
        users       = getUsers();
        products    = getProducts();
        userNames   = new ArrayList<>();
        for (User user : users){
            userNames.add(user.userName);
        }
        histories = getHistory();
    }

    private static ArrayList<Product> getProducts() throws SQLException {
        ArrayList<Product> returned = new ArrayList<>();
        rs = STMT.executeQuery(SQL_PRODUCTS);

        while(rs.next()){
            String name = rs.getString("NAME");
            int stock = rs.getInt("STOCK");
            double price = rs.getDouble("PRICE");
            Blob blob = rs.getBlob("IMAGE");
            double point = rs.getDouble("POINT");
            String votedUsers = rs.getString("VOTEDUSERS");
            InputStream in = blob.getBinaryStream();
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(in);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            returned.add(new Product(name,stock,price,imageIcon,point,votedUsers));
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
            double wallet = rs.getDouble("WALLET");
            returned.add(new User(userName,password,address,phoneNumber,wallet));
        }

        return returned;
    }

    private static ArrayList<History> getHistory() throws SQLException {
        ArrayList<History> returned = new ArrayList<>();
        rs = STMT.executeQuery(SQL_HISTORY);

        while(rs.next()){
            String id = rs.getString("ID");
            String date = rs.getString("DATE");
            String owner = rs.getString("OWNER");
            String products = rs.getString("PRODUCTS");
            double cost = rs.getDouble("COST");
            returned.add(new History(date,owner,products,cost,id));
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

    public static void insertHistory(History history) throws SQLException, IOException {
        rs = STMT.executeQuery(SQL_HISTORY);
        rs.moveToInsertRow();
        rs.updateString(1, String.valueOf(history.Id));
        rs.updateString(2, history.date);
        rs.updateString(3, history.owner);
        rs.updateString(4, history.products);
        rs.updateDouble(5, history.cost);

        rs.insertRow();
        rs.close();
        rs = STMT.executeQuery(SQL_USERS);
        histories.add(history);
    }

    public static void updateUserWallet(User user, double money) throws SQLException {
        rs = STMT.executeQuery("select * from USERS Where Username like '%" + user.userName + "%'");
        rs.next();
        rs.updateDouble(5,(user.wallet) + money);
        rs.updateRow();
        rs.close();
    }

    public static void updateUser(User user, String userName) throws SQLException {
        rs = STMT.executeQuery("select * from USERS Where Username like '%" + userName + "%'");
        rs.next();
        rs.updateString(1,user.userName);
        rs.updateString(2, String.valueOf(user.password.hashCode()));
        rs.updateString(3,user.phoneNumber);
        rs.updateString(4,user.address);
        rs.updateRow();
        rs.close();
    }

    public static void updateProductStock(Product product, int number) throws SQLException {
        rs = STMT.executeQuery("select * from Products Where Name like '%" + product.name + "%'");
        rs.next();
        rs.updateDouble(2,(product.stock)-number);
        rs.updateRow();
        rs.close();
    }

    public static void updateProductPoint(String name, double newPoint) throws SQLException {
        rs = STMT.executeQuery("select * from Products Where Name like '%" + name + "%'");
        rs.next();
        rs.updateDouble(5,newPoint);
        rs.updateRow();
        rs.close();
    }

    public static void updateProductVotedUsers(String name, String newVoted) throws SQLException {
        rs = STMT.executeQuery("select * from Products Where Name like '%" + name + "%'");
        rs.next();
        rs.updateString(6,newVoted);
        rs.updateRow();
        rs.close();
    }

    public static void insertProductToDB(String imagePath, String name, String stock, String price, double point, String votedusers) throws SQLException, IOException {
        ps = con.prepareStatement("insert into Products values(?,?,?,?,?,?)");

        ps.setString(1, name);
        ps.setInt(2, Integer.parseInt(stock));
        ps.setDouble(3, Double.parseDouble(price));
        FileInputStream fin = new FileInputStream(imagePath);
        ps.setBinaryStream(4, fin, fin.available());
        ps.setDouble(5,point);
        ps.setString(6,votedusers);
        ps.executeUpdate();
        ps.close();
    }

    public static void updateProductsTable(String name, String newName, String newStock, String newPrice, Double newPoint, String newVotedUsers) throws SQLException, FileNotFoundException {
        ps = con.prepareStatement("UPDATE PRODUCTS SET NAME=?,STOCK=?,PRICE=?,POINT=?,VOTEDUSERS=? WHERE NAME=?");

        ps.setString(1,newName);
        ps.setInt(2, Integer.parseInt(newStock));
        ps.setDouble(3, Double.parseDouble((newPrice)));
        ps.setDouble(4,newPoint);
        ps.setString(5,newVotedUsers);
        ps.setString(6,name);

        ps.executeUpdate();
        ps.close();
    }

    public static void removeProductsTable(String name) throws SQLException, FileNotFoundException {
        ps = con.prepareStatement("DELETE FROM PRODUCTS WHERE NAME = ?");

        ps.setString(1,name);
        ps.executeUpdate();
        ps.close();
    }

    public static double getWallet(String username) throws SQLException {
        rs = STMT.executeQuery("select * from USERS Where Username like '%" + username + "%'");
        rs.next();
        return rs.getDouble("WALLET");
    }

    public static String getPhone(String username) throws SQLException {
        rs = STMT.executeQuery("select * from USERS Where Username like '%" + username + "%'");
        rs.next();
        return rs.getString("PHONENUMBER");
    }

    public static String getAddress(String username) throws SQLException {
        rs = STMT.executeQuery("select * from USERS Where Username like '%" + username + "%'");
        rs.next();
        return rs.getString("ADDRESS");
    }

    public static double getPoint(String name) throws SQLException {
        rs = STMT.executeQuery("select * from PRODUCTS Where Name like '%" + name + "%'");
        rs.next();
        return rs.getDouble("POINT");
    }

    public static int assignId() throws SQLException {
        rs = STMT.executeQuery(SQL_HISTORY);
        rs.last();
        return rs.getRow()+1;
    }
}
