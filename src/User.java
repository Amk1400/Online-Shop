import java.util.HashMap;

public class User {

    String userName;
    String password;
    String address;
    String phoneNumber;
    double wallet=0;
    HashMap<Product,Integer> cart;


    public User(String userName, String password, String address, String  phoneNumber){
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        cart = new HashMap<>();
    }

    @Override
    public boolean equals(Object object) {
        User user = (User) object;
        return user.userName.equals(this.userName) && user.password.equals(this.password);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", cart=" + cart +
                '}';
    }
}
