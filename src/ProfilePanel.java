import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class ProfilePanel extends ParentPanel{

    User currentUser;
    public ProfilePanel(JPanel lastPanel, User user) throws SQLException, IOException {
        super(lastPanel);
        currentUser = user;
        if(currentUser.equals(SignPanel.ADMIN)){
            gridConstraints.insets = new Insets(5,0,5,0);
            gridConstraints.fill = GridBagConstraints.HORIZONTAL;
            gridConstraints.gridx = 0;
            gridConstraints.gridwidth = 1;
            for (int i = 1; i < DataBase.users.size() ; i++) {
                gridConstraints.gridy = i;
                bodyPanel.add(new JLabel(DataBase.users.get(i).toString()), gridConstraints);
            }
            this.add(bodyPanel);

        }else {
            //TODO normal profile
        }
    }


}
