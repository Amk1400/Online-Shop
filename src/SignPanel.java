import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class SignPanel extends SignAndRegPanel {

    JButton signInButton;
    public static final User ADMIN = new User("Admin1","Admin1",null,null);

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

    protected void assignErrors(User inputUser) {
        errors = new ArrayList<>();
        if(!alreadyRegistered()){
            errors.add("There is no such user, please register first!");
        }
    }

    @Override
    protected boolean alreadyRegistered() {
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
            User inputUser = getInputUser();
            System.out.println("I am assigning errors");
            assignErrors(inputUser);

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
                    } catch (SQLException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Main.setCurrentPanel(Main.USER_BUY_PANEL);
                }
                try {
                    Main.CART_PANEL = new CartPanel(Main.USER_BUY_PANEL);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
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



