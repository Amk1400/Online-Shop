import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public abstract class AfterLoginPanel extends ParentPanel implements ActionListener {
    JPanel headerButtonsPanel;

    public AfterLoginPanel(JPanel lastPanel) throws SQLException {
        super(lastPanel);
    }

    @Override
    protected void createHeaderPanel(){
        super.createHeaderPanel();

        headerButtonsPanel = new JPanel();
        headerPanel.add(headerButtonsPanel, BorderLayout.EAST);
        headerButtonsPanel.setLayout(new FlowLayout());
        headerButtonsPanel.setBackground(Color.pink);

        createButton(cartButton, "pictures\\cartButton.png", 40, 40, "cart");
        createButton(profileButton, "pictures\\profileButton.png", 40, 40, "profile");
        createButton(backButton,"pictures\\introButton.png",40,40, "Back");
        headerPanel.add(backButton,BorderLayout.WEST);
        headerButtonsPanel.add(cartButton);
        headerButtonsPanel.add(profileButton);
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
