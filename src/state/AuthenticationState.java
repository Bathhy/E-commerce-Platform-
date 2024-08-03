package state;

public class AuthenticationState {
    private static boolean islogin = false;

    public static boolean isLogin(){
        return islogin;
    }
    public static void setLogin(boolean login){
        islogin = login;
    }
}
