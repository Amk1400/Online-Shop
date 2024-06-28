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
            gridConstraints.insets = new Insets(1,0,DataBase.users.size(),0);
            gridConstraints.fill = GridBagConstraints.HORIZONTAL;
            gridConstraints.gridwidth = 1;
            gridConstraints.gridx = 0;
            gridConstraints.gridy = 1;
            bodyPanel.add(new JLabel("test"), gridConstraints);
            for (int i = 0; i < DataBase.users.size() ; i++) {
                gridConstraints.gridy = i;
                bodyPanel.add(new JLabel(DataBase.users.get(i).toString()), gridConstraints);
            }

        }else {
            //TODO normal profile
        }
    }


}
