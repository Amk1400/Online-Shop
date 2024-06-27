import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class RegPanel extends SignAndRegPanel {

    JTextField passwordAgainField;
    JButton registerButton;


    public RegPanel(JPanel lastPanel) throws SQLException {
        super(lastPanel);
        putRegButtonInPlace(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);//backButton

        if(e.getSource().equals(registerButton)){
            User inputUser = getInputUser();
            assignErrors(inputUser);

            if (errors.isEmpty()) {
                try {
                    DataBase.insertUser(inputUser);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                Main.setCurrentPanel(Main.INTRO_PANEL);
            } else {
                while (this.bodyPanel.getComponents().length > 10){
                    System.out.println(this.bodyPanel.getComponent(this.bodyPanel.getComponents().length-1).getBounds());
                    this.bodyPanel.remove(this.bodyPanel.getComponents().length-1);
                }

                putRegButtonInPlace(errors.size());
                setErrors(errors);
                this.repaint();
                this.revalidate();
                Main.setCurrentPanel(Main.REG_PANEL);
            }

        }
    }

    @Override
    protected void assignErrors(User inputUser) {
        errors = Validators.passwordValidator(inputUser.password);
        errors.addAll(Validators.userNameValidator(inputUser.userName));

        if (!repeatedPassMatches()){
            errors.add("You have repeated your password wrongly");
        }
        if (alreadyRegistered()){
            errors.add("This username is already taken");
        }
    }

    private boolean repeatedPassMatches() {
        String inputPass = passwordField.getText();
        String repeatPass = passwordAgainField.getText();
        return inputPass.equals(repeatPass);
    }

    protected void createBodyPanel() throws SQLException {
        super.createBodyPanel();

        this.add(bodyPanel,BorderLayout.CENTER);
    }

    private void putRegButtonInPlace(int errorsNum) {
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        registerButton = new JButton();
        createButton(registerButton,"pictures\\regButton.png",150,50, "Register");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = errorsNum+4;
        gridConstraints.gridwidth = 4;
        bodyPanel.add(registerButton,gridConstraints);
    }

    private void createRepeatPasswordSection() {
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridy = 3;
        gridConstraints.gridx = 0;
        gridConstraints.gridwidth= 1;
        bodyPanel.add(new JLabel("Repeat password: "),gridConstraints);
        gridConstraints.gridx = 1;
        gridConstraints.gridwidth = 3;
        gridConstraints.ipady = 30;
        passwordAgainField = new JTextField();
        passwordAgainField.setBorder(new LineBorder(Color.BLACK,3));
        bodyPanel.add(passwordAgainField,gridConstraints);
    }

    @Override
    protected void fillBlankOrRepeat() {
        createRepeatPasswordSection();
    }
}

