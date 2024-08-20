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
    public static String getcartinfo = "SELECT \n" +
            "\tp.name AS product_name,\n" +
            "    p.price AS product_price,\n" +
            "\ts.seller_name AS seller_name,\n" +
            "    ci.qty AS Quantity,\n" +
            "    p.images AS cart_image,\n" +
            "    SUM(p.price * ci.qty) AS total_amount\n" +
            "    FROM cart c\n" +
            "    JOIN cart_item ci ON c.cart_id = ci.cart_id_fk\n" +
            "    JOIN product p ON ci.product_id_fk = p.product_id\n" +
            "    JOIN seller s ON p.seller_id = s.seller_id\n" +
            "\n" +
            " WHERE customer_id_fk= ?\n" +
            " GROUP BY \n" +
            "    p.name, p.price, s.seller_name, ci.qty, p.images;";
}
