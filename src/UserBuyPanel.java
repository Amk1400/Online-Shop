import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class UserBuyPanel extends BuyPanel{


    public UserBuyPanel(JPanel lastPanel) throws SQLException, IOException {
        super(lastPanel);
    }

    @Override
    protected void createBodyPanel() throws SQLException, IOException {
        super.createBodyPanel();
        User user = Main.PROFILE_PANEL.currentUser;
        userCart = user.cart;
        fetchDBProducts();
    }
}
