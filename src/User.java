public class User {

    String userName;
    String password;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean equals(Object object) {
        User user = (User) object;
        return user.userName.equals(this.userName) && user.password.equals(this.password);
    }
}
