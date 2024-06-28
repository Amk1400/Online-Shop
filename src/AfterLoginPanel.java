import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public abstract class AfterLoginPanel extends ParentPanel implements ActionListener {
    JPanel headerEastButtonsPanel;
    JPanel headerCenterButtonsPanel;
    JPanel searchPanel;
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

        name = new JMenuItem("name");
        price = new JMenuItem("price");
        name.addActionListener(this);
        price.addActionListener(this);
        mb=new JMenuBar();
        order = new JMenu("Order");
        order.add(name);
        order.add(price);
        mb.add(order);
        mb.setBackground(Color.pink);
        headerCenterButtonsPanel.add(mb, BorderLayout.WEST);

    }

    @Override
    protected void createFooterPanel() {
        super.createFooterPanel();

        createButton(nextPageButton, "pictures\\nextPageButton.png", 40, 40, "nextPage");
        createButton(prevPageButton, "pictures\\prevPageButton.png", 40, 40, "prevPage");
        footerPanel.add(nextPageButton,BorderLayout.EAST);
        footerPanel.add(prevPageButton,BorderLayout.WEST);
    }

}
