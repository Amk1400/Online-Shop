import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class SignPanel extends SignAndRegPanel {

    JButton signInButton;
    public static final User ADMIN = new User("Admin1",String.valueOf("Admin1".hashCode()),null,null,0);

    public SignPanel(JPanel lastPanel) throws SQLException, IOException {
        super(lastPanel);
    }

    protected void createBodyPanel() throws SQLException, IOException {
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
        createButton(signInButton,"pictures\\signinButton.png",100,50, "SignIn");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = errorsNum+4;
        gridConstraints.gridwidth = 4;
        bodyPanel.add(signInButton,gridConstraints);
    }

    protected void assignErrors(User inputUser) throws SQLException {
        errors = new ArrayList<>();
        if(!alreadyRegistered()){
            errors.add("There is no such user, please register first!");
        }
    }

    @Override
    protected boolean alreadyRegistered() throws SQLException {
        ArrayList<User> users = DataBase.users;
        System.out.println(Arrays.toString(users.toArray()));
        boolean returned = super.alreadyRegistered();
        try {
            String inputPassword = getInputUser().password;
            returned = returned && inputPassword.equals(users.get(DataBase.userNames.indexOf(getInputUser().userName)).password);
        } catch (IndexOutOfBoundsException e) {//not found
            return false;
        }
        return returned;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);//backButton

        if (e.getSource().equals(signInButton)) {
            User inputUser = null;
            try {
                inputUser = getInputUser();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("I am assigning errors");
            try {
                assignErrors(inputUser);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (errors.isEmpty()) {
                if(inputUser.equals(ADMIN)){
                    try {
                        Main.PROFILE_PANEL = new ProfilePanel(Main.MANAGER_BUY_PANEL, inputUser);
                        Main.USER_BUY_PANEL = new UserBuyPanel(Main.SIGN_PANEL);
                    } catch (SQLException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Main.setCurrentPanel(Main.MANAGER_BUY_PANEL);
                }else {
                    try {
                        Main.PROFILE_PANEL = new ProfilePanel(Main.USER_BUY_PANEL, inputUser);
                        Main.USER_BUY_PANEL = new UserBuyPanel(Main.SIGN_PANEL);
                        Main.PROFILE_PANEL.lastPanel = Main.USER_BUY_PANEL;
                    } catch (SQLException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Main.setCurrentPanel(Main.USER_BUY_PANEL);
                }

            } else {
                while (this.bodyPanel.getComponents().length > 12){
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



