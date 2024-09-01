package controller;

import connection.MyDBConnection;
import constant.Query;
import model.ProductModel;
import model.UserModel;
import state.AuthenticationState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
    List<ProductModel> product = new ArrayList<>();
    Connection con = MyDBConnection.getInstance().getConnection();
//    public List<ProductModel> getProduct() {
//
//        try {
//
//            String query = Query.getproduct;
//            PreparedStatement pst = con.prepareStatement(query);
//            ResultSet resp = pst.executeQuery();
//            while (resp.next()) {
//                System.out.println("Fetching data:");
//                System.out.println("Name: " + resp.getString("name"));
//                System.out.println("Price: " + resp.getDouble("price"));
//                System.out.println("Quantity: " + resp.getInt("quantity"));
//                System.out.println("Images: " + resp.getString("images"));
//                System.out.println("Seller ID: " + resp.getString("seller_name"));
//                ProductModel prod = new ProductModel(
//                        resp.getString("name"),
//                        resp.getDouble("price"),
//                        resp.getInt("quantity"),
//                        resp.getString("images"),
//                        resp.getString("seller_name")
//                );
//                product.add(prod);
//
//            }
//            for (ProductModel prod : product) {
//                System.out.println("Product name: " + prod.getProductname());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public boolean loginUser(String username, String password) {
        String query = Query.GET_CUSTOMER;

        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, password);

            try (ResultSet resp = pst.executeQuery()) {
                if (resp.next()) {
                    UserModel.setUsername(resp.getString("username"));
                    UserModel.setPassword(resp.getString("password"));
                    AuthenticationState.setLogin(true);
                    return true;
                } else {
                    AuthenticationState.setLogin(false);
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
