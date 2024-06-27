import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class CreateTabels {
    static PreparedStatement ps;
    static ResultSet rs;

    public static void main(String[] args) throws SQLException, IOException {
        fillProductsTable();
    }

    void CreateProductsTable(){

    }

    public static void fillProductsTable() throws SQLException, IOException {
        for(int i=0; i<10; i++) {
            ps = connectDB();
            ps.setString(1, String.valueOf(i));
            ps.setInt(2, 2);
            ps.setDouble(3, 10);
            FileInputStream fin = new FileInputStream("pictures\\banana.jpg");
            ps.setBinaryStream(4, fin, fin.available());
            ps.executeUpdate();
        }
    }

    public static PreparedStatement connectDB() throws SQLException {
        String host = "jdbc:derby://localhost:1527/Shop";
        String username="shopadmin", password="shopadmin";
        Connection con = DriverManager.getConnection( host, username, password );
        return con.prepareStatement("insert into Products values(?,?,?,?)");
    }
}
