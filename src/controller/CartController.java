
package controller;

import constant.Query;
import model.CartModel;
import model.ProfileModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartController {

    private Connection con;
    List<CartModel> carts = new ArrayList<CartModel>();
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

    private int createCart(int customerId) {
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
        return -1;
    }

    public void getCartInformation(List<CartModel> carts) {
        try {
            String query = Query.getcartinfo;
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1 , ProfileModel.getCustomid());
            pst.setInt(2 , ProfileModel.getCustomid());
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

    // Method to add item to cart
    public boolean addItemToCart(int customerId, int productId, int qty) {
        try {
            int cartId = getOrCreateCart(customerId);
            if(checkItemExist(cartId, productId)){
               try(PreparedStatement pst = con.prepareStatement(Query.update_cart_item)){
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
                try (PreparedStatement pst = con.prepareStatement(Query.addtocartitem)) {
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
    private void updateCartTotalPrice(){}
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
            PreparedStatement pst = con.prepareStatement(Query.removecart);
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
}
