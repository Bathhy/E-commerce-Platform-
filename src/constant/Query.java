package constant;

public class Query {
    public static String getcustomer = "SELECT * FROM customer WHERE username=? AND password=?";
    public static String createcustomer = "CALL signup_procedure(?,?,?,?)";
    public static String getprofilecustomer =  "SELECT username, phone_number, email FROM customer WHERE username = ?";
    public static String getproduct = "SELECT `e-com-platform`.product.name, `e-com-platform`.product.price,`e-com-platform`.product.quantity,`e-com-platform`.product.images,`e-com-platform`.seller.seller_name AS seller_name\n" +
            "            FROM product \n" +
            "            INNER JOIN seller\n" +
            "\t\tON product.seller_id = seller.seller_id";
}
