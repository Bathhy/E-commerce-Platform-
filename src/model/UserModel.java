package model;

public class UserModel {
    private static String username;
    private static String password;


    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserModel.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserModel.password = password;
    }


}
