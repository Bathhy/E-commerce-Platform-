package constant;

public class Query {
    public static String getcustomer = "SELECT * FROM customer WHERE username=? AND password=?";
    public static String createcustomer = "CALL signup_procedure(?,?,?,?)";
    public static String getprofilecustomer =  "SELECT username, phone_number, email FROM customer WHERE username = ?";
}
