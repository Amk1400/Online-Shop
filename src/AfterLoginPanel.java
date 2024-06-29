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
    JMenuItem nameA_Z,nameZ_A,priceASC,priceDESC;
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
        createButton(backButton,"pictures\\backButton.png",40,40, "Back");
        headerPanel.add(backButton,BorderLayout.WEST);
        headerEastButtonsPanel.add(cartButton);
        headerEastButtonsPanel.add(profileButton);

        headerCenterButtonsPanel = new JPanel();
        headerPanel.add(headerCenterButtonsPanel,BorderLayout.CENTER);
        headerCenterButtonsPanel.setLayout(new BorderLayout());
        headerCenterButtonsPanel.setBackground(Color.pink);
        createSearchSection();

        nameA_Z = new JMenuItem("name A_Z");
        nameZ_A = new JMenuItem("name Z_A");
        priceASC = new JMenuItem("price ascending");
        priceDESC = new JMenuItem("price descending");
        nameA_Z.addActionListener(this);
        priceASC.addActionListener(this);
        nameZ_A.addActionListener(this);
        priceDESC.addActionListener(this);
        mb=new JMenuBar();
        order = new JMenu("Sort");
        order.add(nameA_Z);
        order.add(priceASC);
        order.add(nameZ_A);
        order.add(priceDESC);
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
            Main.setCurrentPanel(lastPanel);
        }
        else if (e.getSource().equals(profileButton)){
            try {
                Main.setCurrentPanel(new HistoryPanel(Main.USER_BUY_PANEL));
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
