import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class ProfilePanel extends AfterLoginPanel{
    public ProfilePanel(JPanel lastPanel, User user) throws SQLException, IOException {
        super(lastPanel);
    }
}
