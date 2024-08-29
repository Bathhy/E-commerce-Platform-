package controller;

import connection.MyDBConnection;
import constant.Query;
import model.OrderModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderController implements OrderControllerInt{
    Connection con = MyDBConnection.getInstance().getConnection();
    @Override
    public void getOrder(int customerId) {
        try{
            PreparedStatement pst = con.prepareStatement(Query.GET_ORDER);
            pst.setInt(1, customerId);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                OrderModel order = new OrderModel();
                int id = res.getInt("order_id");
                order.setOrderID(id);
                System.out.println("Order ID: " + order.getOrderID());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
