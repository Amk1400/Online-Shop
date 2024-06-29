import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class HistoryPanel extends ParentPanel implements ActionListener {

    int gridY = 0;
    JLabel date;
    JLabel time;
    JLabel user;
    JLabel products;

    public HistoryPanel(JPanel lastPanel) throws SQLException, IOException {
        super(lastPanel);
    }

    @Override
    protected void createBodyPanel() throws SQLException, IOException {
        super.createBodyPanel();

        User currentUser = Main.PROFILE_PANEL.currentUser;
        if(currentUser.userName.equals("Admin1")){
            for(History history : DataBase.histories){
                createHistoryRecord(history);
            }
        }
        else {
            for (History history : DataBase.histories) {
                if (history.user.equals(currentUser.userName)) {
                    createHistoryRecord(history);
                }
            }
        }

        this.add(bodyPanel, BorderLayout.CENTER);
    }

    private void createHistoryRecord(History history){

        gridConstraints.insets = new Insets(3,3,3,3);
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = gridY;
        date = new JLabel(history.date);
        bodyPanel.add(date,gridConstraints);

        gridConstraints.gridx++;
        time = new JLabel(history.time);
        bodyPanel.add(time,gridConstraints);

        gridConstraints.gridx++;
        products = new JLabel(history.products);
        bodyPanel.add(products,gridConstraints);

        gridConstraints.gridx++;
        user = new JLabel(history.user);
        bodyPanel.add(user,gridConstraints);

        gridY++;
    }

}