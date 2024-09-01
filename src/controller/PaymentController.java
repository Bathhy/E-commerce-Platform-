package controller;

import connection.MyDBConnection;
import constant.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentController {
    Connection con = MyDBConnection.getInstance().getConnection();

    public Boolean createPayment(int orderId, int paymentTypeId, String cardNumber, String cvvNumber, String cardExpire){
        PreparedStatement pst = null;
        try{
           pst= con.prepareStatement(Query.CREATE_PAYMENT);
           pst.setInt(1, orderId);
            pst.setInt(2, paymentTypeId);
            pst.setString(3, cardNumber);
            pst.setString(4, cvvNumber);
            pst.setString(5, cardExpire);

            int rowApply = pst.executeUpdate();
            if(rowApply>0){
                System.out.println("Already Already add to payment to DB");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return true
                ;
    }
}
