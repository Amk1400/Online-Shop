import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class SignAndRegPanel extends ParentPanel{


    JTextField usernameField, passwordField;
    ArrayList<String> errors = new ArrayList<>();
    static final int IPADY = 30;


    public SignAndRegPanel(JPanel lastPanel) throws SQLException, IOException {
        super(lastPanel);
    }

    protected User getInputUser() throws SQLException {
        return new User(usernameField.getText(), passwordField.getText(),null,null,DataBase.getWallet(usernameField.getText()));
    }

    protected void assignErrors(User inputUser) throws SQLException {
        errors = Validators.passwordValidator(inputUser.password);
        errors.addAll(Validators.userNameValidator(inputUser.userName));
    }

    protected boolean alreadyRegistered() throws SQLException {
        return DataBase.userNames.contains(getInputUser().userName);
    }

    protected void createBodyPanel() throws SQLException, IOException {
        super.createBodyPanel();
        gridConstraints.insets = new Insets(5,0,7,0);
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
        gridConstraints.ipady = IPADY;
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
        gridConstraints.ipady = IPADY;
        usernameField = new JTextField();
        usernameField.setBorder(new LineBorder(Color.BLACK,3));
        bodyPanel.add(usernameField, gridConstraints);
    }

    protected static void fillBlankRowOf(int rowNumber, JPanel bodyPanel) {
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridwidth = 1;
        gridConstraints.gridy = rowNumber;
        for (int j = 0; j < 4; j++) {
            gridConstraints.gridx = j;
            bodyPanel.add(new JLabel("                   "), gridConstraints);
        }
    }

    protected void setErrors(ArrayList<String> errors){
        int errorsNum = errors.size();
        for (int i = 0; i < errorsNum; i++) {
            gridConstraints.fill = GridBagConstraints.HORIZONTAL;
            gridConstraints.gridx = 0;
            gridConstraints.gridy = 6 + i;
            gridConstraints.ipady = 5;
            gridConstraints.gridwidth = 2;
            try {
                JLabel label = new JLabel("*"+errors.get(i));
                label.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,12));
                bodyPanel.add(label, gridConstraints);
            } catch (Exception e) {
                bodyPanel.add(new JLabel(""), gridConstraints);
            }
        }
    }
}
