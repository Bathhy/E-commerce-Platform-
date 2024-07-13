package Lib.GUIScreen;

import constant.Constant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginScreen extends JFrame implements ActionListener {
    private Container container;
    private JLabel asksignuplabel;
    private JLabel projectlabel;
    private JLabel label;
    private JLabel passwordLabel;
    private JTextField usertextField;
    private JPasswordField passwordField;
    private JButton loginbutton;
    private  JLabel result;
    private JButton signupbutton;
    private JButton togglePasswordButton;
    private boolean istogglepassword = false;
    public LoginScreen() {
            Constant mycont = new Constant();
            URL logourl;
            try {
                logourl = new URL(Constant.imgurl);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            setTitle("E-Commerce Platform");
            setSize(400, 400);
            try {
                setIconImage(ImageIO.read(logourl));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            container = getContentPane();
            container.setLayout(null);


            projectlabel = new JLabel("Login");
            projectlabel.setFont(new Font("Arial", Font.PLAIN, 24));
            projectlabel.setBounds(150, 10, 100, 30);
            container.add(projectlabel);

            //username textfield label
            label = new JLabel("Username");
            label.setBounds(50, 30, 100, 30);
            container.add(label);

            //username textfield input
            usertextField = new JTextField();
            usertextField.setBounds(150, 40, 150, 30);
            container.add(usertextField);


            //password textfield label
            passwordLabel = new JLabel("Password");
            passwordLabel.setBounds(50, 80, 100, 30);
            container.add(passwordLabel);

            //password textfield
            passwordField = new JPasswordField();
            passwordField.setBounds(150, 80, 150, 30);
            container.add(passwordField);

//            togglePasswordButton = new JButton("👁");
//            togglePasswordButton.setBounds(310, 80, 50, 30);
//            togglePasswordButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                togglePasswordVisibility();
//            }
//        });
//        container.add(togglePasswordButton);
            //Login Button
            loginbutton = new JButton("Login");
            loginbutton.setBounds(150, 120, 100, 30);
            loginbutton.addActionListener(this);
            loginbutton.setForeground(Color.WHITE);
            loginbutton.setBackground(Color.BLUE);
            container.add(loginbutton);
            //ask Sign Up label
            asksignuplabel = new JLabel("Don't Have Account Yet?");
            asksignuplabel.setForeground(Color.BLUE);
            asksignuplabel.setBounds(60, 165, 150, 30);
            container.add(asksignuplabel);
            //signup button
            signupbutton = new JButton("Sign Up");
            signupbutton.setBounds(210, 165, 100, 30);
            signupbutton.setForeground(Color.ORANGE);
            signupbutton.addActionListener(this::navigateSignup);
            container.add(signupbutton);

            //warning message text
            result = new JLabel("");
            result.setBounds(50, 190, 300, 30);
            result.setForeground(Color.RED);
            container.add(result);

            setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String usernametextediting =usertextField.getText();
        String passwordtexttediting =new String(passwordField.getText());
        if(usernametextediting.isEmpty() && passwordtexttediting.isEmpty()){
            result.setText("Please Enter your Username and Password");
        }
        else if(passwordtexttediting.length() < 8){
            result.setText("Password must be 8 character");
        }
    }
    public void navigateSignup(ActionEvent e) {
        new SignUpScreen();
        dispose();
    }
//    private

}
