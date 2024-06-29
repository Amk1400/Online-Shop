import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public abstract class AfterLoginPanel extends ParentPanel implements ActionListener {
    JPanel headerEastButtonsPanel;
    JPanel headerCenterButtonsPanel;
    JTextField searchField;
    JButton searchButton;
    JMenuBar mb;
    JMenu order;
    JMenuItem name,price;
    static String sql = "select * from Products Order by Name";

    public AfterLoginPanel(JPanel lastPanel) throws SQLException, IOException {
        super(lastPanel);
    }



    @Override
    protected void createHeaderPanel(){
        super.createHeaderPanel();

        headerEastButtonsPanel = new JPanel();
        headerPanel.add(headerEastButtonsPanel, BorderLayout.EAST);
        headerEastButtonsPanel.setLayout(new FlowLayout());
        headerEastButtonsPanel.setBackground(Color.pink);

        createButton(cartButton, "pictures\\cartButton.png", 40, 40, "cart");
        createButton(profileButton, "pictures\\profileButton.png", 40, 40, "profile");
        createButton(backButton,"pictures\\introButton.png",40,40, "Back");
        headerPanel.add(backButton,BorderLayout.WEST);
        headerEastButtonsPanel.add(cartButton);
        headerEastButtonsPanel.add(profileButton);

        headerCenterButtonsPanel = new JPanel();
        headerPanel.add(headerCenterButtonsPanel,BorderLayout.CENTER);
        headerCenterButtonsPanel.setLayout(new BorderLayout());
        headerCenterButtonsPanel.setBackground(Color.pink);
        createSearchSection();

        name = new JMenuItem("name");
        price = new JMenuItem("price");
        name.addActionListener(this);
        price.addActionListener(this);
        mb=new JMenuBar();
        order = new JMenu("Sort");
        order.add(name);
        order.add(price);
        mb.add(order);
        mb.setBackground(Color.pink);
        headerCenterButtonsPanel.add(mb, BorderLayout.WEST);

    }

    protected void createSearchSection() {

    }

    @Override
    protected void createFooterPanel() {
        super.createFooterPanel();

        createButton(nextPageButton, "pictures\\nextPageButton.png", 40, 40, "nextPage");
        createButton(prevPageButton, "pictures\\prevPageButton.png", 40, 40, "prevPage");
        footerPanel.add(nextPageButton,BorderLayout.EAST);
        footerPanel.add(prevPageButton,BorderLayout.WEST);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(backButton)){
            Main.setCurrentPanel(Main.SIGN_PANEL);
        }
        else if (e.getSource().equals(profileButton)){
            Main.setCurrentPanel(Main.PROFILE_PANEL);
        }
    }
}
