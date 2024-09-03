package controller;

import connection.MyDBConnection;
import constant.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentController {
    Connection con = MyDBConnection.getInstance().getConnection();

    public int createPayment(int paymentTypeId, String cardNumber, String cvvNumber, String cardExpire){
        PreparedStatement pst = null;
        ResultSet rs = null;
        int paymentId = -1;
        try{
           pst= con.prepareStatement(Query.CREATE_PAYMENT ,PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, paymentTypeId);
            pst.setString(2, cardNumber);
            pst.setString(3, cvvNumber);
            pst.setString(4, cardExpire);

            int rowApply = pst.executeUpdate();
            if(rowApply>0){
                rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    paymentId = rs.getInt(1);
                }
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
        return paymentId
                ;
    }

}
