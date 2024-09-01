package controller;

import connection.MyDBConnection;
import constant.Query;
import model.OrderDetailModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class OrderController {

    Connection con = MyDBConnection.getInstance().getConnection();
    public Vector<OrderDetailModel> getOrdersDetail(int customerId) {
        Vector<OrderDetailModel> orderDetailList = new Vector<>();
        try {
            PreparedStatement pst = con.prepareStatement(Query.GET_ORDER_DETAIL);
            pst.setInt(1, customerId);
            ResultSet resp = pst.executeQuery();
            while (resp.next()) {
                System.out.println("======================>Get Order Detail data:");
                System.out.println("Quantity: " + resp.getInt("ProductOrderQty"));
                System.out.println("customer: " + resp.getString("CustomerName"));
                System.out.println("Tital ID: " + resp.getDouble("Total Amount"));
                OrderDetailModel orderDetail = new OrderDetailModel(
                        resp.getString("CustomerName"), resp.getString("OrderDate"),
                        resp.getString("PaymentMethod"),
                        resp.getDouble("Total Amount"),
                        resp.getDouble("Total Individual Product Price"),
                        resp.getDouble("Individual Product Price")
                        ,resp.getInt("ProductOrderQty"),
                        resp.getString("SellerName"),
                        resp.getString("ProductName")

                );
                orderDetailList.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetailList;
    }
    public void deleteCart(int customerID){
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(Query.DELETE_CART);
            pst.setInt(1, customerID);
            int rowEffect = pst.executeUpdate();
            if(rowEffect > 0){
                System.out.println("====================.>>Already Remove the Cart");
            }else {
                System.out.println("====================>> No Cart Found for the Customer");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
