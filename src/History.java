import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.HashMap;

public class History {
    String Id;
    String date;
    String time;
    String user;
    String products;
    double cost;

    History(String date, String user, String products, double cost, String Id) throws SQLException {
        this.date = date;
        this.user = user;
        this.products = products;
        this.cost = cost;
        this.Id = Id;
    }


}
