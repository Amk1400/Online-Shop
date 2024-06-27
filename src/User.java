public class User {

    String userName;
    String password;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
