import javax.swing.*;

public class Product {
    final static ImageIcon NO_IMAGE = new ImageIcon("pictures\\default-product.jpg");
    String name;
    ImageIcon imageIcon;
    int stock;
    double price;

    public Product(String name, int stock, double price, ImageIcon imageIcon){
        this.name = name;
        this.stock = stock;
        this.price = price;
        try {
            this.imageIcon = imageIcon;
        } catch (NullPointerException e) {//
            this.imageIcon = NO_IMAGE;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}
