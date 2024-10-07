package Online;

public class Client {

    private String nameClient;

    private String emailClient;
    private int phoneClient;

    // public getters
    public void setNameClient(String name) { this.nameClient = name; }
    public void setEmailClient(String email) { this.emailClient = email; }
    public void setPhoneClient(int phone) { this.phoneClient = phone; }

    // public setters
    public String getNameClient() {
        return this.nameClient;
    }
    public String getEmailClient() { return this.emailClient; }
    public int setPhoneClient() { return this.phoneClient; }

    // Constructor
    public Client(String cName, String cMail, int cPhone){
        this.nameClient = cName;
        this.emailClient = cMail;
        this.phoneClient = cPhone;
    }
}
