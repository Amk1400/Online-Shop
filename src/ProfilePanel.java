import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfilePanel extends ParentPanel implements ActionListener {

    User currentUser;
    JButton historyButton;
    JButton walletIncrease;
    JButton applyChanges;
    JTextField usernameField;
    JTextField passwordField;
    JTextField phoneField;
    JTextField addressField;
    JLabel money;

    @Override
    protected void createHeaderPanel() throws SQLException {
        super.createHeaderPanel();

        JPanel walletPanel = new JPanel();
        walletPanel.setLayout(new FlowLayout());
        walletPanel.setBackground(Color.pink);
        money = new JLabel();
        JButton walletButton = new JButton();
        createButton(walletButton,"pictures\\wallet.png",30,30,"wallet");
        walletPanel.add(walletButton);
        walletPanel.add(money);
        headerPanel.add(walletPanel,BorderLayout.CENTER);

        historyButton = new JButton();
        createButton(historyButton,"pictures\\history.png",40,40,"history");
        headerPanel.add(historyButton,BorderLayout.EAST);
    }

    public ProfilePanel(JPanel lastPanel, User user) throws SQLException, IOException {
        super(lastPanel);
        currentUser = user;
        money.setText(String.valueOf(DataBase.getWallet(currentUser.userName)));
        if(currentUser.equals(SignPanel.ADMIN)){
            gridConstraints.insets = new Insets(5,0,5,0);
            gridConstraints.fill = GridBagConstraints.HORIZONTAL;
            gridConstraints.gridx = 0;
            gridConstraints.gridwidth = 1;
            for (int i = 0; i < DataBase.users.size() ; i++) {
                gridConstraints.gridy = i;
                if(!DataBase.users.get(i).userName.equals("Admin1")) {
                    bodyPanel.add(new JLabel(DataBase.users.get(i).toString()), gridConstraints);
                }
            }
            this.add(bodyPanel);

        }
        else{
            gridConstraints.insets = new Insets(5,0,5,0);
            gridConstraints.fill = GridBagConstraints.HORIZONTAL;
            gridConstraints.gridx = 0;
            gridConstraints.gridwidth = 2;
            gridConstraints.gridy = 0;
            bodyPanel.add(new JLabel(" "), gridConstraints);
            gridConstraints.gridx++;
            bodyPanel.add(new JLabel("IF YOU WANT TO CHANGE SOMETHING WRITE IN TEXT FIELDS:"), gridConstraints);
            gridConstraints.gridy++;
            gridConstraints.gridwidth--;

            bodyPanel.add(new JLabel("Username: " + currentUser.userName), gridConstraints);
            gridConstraints.gridx++;
            usernameField = new JTextField();
            bodyPanel.add(usernameField,gridConstraints);
            gridConstraints.gridx--;

            gridConstraints.gridy++;
            bodyPanel.add(new JLabel("Adress: " + currentUser.address), gridConstraints);
            gridConstraints.gridx++;
            addressField = new JTextField();
            bodyPanel.add(addressField,gridConstraints);
            gridConstraints.gridx--;

            gridConstraints.gridy++;
            bodyPanel.add(new JLabel("phone number: " + currentUser.phoneNumber), gridConstraints);
            gridConstraints.gridx++;
            phoneField = new JTextField();
            bodyPanel.add(phoneField,gridConstraints);
            gridConstraints.gridx--;

            gridConstraints.gridy++;
            bodyPanel.add(new JLabel("password: " + "******"), gridConstraints);
            gridConstraints.gridx++;
            passwordField = new JTextField();
            bodyPanel.add(passwordField,gridConstraints);
            gridConstraints.gridx--;

            gridConstraints.gridy++;
            this.add(bodyPanel);

            gridConstraints.gridwidth=1;
            applyChanges = new JButton("APPLY CHANGES");
            applyChanges.addActionListener(this);
            walletIncrease = new JButton("INCREASE WALLET");
            walletIncrease.addActionListener(this);
            bodyPanel.add(applyChanges,gridConstraints);
            gridConstraints.gridx++;
            bodyPanel.add(walletIncrease,gridConstraints);
            this.add(bodyPanel);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

        if(e.getSource().equals(historyButton)){
            try {
                Main.setCurrentPanel(new HistoryPanel(Main.PROFILE_PANEL));
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource().equals(walletIncrease)){
            money.setText(String.valueOf(Double.parseDouble(money.getText())+100));
            currentUser.wallet += 100;
            try {
                DataBase.updateUserWallet(currentUser,100);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource().equals(applyChanges)){
            try {
                DataBase.updateUser(new User(usernameField.getText(),passwordField.getText(),phoneField.getText(),addressField.getText(),currentUser.wallet),currentUser.userName);
                DataBase.fetchDB();

                String newusername = usernameField.getText();
                String newpassword = passwordField.getText();
                String newphone = phoneField.getText();
                String newaddres = addressField.getText();

                ArrayList<String> errors = Validators.passwordValidator(newpassword);
                errors.addAll(Validators.userNameValidator(newusername));
                errors.addAll(Validators.phoneNumberValidator(newphone));
                //TODO
                if (errors.isEmpty()) {
                    while (this.bodyPanel.getComponents().length > 14) {
                        System.out.println(this.bodyPanel.getComponent(this.bodyPanel.getComponents().length - 1).getBounds());
                        this.bodyPanel.remove(this.bodyPanel.getComponents().length - 1);
                    }
                }

                setErrors(errors);
                this.repaint();
                this.revalidate();
                Main.setCurrentPanel(Main.PROFILE_PANEL);
                //TODO
                boolean u = newusername == null;
                boolean pa = newpassword == null;
                boolean a = newaddres == null;
                boolean ph = newphone == null;
                if (u && pa && ph ){
                    currentUser.address = newaddres;
                }
                if (pa && ph && a){
                    currentUser.userName = newusername;
                }
                if (u && pa && a){
                    currentUser.phoneNumber = newphone;
                }
                if (u && ph && a){
                    currentUser.password = newpassword;
                }
                if (u && pa){
                    currentUser.phoneNumber = newphone;
                    currentUser.address = newaddres;
                }
                if (u && a ){
                    currentUser.password = newpassword;
                    currentUser.phoneNumber = newphone;
                }
                if (u && ph){
                    currentUser.password = newpassword;
                    currentUser.address = newaddres;
                }
                if (pa && a){
                    currentUser.userName = newusername;
                    currentUser.phoneNumber = newphone;
                }
                if (pa && ph){
                    currentUser.userName = newusername;
                    currentUser.address = newaddres;
                }
                if (a && ph){
                    currentUser.password = newpassword;
                    currentUser.userName = newusername;
                }
                if (u){
                    currentUser.password = newpassword;
                    currentUser.address = newaddres;
                    currentUser.phoneNumber = newphone;
                }
                if (pa){
                    currentUser.userName = newusername;
                    currentUser.address = newaddres;
                    currentUser.phoneNumber = newphone;
                }
                if (a){
                    currentUser.password = newpassword;
                    currentUser.userName = newusername;
                    currentUser.phoneNumber = newphone;
                }
                if (ph){
                    currentUser.password = newpassword;
                    currentUser.address = newaddres;
                    currentUser.userName = newusername;
                }


                else{
                    currentUser.password = newpassword;
                    currentUser.address = newaddres;
                    currentUser.userName = newusername;
                    currentUser.phoneNumber = newphone;
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    protected void setErrors(ArrayList<String> errors) {
        int errorsNum = errors.size();
        for (int i = 0; i < errorsNum; i++) {
            gridConstraints.fill = GridBagConstraints.HORIZONTAL;
            gridConstraints.gridx = 0;
            gridConstraints.gridy = 6 + i;
            gridConstraints.ipady = 5;
            gridConstraints.gridwidth = 2;
            try {
                JLabel label = new JLabel("*" + errors.get(i));
                label.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
                bodyPanel.add(label, gridConstraints);
            } catch (Exception e) {
                bodyPanel.add(new JLabel(""), gridConstraints);
            }
        }
    }
}