import javax.swing.*;

public class Product {
    final static ImageIcon NO_IMAGE = new ImageIcon("pictures\\default-product.jpg");
    String name;
    ImageIcon imageIcon;
    int stock;
    double price;
    double point;
    String votedUsers;

    public Product(String name, int stock, double price, ImageIcon imageIcon, double point, String votedUsers){
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.point = point;
        this.votedUsers = votedUsers;
        try {
            this.imageIcon = imageIcon;
        } catch (Exception e) {
            this.imageIcon = new ImageIcon("pictures\\default-product.png");
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}
