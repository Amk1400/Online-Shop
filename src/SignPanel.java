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
        putSignButtonInPlace();
        this.add(bodyPanel, BorderLayout.CENTER);
    }

    @Override
    protected void fillBlankOrRepeat() {
        fillBlankRowOf(4,bodyPanel);
    }

    private void putSignButtonInPlace() {
        signInButton = new JButton();
        //TODO assign path,width,height of sign in icon
        createButton(signInButton,"pictures\\signinButton.png",100,50,"Sign-in");
        bodyPanel.add(signInButton,gridConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource().equals(signInButton)){
            if(infoIsCorrect()){
                //TODO go to profile
            }else {//info wrong
                if(DBcontainsUserName()){
                    //TODO password is not correct
                }else {
                    //TODO please register first, there is no account with this username
                }
            }
        }
    }

    private boolean infoIsCorrect() {
        return DBcontainsUserName() && passwordMatchesThisUser();
    }

    private boolean DBcontainsUserName() {
        //TODO check if it contains
        return true;
    }

    private boolean passwordMatchesThisUser() {
        if(DBcontainsUserName()){
            //TODO check if it matches
            return true;
        }else {
            return false;
        }
    }
}
