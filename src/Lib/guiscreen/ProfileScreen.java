package Lib.guiscreen;

import Extension.Extension;
import model.ProfileModel;
import model.UserModel;
import constant.Constant;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import connection.MyDBConnection;
import constant.Query;

public class ProfileScreen extends JPanel {
    public void getProfile(){
        try {
            UserModel user= new UserModel();
            Connection con = MyDBConnection.getConnection();
            String query = Query.getprofilecustomer;
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1 , user.getUsername());
            ResultSet resp = pst.executeQuery();
            if(resp.next()) {
                ProfileModel.setUsername(resp.getString("username"));
                ProfileModel.setPhonenumber(resp.getString("phone_number"));
                ProfileModel.setEmail(resp.getString("email"));
            }else{
                System.out.println("No profile found");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public ProfileScreen() {
        getProfile();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        //Icon user
        ImageIcon usericon = new ImageIcon(Constant.iconpath);
        ImageIcon newusericon = Extension.resizeIcon(usericon, 40,50);
        JLabel iconLabel = new JLabel(newusericon);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create labels
        JLabel usernameLabel = new JLabel("Username: "+ProfileModel.getUsername(), SwingConstants.CENTER);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel emailLabel = new JLabel("Email: "+ProfileModel.getEmail(), SwingConstants.CENTER);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel phoneNumLabel = new JLabel("Phone Number: "+ProfileModel.getPhonenumber(), SwingConstants.CENTER);
        phoneNumLabel.setFont(new Font("Arial", Font.BOLD, 24));

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(iconLabel, gbc);

        gbc.gridy = 1;
        add(usernameLabel, gbc);

        gbc.gridy = 2;
        add(emailLabel, gbc);

        gbc.gridy = 3;
        add(phoneNumLabel, gbc);
    }

}

