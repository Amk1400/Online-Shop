import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class SignPanel extends SignAndRegPanel {

    JButton signInButton;

    public SignPanel(JPanel lastPanel) throws SQLException {
        super(lastPanel);
    }

    protected void createBodyPanel() throws SQLException {
        super.createBodyPanel();
        putSignButtonInPlace();
        this.add(bodyPanel, BorderLayout.CENTER);
    }

    @Override
    protected void fillBlankOrRepeat() {
        fillBlankRowOf(4,bodyPanel);
    }

    private void putSignButtonInPlace() {
        signInButton = new JButton();
        //TODO assign path,width,height of sign in icon
        createButton(signInButton,"pictures\\signinButton.png",100,50,"Sign-in");
        bodyPanel.add(signInButton,gridConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);//backButton

        if (e.getSource().equals(signInButton)) {
            User inputUser = getInputUser();
            assignErrors(inputUser);

            if (errors.isEmpty()) {
                Main.setCurrentPanel(Main.BUY_PANEL);
            } else {
                try {
                    Main.REG_PANEL = new RegPanel(lastPanel, errors);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                Main.setCurrentPanel(Main.REG_PANEL);
            }
        }
    }
}



