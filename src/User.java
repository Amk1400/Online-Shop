import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

public class User {

    String userName;
    String password;
    String address;
    String phoneNumber;
    double wallet;
    HashMap<Product,Integer> cart;


    public User(String userName, String password, String address, String  phoneNumber, double wallet){
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.wallet = wallet;
        cart = new HashMap<>();
    }

    public void deposit(double money) throws SQLException {
        DataBase.updateUserWallet(this,money);
        wallet += money;
    }

    @Override
    public boolean equals(Object object) {
        User user = (User) object;
        return user.userName.equals(this.userName) && user.password.equals(this.password);
    }

    @Override
    public String toString() {
        return "User: " +
                userName +
                ", address = " + address +
                ", phoneNumber = " + phoneNumber;
    }
}
