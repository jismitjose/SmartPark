package Online;

public class LoginOnline {

    private String userName;
    private String password;

    //setters
    public void setUserName(String userName) { this.userName = userName; }
    public void setPassword(String password) { this.password = password; }

    //getters
    public String getPassword() { return password; }
    public String getUserName() { return userName; }

    // constructor
    public LoginOnline(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
}
