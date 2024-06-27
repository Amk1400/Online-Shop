import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class SignAndRegPanel extends ParentPanel{


    JTextField usernameField, passwordField;
    ArrayList<String> errors = new ArrayList<>();


    public SignAndRegPanel(JPanel lastPanel) throws SQLException {
        super(lastPanel);
    }

    protected User getInputUser(){
        return new User(usernameField.getText(), passwordField.getText());
    }

    protected void assignErrors(User inputUser) {
        errors = Validators.passwordValidator(inputUser.password);
        errors.addAll(Validators.userNameValidator(inputUser.userName));
    }

    protected boolean alreadyRegistered(){
        return DataBase.users.contains(getInputUser());
    }

    protected void createBodyPanel() throws SQLException {
        super.createBodyPanel();
        gridConstraints.insets = new Insets(5,0,5,0);
        fillBlankRowOf(0,bodyPanel);
        createUserNameSection();
        createPasswordSection();
        fillBlankOrRepeat();
    }

    protected void fillBlankOrRepeat() {

    }

    private static void pointToButtonInset() {
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 5;
        gridConstraints.ipady = 15;
        gridConstraints.gridwidth= 3;
    }

    private void createPasswordSection() {
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridy = 2;
        gridConstraints.gridx = 0;
        gridConstraints.gridwidth = 1;
        bodyPanel.add(new JLabel("Password: "), gridConstraints);
        gridConstraints.gridx = 1;
        gridConstraints.gridwidth = 3;
        gridConstraints.ipady = 30;
        passwordField = new JTextField();
        passwordField.setBorder(new LineBorder(Color.BLACK,3));
        bodyPanel.add(passwordField, gridConstraints);
    }

    private void createUserNameSection() {
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridy = 1;
        gridConstraints.gridx = 0;
        gridConstraints.gridwidth= 1;
        bodyPanel.add(new JLabel("Username: "), gridConstraints);
        gridConstraints.gridx = 1;
        gridConstraints.gridwidth = 3;
        gridConstraints.ipady = 30;
        usernameField = new JTextField();
        usernameField.setBorder(new LineBorder(Color.BLACK,3));
        bodyPanel.add(usernameField, gridConstraints);
    }

    protected static void fillBlankRowOf(int rowNumber, JPanel bodyPanel) {
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridwidth = 1;
        for (int j = 0; j < 4; j++) {
            gridConstraints.gridy = rowNumber;
            gridConstraints.gridx = j;
            bodyPanel.add(new JLabel("                   "), gridConstraints);
        }
    }
}
