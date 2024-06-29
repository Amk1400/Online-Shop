import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class UserBuyPanel extends BuyPanel{


    public UserBuyPanel(JPanel lastPanel) throws SQLException, IOException {
        super(lastPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource().equals(cartButton)){
            Main.setCurrentPanel(Main.CART_PANEL);
        }
    }

    protected void createSearchSection() {
        searchPanel = new JPanel();
        searchPanel.setBackground(Color.pink);
        searchPanel.setLayout(new FlowLayout());
        headerCenterButtonsPanel.add(searchPanel,BorderLayout.EAST);
        searchButton = new JButton();
        createButton(searchButton,"pictures\\search.png",30,30,"search");
        searchField = new JTextField();
        searchField.setSize(100,20);
        searchField.setColumns(10);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
    }

    @Override
    protected void createBodyPanel() throws SQLException, IOException {
        super.createBodyPanel();
        User user = Main.PROFILE_PANEL.currentUser;
        userCart = user.cart;
        fetchDBProducts();
    }
}
