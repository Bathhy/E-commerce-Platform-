package model;

public class ProfileModel {
    private static ProfileModel instance;
    public static ProfileModel getInstance() {
        if (instance == null) {
            instance = new ProfileModel();
        }
        return instance;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getCustomid() {
        return customid;
    }

    public void setCustomid(int customid) {
        this.customid = customid;
    }

    private  String username;
    private  String email;
    private  String phonenumber;
    private  int customid;


}
