import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class buyPanel implements ActionListener {
    JPanel p;
    JPanel productsPanel;
    JPanel headerPanel;
    JPanel headerButtonsPanel;
    JPanel footerPanel;
    JPanel footerButtonsPanel;
    JButton productButton;
    JButton backButton;
    JButton profileButton;
    JButton prevPageButton;
    JButton cartButton;
    JButton nextPageButton;
    JPanel[][] panelHolder;
    production banana;

    public buyPanel(JPanel p){
        this.p = p;
        setPanel();
    }

    public void setPanel(){
        p.removeAll();

        p.setSize(1000,700);
        p.setLayout(new BorderLayout());
        p.setBackground(Color.pink);

        headerPanel = new JPanel();
        p.add(headerPanel,BorderLayout.NORTH);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(Color.pink);

        headerButtonsPanel = new JPanel();
        headerPanel.add(headerButtonsPanel,BorderLayout.EAST);
        headerButtonsPanel.setLayout(new FlowLayout());
        headerButtonsPanel.setBackground(Color.pink);

        backButton=new JButton();
        ImageIcon imageIcon = new ImageIcon("pictures\\—Pngtree—logout vector icon_4236995.png");
        Image image = imageIcon.getImage().getScaledInstance(40,40,0);
        backButton = new JButton(new ImageIcon());
        backButton.setIcon(new ImageIcon(image));
        backButton.setBackground(Color.pink);
        backButton.setBorderPainted(false);
        backButton.addActionListener(this);
        headerPanel.add(backButton,BorderLayout.WEST);

        cartButton=new JButton();
        imageIcon = new ImageIcon("pictures\\computer-icons-basket-shopping-cart-icon-basket-ab2de23c5d2704ea1bb31b2cd82be34a.png");
        image = imageIcon.getImage().getScaledInstance(40,40,0);
        cartButton = new JButton(new ImageIcon());
        cartButton.setIcon(new ImageIcon(image));
        cartButton.setBackground(Color.pink);
        cartButton.setBorderPainted(false);
        cartButton.addActionListener(this);
        headerButtonsPanel.add(cartButton);

        profileButton=new JButton();
        imageIcon = new ImageIcon("pictures\\favpng_user-profile-button.png");
        image = imageIcon.getImage().getScaledInstance(40,40,0);
        profileButton = new JButton(new ImageIcon());
        profileButton.setIcon(new ImageIcon(image));
        profileButton.setBackground(Color.pink);
        profileButton.setBorderPainted(false);
        profileButton.addActionListener(this);
        headerButtonsPanel.add(profileButton);

        productsPanel = new JPanel();
        productsPanel.setLayout(new GridLayout(2,4));
        productsPanel.setBackground(Color.pink);
        p.add(productsPanel,BorderLayout.CENTER);

        int i = 2;
        int j = 4;
        panelHolder = new JPanel[i][j];
        for(int m = 0; m < i; m++) {
            for(int n = 0; n < j; n++) {
                JPanel panel = new JPanel();
                panel.setBackground(Color.pink);
                panelHolder[m][n] = panel;
                productsPanel.add(panelHolder[m][n]);
            }
        }

        banana = new production();
        banana.number=3;

        for(int a=0; a<2; a++){
            for (int b=0; b<4; b++){
                panelHolder[a][b].add(createProduct("pictures\\banana.jpg","banana","100000"));
            }
        }

        footerPanel = new JPanel();
        footerPanel.setBackground(Color.pink);
        footerPanel.setLayout(new BorderLayout());
        footerButtonsPanel = new JPanel();
        footerPanel.add(footerButtonsPanel,BorderLayout.CENTER);
        footerButtonsPanel.setLayout(new BorderLayout());
        footerButtonsPanel.setBackground(Color.pink);

        imageIcon = new ImageIcon("pictures\\noun-next-button-967110.png");
        image = imageIcon.getImage().getScaledInstance(40,40,0);
        nextPageButton = new JButton(new ImageIcon());
        nextPageButton.setIcon(new ImageIcon(image));
        nextPageButton.setBackground(Color.pink);
        nextPageButton.setBorderPainted(false);
        nextPageButton.addActionListener(this);
        footerButtonsPanel.add(nextPageButton,BorderLayout.EAST);

        imageIcon = new ImageIcon("pictures\\noun-next-button-967105.png");
        image = imageIcon.getImage().getScaledInstance(40,40,0);
        prevPageButton = new JButton(new ImageIcon());
        prevPageButton.setIcon(new ImageIcon(image));
        prevPageButton.setBackground(Color.pink);
        prevPageButton.setBorderPainted(false);
        prevPageButton.addActionListener(this);
        footerButtonsPanel.add(prevPageButton,BorderLayout.WEST);

        p.add(footerPanel,BorderLayout.SOUTH);

        p.revalidate();
        p.repaint();
        p.setVisible(true);
    }

    public JPanel createProduct(String imagePath, String name, String price){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.pink);
        panel.setBorder(new LineBorder(Color.LIGHT_GRAY,1));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 5;
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage().getScaledInstance(100,120,0);
        productButton  = new JButton(new ImageIcon());
        productButton.setIcon(new ImageIcon(image));
        productButton.setBackground(Color.pink);
        productButton.setBorderPainted(false);
        productButton.addActionListener(this);
        panel.add(productButton,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel nameLabel = new JLabel(name,SwingConstants.CENTER);
        nameLabel.setBorder(new LineBorder(Color.LIGHT_GRAY,1));
        nameLabel.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,12));
        panel.add(nameLabel,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel priceLabel = new JLabel(price,SwingConstants.CENTER);
        priceLabel.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,12));
        priceLabel.setBorder(new LineBorder(Color.LIGHT_GRAY,1));
        panel.add(priceLabel,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new BorderLayout());
        JButton minusButton = new JButton("-");
        JButton plusButton = new JButton("+");
        JLabel countLabel = new JLabel("0",SwingConstants.CENTER);

        plusButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int number = Integer.parseInt(countLabel.getText());
                if(number+1 <= banana.number) {
                    number++;
                    countLabel.setText(String.valueOf(number));
                }
                else {
                    plusButton.setEnabled(false);
                }
            }
        });

        minusButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int number = Integer.parseInt(countLabel.getText());
                if(number-1 >= 0) {
                    number--;
                    countLabel.setText(String.valueOf(number));
                    plusButton.setEnabled(true);
                }
            }
        });

        countLabel.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,12));
        addPanel.add(minusButton,BorderLayout.WEST);
        addPanel.add(plusButton,BorderLayout.EAST);
        addPanel.add(countLabel,BorderLayout.CENTER);
        panel.add(addPanel,gbc);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

class production{
    int number;
}
