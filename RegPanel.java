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
        putRegButtonInPlace(0);
    }

    RegPanel(JPanel lastPanel, ArrayList<String> errors){
        super(lastPanel);
        this.errors = errors;
        putRegButtonInPlace(errors.size());
        setErrors(errors);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource().equals(registerButton)){

            assignErrors();

            if (errors.isEmpty()) {
                //TODO
            } else {
                Main.REG_PANEL = new RegPanel(lastPanel,errors);
                Main.setCurrentPanel(Main.REG_PANEL);
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

        this.add(bodyPanel,BorderLayout.CENTER);
    }

    private void setErrors(ArrayList<String> errors){
        int errorsNum = errors.size();
        for (int i = 0; i <= errorsNum; i++) {
            gridConstraints.fill = GridBagConstraints.HORIZONTAL;
            gridConstraints.gridx = 0;
            gridConstraints.gridy = 4 + i;
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

