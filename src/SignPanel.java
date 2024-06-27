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
        putSignButtonInPlace(0);
        this.add(bodyPanel, BorderLayout.CENTER);
    }

    @Override
    protected void fillBlankOrRepeat() {
        fillBlankRowOf(4,bodyPanel);
    }

    private void putSignButtonInPlace(int errorsNum) {
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        signInButton = new JButton();
        createButton(signInButton,"pictures\\signinButton.png",150,50, "SignIn");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = errorsNum+4;
        gridConstraints.gridwidth = 4;
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
                this.bodyPanel.remove(signInButton);
                putSignButtonInPlace(errors.size());
                setErrors(errors);
                this.repaint();
                this.revalidate();
                Main.setCurrentPanel(Main.SIGN_PANEL);
            }
        }
    }
}



