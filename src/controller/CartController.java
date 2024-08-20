
package controller;
import constant.Query;
import java.sql.*;

public class CartController {

    private Connection con;

    public CartController(Connection con) {
        this.con = con;
    }

    public int getOrCreateCart(int customerId) {
        int cartId = -1;
        cartId = getExistingCartId(customerId);
        if (cartId == -1) {
            cartId = createCart(customerId);
        }
        return cartId;
    }

    private int getExistingCartId(int customerId) {
        try (PreparedStatement pst = con.prepareStatement(Query.getcartid)) {
            pst.setInt(1, customerId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("cart_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int createCart(int customerId)  {
        try (PreparedStatement pst = con.prepareStatement(Query.insertcart, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, customerId);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  -1;
    }

    // Method to add item to cart
    public boolean addItemToCart(int customerId, int productId, int qty) {
        try {
            int cartId = getOrCreateCart(customerId);
            try (PreparedStatement pst = con.prepareStatement(Query.addtocartitem)) {
                pst.setInt(1, cartId);
                pst.setInt(2, productId);
                pst.setInt(3, qty);
                int rowsAffected = pst.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }
}
