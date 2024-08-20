package constant;

public class Query {
    public static String getcustomer = "SELECT * FROM customer WHERE username=? AND password=?";
    public static String createcustomer = "CALL signup_procedure(?,?,?,?)";
    public static String getprofilecustomer =  "SELECT username, phone_number, email, customer_id FROM customer WHERE username = ?";
    public static String getproduct = "SELECT `e-com-platform`.product.product_id ,`e-com-platform`.product.name, `e-com-platform`.product.price,`e-com-platform`.product.quantity,`e-com-platform`.product.images,`e-com-platform`.seller.seller_name AS seller_name\n" +
            "            FROM product \n" +
            "            INNER JOIN seller\n" +
            "\t\tON product.seller_id = seller.seller_id";
    public static String addtocartitem = "INSERT INTO cart_item (cart_id_fk, product_id_fk, qty)\n" +
            "VALUES (?, ?, ?);";
    public static String insertcart = "INSERT INTO cart(customer_id_fk)\n" +
            "VALUE(?)";
    public static String getcartid = "SELECT cart_id FROM cart WHERE cart.customer_id_fk = ?";
}
