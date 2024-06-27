import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class CreateTabels {
    static PreparedStatement ps;
    static PreparedStatement st;
    static ResultSet rs;

    public static void main(String args[]) throws SQLException, IOException {

    }

    void CreateProductsTable(){
        //TODO
    }

    public static void fillProductsTable(String imagePath, String name, String stock, String price) throws SQLException, IOException {
        ps = psConnectDB();
        ps.setString(1, name);
        ps.setInt(2, Integer.parseInt(stock));
        ps.setDouble(3, Double.parseDouble(price));
        FileInputStream fin = new FileInputStream(imagePath);
        ps.setBinaryStream(4, fin, fin.available());
        ps.executeUpdate();
        ps.close();
    }

    public static void updateProductsTable(String name, String newName, String newStock, String newPrice) throws SQLException, FileNotFoundException {
        st = stConnectDB();

        st.setString(1,newName);
        st.setInt(2, Integer.parseInt(newStock));
        st.setDouble(3, Double.parseDouble((newPrice)));
        st.setString(4,name);
        st.executeUpdate();
        st.close();
    }

    public static PreparedStatement psConnectDB() throws SQLException {
        String host = "jdbc:derby://localhost:1527/Shop";
        String username="shopadmin", password="shopadmin";
        Connection con = DriverManager.getConnection( host, username, password );
        return con.prepareStatement("insert into Products values(?,?,?,?)");
    }

    public static PreparedStatement stConnectDB() throws SQLException {
        String host = "jdbc:derby://localhost:1527/Shop";
        String username="shopadmin", password="shopadmin";
        Connection con = DriverManager.getConnection( host, username, password );
        return con.prepareStatement("UPDATE PRODUCTS SET NAME=?,STOCK=?,PRICE=? WHERE NAME=?");
    }
}
