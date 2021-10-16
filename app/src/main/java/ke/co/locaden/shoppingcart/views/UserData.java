package ke.co.locaden.shoppingcart.views;

public class UserData {
    public String email,passwordRegister;


    public UserData() {

    }

    public UserData(String email, String passwordRegister) {

        this.email = email;
        this.passwordRegister = passwordRegister;

    }

    public String getEmail1() {
        return email;
    }

    public void setEmail1(String email1) {
        this.email = email1;
    }


    public String getPasswordR() {
        return passwordRegister;
    }

    public void setPasswordR(String passwordR) {
        this.passwordRegister = passwordRegister;
    }
}
