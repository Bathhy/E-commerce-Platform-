package Lib.guiscreen;

import Navigator.Navigate;
import connection.MyDBConnection;
import constant.Constant;
import constant.Query;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpScreen extends JFrame{
    private Container container;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel titlelabel;
    private JLabel emailabel;
    private JLabel passwordLabel;
    private JLabel phonelabel;
    private JLabel userlabel;
    private JTextField usertextField;
    private JPasswordField passwordField;
    private JTextField emailTextField;
    private final JTextField phoneTextField;
    private JButton signbutton;
    private JButton loginbutton;
    private JLabel resLabel;
    Navigate nav = new Navigate(this);
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
        setSize(Constant.screenwidth, Constant.screenheight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        container = getContentPane();
        container.setLayout(new BorderLayout());

        // Left panel
        leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLUE);
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));
        leftPanel.setLayout(new GridBagLayout()); // Use GridBagLayout to center components
        container.add(leftPanel, BorderLayout.WEST);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel welcomelabel = new JLabel("Welcome to ");
        welcomelabel.setForeground(Color.WHITE);
        welcomelabel.setFont(new Font("Arial", Font.PLAIN, 15));
        leftPanel.add(welcomelabel, gbc);

        gbc.gridy++;
        JLabel ecomlabel = new JLabel("E-Commerce Platform");
        ecomlabel.setForeground(Color.WHITE);
        ecomlabel.setFont(new Font("Arial", Font.BOLD, 15));
        leftPanel.add(ecomlabel, gbc);

        // Right panel
        rightPanel = new JPanel();
        rightPanel.setLayout(null);

        // Title label
        titlelabel = new JLabel("Sign Up");
        titlelabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlelabel.setBounds(150, 10, 100, 30);
        rightPanel.add(titlelabel);

        // Username Label and textfield
        userlabel = new JLabel("Username");
        userlabel.setBounds(50, 60, 100, 30);
        rightPanel.add(userlabel);

        usertextField = new JTextField();
        usertextField.setBounds(150, 60, 150, 30);
        rightPanel.add(usertextField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 100, 100, 30);
        rightPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 30);
        rightPanel.add(passwordField);

        emailabel = new JLabel("Email");
        emailabel.setBounds(50, 140, 100, 30);
        rightPanel.add(emailabel);

        emailTextField = new JTextField();
        emailTextField.setBounds(150, 140, 150, 30);
        rightPanel.add(emailTextField);

        phonelabel = new JLabel("Phone Number");
        phonelabel.setBounds(50, 180, 100, 30);
        rightPanel.add(phonelabel);

        phoneTextField = new JTextField();
        phoneTextField.setBounds(150, 180, 150, 30);
        rightPanel.add(phoneTextField);

        signbutton = new JButton("Sign Up");
        signbutton.setBounds(150, 220, 100, 30);
        signbutton.addActionListener(e -> {

            String username = usertextField.getText();
            String password = passwordField.getText();
            String email = emailTextField.getText();
            String phone = phoneTextField.getText();
            if(username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Please fill all the fields");
                return;
            }
            if(password.length() < 8){
                JOptionPane.showMessageDialog(rootPane, "Password must be at least 8 characters");
                return;
            }
            try {
                Connection con =MyDBConnection.getInstance().getConnection();
                String query = Query.CREATE_CUSTOMER;
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setString(3, email);
                stmt.setString(4 , phone);
                stmt.execute();
                System.out.println("sign up save");
                nav.navigatelogin(e);
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        });
        signbutton.setForeground(Color.WHITE);
        signbutton.setBackground(Color.BLUE);
        rightPanel.add(signbutton);

        JLabel asklogin = new JLabel("Already have an account?");
        asklogin.setBounds(30, 260, 150, 30);
        asklogin.setForeground(Color.BLUE);
        rightPanel.add(asklogin);

        loginbutton = new JButton("Login");
        loginbutton.setBounds(190, 260, 100, 30);
        loginbutton.setForeground(Color.ORANGE);
        loginbutton.addActionListener(e -> {
            nav.navigatelogin(e);
        });
        rightPanel.add(loginbutton);

        resLabel = new JLabel("");
        resLabel.setForeground(Color.RED);
        resLabel.setBounds(50, 290, 300, 30);
        rightPanel.add(resLabel);

        // Add right panel to container
        container.add(rightPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }


}
