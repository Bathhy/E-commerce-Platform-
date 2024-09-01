package controller;

import connection.MyDBConnection;
import constant.Query;
import model.ProfileModel;
import model.UserModel;
import state.AuthenticationState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    Connection con = MyDBConnection.getInstance().getConnection();
    private model.ProfileModel ProfileModel = model.ProfileModel.getInstance();
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


    public void getProfile(){
        try {
            UserModel user= new UserModel();
            Connection con = MyDBConnection.getInstance().getConnection();
            String query = Query.GET_PROFILE_CUSTOMER;
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1 , user.getUsername());
            ResultSet resp = pst.executeQuery();
            if(resp.next()) {
                ProfileModel.setUsername(resp.getString("username"));
                ProfileModel.setPhonenumber(resp.getString("phone_number"));
                ProfileModel.setEmail(resp.getString("email"));
                ProfileModel.setCustomid(resp.getInt("customer_id"));
                System.out.println("Profile Name : "+ProfileModel.getCustomid());
            }else{
                System.out.println("No profile found");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
