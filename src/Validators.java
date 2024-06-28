import java.util.ArrayList;
import java.util.regex.Pattern;

public abstract class Validators {

    public static final String TEST_PASSWORD = "kj";

    public static ArrayList<String> passwordValidator(String password){
        ArrayList<String> errors = new ArrayList<>();
        if(Pattern.matches("[^A-Z]+",password)){
            errors.add("Password must have A-Z character at least");
        }
        if(Pattern.matches("[^a-z]+",password)){
            errors.add("Password must have a-z character at least");
        }
        if(Pattern.matches("\\D+",password)){
            errors.add("Password must have 0-9 character at least");
        }
        if(!Pattern.matches("\\S+",password)){
            errors.add("Password must not have whitespace character");
        }
        if(!Pattern.matches("\\w{4,8}",password)){
            errors.add("Password must have 4-8 character");
        }
        return errors;
    }

    public static ArrayList<String> userNameValidator(String userName){
        ArrayList<String> errors = new ArrayList<>();
        if(Pattern.matches("[^A-Z]+",userName)){
            errors.add("UserName must have A-Z character at least");
        }
        if(Pattern.matches("[^a-z]+",userName)){
            errors.add("UserName must have a-z character at least");
        }
        if(Pattern.matches("\\D+",userName)){
            errors.add("UserName must have 0-9 character at least");
        }
        if(!Pattern.matches("\\S+",userName)){
            errors.add("UserName must not have whitespace character");
        }
        if(!Pattern.matches("\\w{4,8}",userName)){
            errors.add("UserName must have 4-8 character");
        }
        return errors;
    }

    public static ArrayList<String> phoneNumberValidator(String phoneNumber){
        ArrayList<String> errors = new ArrayList<>();
        if(!phoneNumber.startsWith("09")){
            errors.add("Phone number must start with 09");
        }
        if(phoneNumber.length() != 11){
            errors.add("Phone number must have 11 number");
        }
        return errors;
    }


    public static void main(String[] args){
        //TEST:
        ArrayList<String> errors = passwordValidator(TEST_PASSWORD);
        System.out.println(errors);
    }

}
