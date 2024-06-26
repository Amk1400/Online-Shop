import javax.swing.*;

public class Product {

    String name;
    ImageIcon imageIcon;
    int stock;
    double price;

    Product(String name, int stock, double price){
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }
}
