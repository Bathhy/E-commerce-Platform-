package Lib.GUIScreen;

import Extension.Extension;
import constant.Constant;

import javax.swing.*;
import java.awt.*;

public class ProfileScreen extends JPanel {

    public ProfileScreen() {
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
        JLabel usernameLabel = new JLabel("Username: Leonard", SwingConstants.CENTER);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel emailLabel = new JLabel("Email: Leonard@gmail.com", SwingConstants.CENTER);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel phoneNumLabel = new JLabel("Phone Number: 01233434", SwingConstants.CENTER);
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

