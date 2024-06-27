import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class SignPanel extends SignAndRegPanel {

    JButton signInButton;
    final private User ADMIN = new User("Admin1","Admin1");

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

    protected void assignErrors(User inputUser) {
        super.assignErrors(inputUser);
        if(!alreadyRegistered()){
            errors.add("There is no such user, please register first!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);//backButton

        if (e.getSource().equals(signInButton)) {
            User inputUser = getInputUser();
            assignErrors(inputUser);

            if (errors.isEmpty()) {
                try {
                    Main.PROFILE_PANEL = new ProfilePanel(Main.SIGN_PANEL, inputUser);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                Main.setCurrentPanel(Main.PROFILE_PANEL);
            } else {
                while (this.bodyPanel.getComponents().length > 10){
                    System.out.println(this.bodyPanel.getComponent(this.bodyPanel.getComponents().length-1).getBounds());
                    this.bodyPanel.remove(this.bodyPanel.getComponents().length-1);
                }

                putSignButtonInPlace(errors.size());
                setErrors(errors);
                this.repaint();
                this.revalidate();
                Main.setCurrentPanel(Main.SIGN_PANEL);
            }
        }
    }
}



