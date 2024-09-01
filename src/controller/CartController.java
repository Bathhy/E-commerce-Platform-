
package controller;

import constant.Query;
import model.CartModel;
import model.ProfileModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartController {

    final private Connection con;
    List<CartModel> carts = new ArrayList<>();
    private ProfileModel prof = new ProfileModel();
    public CartController(Connection con) {
        this.con = con;
    }

    public int getOrCreateCart(int customerId) {
        int cartId ;
        cartId = getExistingCartId(customerId);
        if (cartId == -1) {
            cartId = createCart(customerId);
            System.out.println("new cart created--->"+customerId);
        }
        return cartId;
    }

    private int getExistingCartId(int customerId) {
        try (PreparedStatement pst = con.prepareStatement(Query.GET_CART_ID)) {
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



    public void getCartInformation(List<CartModel> carts) {
        try {
            String query = Query.GET_CART_INFO;
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1 , prof.getCustomid());
            pst.setInt(2 , prof.getCustomid());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("Fetching Cart data:");
                System.out.println("Name: " + rs.getString("product_name"));
                System.out.println("Price: " + rs.getDouble("product_price"));
                System.out.println("Quantity: " + rs.getInt("Quantity"));
                System.out.println("Images: " + rs.getString("cart_image"));
                System.out.println("Seller ID: " + rs.getString("seller_name"));
                System.out.println("total amount: " + rs.getDouble("total_amount"));
                CartModel cartModel = new CartModel(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getDouble("product_price"),
                        rs.getString("cart_image"),
                        rs.getString("seller_name"),
                        rs.getInt("Quantity"),
                        rs.getDouble("total_amount"),
                        rs.getInt("cart_id"),
                        rs.getDouble("total_cart_amount")
                        );
                carts.add(cartModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private int createCart(int customerId) {
//        System.out.println("------------>Customer ID:"+customerId);
        try (PreparedStatement pst = con.prepareStatement(Query.INSERT_CART, Statement.RETURN_GENERATED_KEYS)) {
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
            e.getMessage();
        }
        return -1;
    }
    // Method to add item to cart
    public boolean addItemToCart(int customerId, int productId, int qty) {
        System.out.println("------------>Customer ID:"+customerId);
        try {
            int cartId = getOrCreateCart(customerId);
            if(checkItemExist(cartId, productId)){
               try(PreparedStatement pst = con.prepareStatement(Query.UPDATE_CART_ITEM)){
                    pst.setInt(1, qty);
                   pst.setInt(2, cartId);
                   pst.setInt(3, productId);
                   int rowsAffected = pst.executeUpdate();
                   if (rowsAffected > 0) {
                       getCartInformation(carts);
                       return true;
                   }
               }
            }else{
                try (PreparedStatement pst = con.prepareStatement(Query.ADD_TO_CART_ITEM)) {
                    pst.setInt(1, cartId);
                    pst.setInt(2, productId);
                    pst.setInt(3, qty);
                    int rowsAffected = pst.executeUpdate();
                    if( rowsAffected > 0){
                        getCartInformation(carts);
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Boolean checkItemExist(int cartid , int productId){
        try{
            PreparedStatement pst = con.prepareStatement(Query.CHECK_CART_ITEM_EXIST);
            pst.setInt(1, cartid);
            pst.setInt(2, productId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean removeItemCart(int cartid, int productId){
        try{
            PreparedStatement pst = con.prepareStatement(Query.REMOVE_CART);
            pst.setInt(1, cartid);
            pst.setInt(2, productId);
            int rowsAffected = pst.executeUpdate();
            System.out.println("what is cartid:"+cartid);
            System.out.println("what is prodid:"+productId);
            getCartInformation(carts);
            if (rowsAffected > 0) {
                getCartInformation(carts);
                return true;
            }
            getCartInformation(carts);
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return false;
    }
    public Integer createOrder(int cartid, int customerId, Date orderDate) {
        Integer orderId = null;
        try {
            PreparedStatement pst = con.prepareStatement(Query.CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, cartid);
            pst.setInt(2, customerId);
            pst.setDate(3, new java.sql.Date(orderDate.getTime()));
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderId = generatedKeys.getInt(1);  // Get the generated order ID
                        System.out.println("Order created with Order ID: " + orderId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }
    public Boolean createOrderItem(int orderId, int productId, int qty, double price){
        try {
            PreparedStatement pst = con.prepareStatement(Query.CREATE_ORDER_ITEM);
            pst.setInt(1, orderId);
            pst.setInt(2, productId);
            pst.setInt(3, qty);
            pst.setDouble(4, price);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order Item created!");
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
