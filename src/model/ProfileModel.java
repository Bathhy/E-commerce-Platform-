package model;

public class ProfileModel {
    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        ProfileModel.username = username;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        ProfileModel.email = email;
    }

    public static String getPhonenumber() {
        return phonenumber;
    }

    public static void setPhonenumber(String phonenumber) {
        ProfileModel.phonenumber = phonenumber;
    }

    private static String username;
    private static String email;
    private static String phonenumber;
    private static int customid;

    public static int getCustomid() {
        return customid;
    }

    public static void setCustomid(int customid) {
        ProfileModel.customid = customid;
    }
}
