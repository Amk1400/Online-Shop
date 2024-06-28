import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class CreateTabels {
    static PreparedStatement ps;
    static ResultSet rs;

    public static void main(String args[]) throws SQLException, IOException {

    }

    void CreateProductsTable(){
        //TODO
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
