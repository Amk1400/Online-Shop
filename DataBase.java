import java.sql.*;

public abstract class DataBase {
    private final static String HOST = "jdbc:derby://localhost:1527/Shop";
    private final static String USER_NAME = "shopadmin";
    private final static String PASSWORD = "shopadmin";
    private final static String USERS_TABLE = "Users";
    private static Connection con = DriverManager.getConnection(HOST, USER_NAME, PASSWORD);

    public static void insertUser(String userName, String  password){
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE ,ResultSet.CONCUR_UPDATABLE);
        String SQL="select * from " + USERS_TABLE;
        ResultSet rs = stmt.executeQuery(SQL);
        rs.moveToInsertRow();
        rs.updateString("userName" , userName);
        rs.updateString("password" , password);
        rs.insertRow();
        rs.close();
        rs = stmt.executeQuery(SQL);
    }
}
