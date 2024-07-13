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

public class SignUpScreen extends JFrame implements ActionListener {
    private Container container;
    private JLabel titlelabel;
    private JLabel emailabel;
    private JLabel passwordLabel;
    private JLabel phonelabel;
    private JLabel userlabel;
    private JTextField usertextField;
    private JPasswordField passwordField;
    private JTextField emailTextField;
    private JTextField phoneTextField;
    private JButton signbutton;
    private JButton loginbutton;
    private JLabel resLabel;

    public SignUpScreen() {
        Constant mycont = new Constant();
        URL logourl;
        try {
            logourl = new URL(Constant.imgurl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try {
            setIconImage(ImageIO.read(logourl));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setTitle("E-Commerce Platform");
        setSize(400 ,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        container = getContentPane();
        container.setLayout(null);

        //Title label
        titlelabel = new JLabel("Sign Up");
        titlelabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titlelabel.setBounds(150, 10, 100,30);
        container.add(titlelabel);

        //Username Label and textfield
        userlabel = new JLabel("Username");
        userlabel.setBounds(50 , 60 ,100,30);
        container.add(userlabel);

        usertextField = new JTextField();
        usertextField.setBounds(150 ,60 , 150 ,30);
        container.add(usertextField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50 ,100,100, 30 );
        container.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150,100, 150 ,30);
        container.add(passwordField);

        emailabel = new JLabel("Email");
        emailabel.setBounds(50 ,140,100, 30 );
        container.add(emailabel);

        emailTextField = new JTextField();
        emailTextField.setBounds(150,140, 150 ,30);
        container.add(emailTextField);

        phonelabel = new JLabel("Phone Number");
        phonelabel.setBounds(50 ,180,100, 30 );
        container.add(phonelabel);


        phoneTextField = new JPasswordField();
        phoneTextField.setBounds(150,180, 150 ,30);
        container.add(phoneTextField);

        signbutton = new JButton("Sign Up");
        signbutton.setBounds(150 , 220 , 100, 30);
        signbutton.addActionListener(this);
        signbutton.setForeground(Color.WHITE);
        signbutton.setBackground(Color.BLUE);
        container.add(signbutton);

        JLabel asklogin = new JLabel("Already have account");
        asklogin.setBounds(60, 260, 150, 30);
        asklogin.setForeground(Color.BLUE);
        container.add(asklogin);

        loginbutton = new JButton("Login");
        loginbutton.setBounds(190, 260, 100, 30);
        loginbutton.setForeground(Color.ORANGE);
        loginbutton.addActionListener(this::navigateBack);
        container.add(loginbutton);

        resLabel = new JLabel("");
        resLabel.setForeground(Color.RED);
        resLabel.setBounds(50 , 290, 300 , 30);
        container.add(resLabel);

        setLocationRelativeTo(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void navigateBack(ActionEvent e){
        new LoginScreen();
        dispose();
    }
}
