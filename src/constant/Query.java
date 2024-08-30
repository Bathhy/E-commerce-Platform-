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
    public static String getcartinfo ="WITH CartTotals AS (\n" +
            "    SELECT \n" +
            "        c.cart_id,\n" +
            "        SUM(p.price * ci.qty) AS total_cart_amount\n" +
            "    FROM cart c\n" +
            "    JOIN cart_item ci ON c.cart_id = ci.cart_id_fk\n" +
            "    JOIN product p ON ci.product_id_fk = p.product_id\n" +
            "    WHERE c.customer_id_fk = ?\n" +
            "    GROUP BY c.cart_id\n" +
            ")\n" +
            "\n" +
            "SELECT \n" +
            "    c.cart_id AS cart_id,\n" +
            "    p.product_id AS product_id,\n" +
            "    p.name AS product_name,\n" +
            "    p.price AS product_price,\n" +
            "    s.seller_name AS seller_name,\n" +
            "    ci.qty AS quantity,\n" +
            "    p.images AS cart_image,\n" +
            "    (p.price * ci.qty) AS total_amount,\n" +
            "    ct.total_cart_amount\n" +
            "FROM cart c\n" +
            "JOIN cart_item ci ON c.cart_id = ci.cart_id_fk\n" +
            "JOIN product p ON ci.product_id_fk = p.product_id\n" +
            "JOIN seller s ON p.seller_id = s.seller_id\n" +
            "JOIN CartTotals ct ON c.cart_id = ct.cart_id\n" +
            "WHERE c.customer_id_fk = ?;";
    public static String CHECK_CART_ITEM_EXIST = "SELECT * FROM cart_item WHERE cart_id_fk = ? AND product_id_fk =?;";
    public static String update_cart_item = "UPDATE cart_item SET qty = qty + ? WHERE cart_id_fk = ? AND product_id_fk = ?;";
    public static String removecart = "DELETE FROM cart_item WHERE cart_item.cart_id_fk=? AND cart_item.product_id_fk=?";
    public static String createOrder = "INSERT INTO `e-com-platform`.order(cart_id, customer_id, orderdate) \n" +
            "VALUES (?,?,?);";
    public static String CREATE_ORDER_ITEM = "INSERT INTO `e-com-platform`.orderitem (order_id, product_id, qty, price) \n" +
            "VALUES(?,?,?,?)";
    public static String CREATE_PAYMENT= "INSERT INTO `e-com-platform`.payment(order_id_pay" +
            ",payment_type_id, payment.cardnumber, payment.cvv_number, payment.card_expiration_date)\n" +
            "VALUE(?,?,?,?,?);";
    public static String GET_ORDER="SELECT * FROM `e-com-platform`.order WHERE customer_id=?;";
}
