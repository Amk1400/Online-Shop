import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RegPanel extends SignAndRegPanel {

    JTextField passwordAgainField;
    JButton registerButton;
    ArrayList<String> errors = new ArrayList<>();

    public RegPanel(JPanel lastPanel){
        super(lastPanel);
    }

    RegPanel(JPanel lastPanel, ArrayList<String> errors){
        super(lastPanel);
        this.errors = errors;
        this.lastPanel = lastPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource().equals(registerButton)){

            assignErrors();

            if (errors.isEmpty()) {
                String userName = this.usernameField.getText();
                String password = this.passwordField.getText();
                DataBase.insertUser(userName ,password);
            } else {
                //new RegPanel(lastPanel,errors);
                //TODO
            }

        }
    }

    private void assignErrors() {
        errors = Validators.passwordValidator(passwordField.getText());
        if (!repeatedPassMatches()){
            errors.add("You have repeated your password wrongly");
        }
    }

    private boolean repeatedPassMatches() {
        String inputPass = passwordField.getText();
        String repeatPass = passwordAgainField.getText();
        return inputPass.equals(repeatPass);
    }

    protected void createBodyPanel() {
        super.createBodyPanel();
        /*
        int errorsNum = errors.size();
        for (int i=0; i<=errorsNum; i++) {
            gridConstraints.fill = GridBagConstraints.HORIZONTAL;
            gridConstraints.gridx = 1;
            gridConstraints.gridy = 4+i;
            gridConstraints.ipady = 5;
            gridConstraints.gridwidth = 2;
            try {
                   JLabel label = new JLabel(STR."*\{errors.get(i)}");
                   label.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,12));
                   panel.add(label, gbc);
            }
            catch (Exception e){
                bodyPanel.add(new JLabel(""), gridConstraints);
            }
        }*/
        putRegButtonInPlace();
        this.add(bodyPanel,BorderLayout.CENTER);
    }

    private void putRegButtonInPlace() {
        registerButton = new JButton();
        createButton(registerButton,"pictures\\regButton.png",150,50, "Register");
        bodyPanel.add(registerButton,gridConstraints);
    }

    private void createRepeatPasswordSection() {
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

