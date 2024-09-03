package constant;

public class Query {
    public static String GET_CUSTOMER = "SELECT * FROM customer WHERE username=? AND password=?";
    public static String CREATE_CUSTOMER = "CALL signup_procedure(?,?,?,?)";
    public static String GET_PROFILE_CUSTOMER =  "SELECT username, phone_number, email, customer_id FROM customer WHERE username = ?";
    public static String GET_PRODUCT = "SELECT `e-com-platform`.product.product_id ,`e-com-platform`.product.name, `e-com-platform`.product.price,`e-com-platform`.product.quantity,`e-com-platform`.product.images,`e-com-platform`.seller.seller_name AS seller_name\n" +
            "            FROM product \n" +
            "            INNER JOIN seller\n" +
            "\t\tON product.seller_id = seller.seller_id";
    public static String ADD_TO_CART_ITEM = "INSERT INTO cart_item (cart_id_fk, product_id_fk, qty)\n" +
            "VALUES (?, ?, ?);";
    public static String INSERT_CART = "INSERT INTO cart(customer_id_fk)\n" +
            "VALUE(?)";
    public static String GET_CART_ID = "SELECT cart_id FROM cart WHERE cart.customer_id_fk = ?";
    public static String GET_CART_INFO ="WITH CartTotals AS (\n" +
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
    public static String UPDATE_CART_ITEM = "UPDATE cart_item SET qty = qty + ? WHERE cart_id_fk = ? AND product_id_fk = ?;";
    public static String REMOVE_CART = "DELETE FROM cart_item WHERE cart_item.cart_id_fk=? AND cart_item.product_id_fk=?";
    public static String CREATE_ORDER = "INSERT INTO `e-com-platform`.order(cart_id,paymet_id_fk, customer_id, orderdate)\n" +
            "            VALUES (?,?,?,?);";
    public static String CREATE_ORDER_ITEM = "INSERT INTO `e-com-platform`.orderitem (order_id, product_id, qty, price) \n" +
            "VALUES(?,?,?,?)";
    public static String CREATE_PAYMENT= "INSERT INTO `e-com-platform`.payment(payment_type_id, payment.cardnumber, payment.cvv_number, payment.card_expiration_date)\n" +
            "VALUE(?,?,?,?);";
    public static String GET_ORDER="SELECT * FROM `e-com-platform`.order WHERE customer_id=?;";
    public static String GET_ORDER_DETAIL= "SELECT \n" +
            "    pr.name AS ProductName,\n" +
            "    oi.qty AS ProductOrderQty,\n" +
            "    s.seller_name AS SellerName,\n" +
            "    pr.price AS \"Individual Product Price\",\n" +
            "    (oi.qty * pr.price) AS \"Total Individual Product Price\",\n" +
            "    (SELECT SUM(oi2.qty * pr2.price)\n" +
            "     FROM `e-com-platform`.OrderItem oi2\n" +
            "     JOIN `e-com-platform`.product pr2 ON oi2.product_id = pr2.product_id\n" +
            "     WHERE oi2.order_id = o.order_id) AS \"Total Amount\",\n" +
            "    payt.type_name AS PaymentMethod,\n" +
            "    o.orderdate AS OrderDate,\n" +
            "    c.username AS CustomerName\n" +
            "FROM \n" +
            "    `e-com-platform`.Order o\n" +
            "JOIN \n" +
            "    `e-com-platform`.OrderItem oi ON o.order_id = oi.order_id\n" +
            "JOIN \n" +
            "    `e-com-platform`.product pr ON oi.product_id = pr.product_id\n" +
            "JOIN \n" +
            "    `e-com-platform`.seller s ON pr.seller_id = s.seller_id\n" +
            "JOIN \n" +
            "    `e-com-platform`.payment py ON o.paymet_id_fk = py.payment_id\n" +
            "JOIN \n" +
            "    `e-com-platform`.payment_type payt ON py.payment_type_id = payt.payment_type_id\n" +
            "JOIN \n" +
            "    `e-com-platform`.customer c ON o.customer_id = c.customer_id\n" +
            "\n" +
            "WHERE o.cart_id = ? AND o.customer_id=?;";
    public static String DELETE_CART_ITEM = "DELETE FROM cart_item WHERE cart_item.cart_id_fk IN (SELECT cart.cart_id FROM cart WHERE cart.customer_id_fk = ?)";
    public static String DELETE_CART= "DELETE cart_itemd\n" +
            "FROM cart_item cart_itemd\n" +
            "JOIN cart c ON c.cart_id = cart_itemd.cart_id_fk\n" +
            "WHERE c.customer_id_fk = ?;";

}
