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
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel projectlabel;
    private JLabel label;
    private JLabel passwordLabel;
    private JTextField usertextField;
    private JPasswordField passwordField;
    private JButton loginbutton;
    private JLabel result;
    private JButton signupbutton;
    private JLabel asksignuplabel;
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
        setSize(800, 600);
        try {
            setIconImage(ImageIO.read(logourl));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = getContentPane();
        container.setLayout(new BorderLayout());

        // Left panel
        leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLUE);
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));
        leftPanel.setLayout(new GridBagLayout());
        container.add(leftPanel, BorderLayout.WEST);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel welcomelabel = new JLabel("Welcome to ");
        welcomelabel.setForeground(Color.WHITE);
        welcomelabel.setFont(new Font("Arial", Font.BOLD, 15));
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
        projectlabel = new JLabel("Login");
        projectlabel.setFont(new Font("Arial", Font.BOLD, 24));
        projectlabel.setBounds(150, 10, 100, 30);
        rightPanel.add(projectlabel);

        // Username Label and textfield
        label = new JLabel("Username");
        label.setBounds(50, 60, 100, 30);
        rightPanel.add(label);

        usertextField = new JTextField();
        usertextField.setBounds(150, 60, 150, 30);
        rightPanel.add(usertextField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 100, 100, 30);
        rightPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 30);
        rightPanel.add(passwordField);

        loginbutton = new JButton("Login");
        loginbutton.setBounds(150, 140, 100, 30);
        loginbutton.addActionListener(this);
        loginbutton.setForeground(Color.WHITE);
        loginbutton.setBackground(Color.BLUE);
        loginbutton.addActionListener(this::navigateHome);
        rightPanel.add(loginbutton);

        asksignuplabel = new JLabel("Don't Have Account Yet?");
        asksignuplabel.setForeground(Color.BLUE);
        asksignuplabel.setBounds(60, 180, 150, 30);
        rightPanel.add(asksignuplabel);

        signupbutton = new JButton("Sign Up");
        signupbutton.setBounds(210, 180, 100, 30);
        signupbutton.setForeground(Color.ORANGE);
        signupbutton.addActionListener(this::navigateSignup);
        rightPanel.add(signupbutton);

        result = new JLabel("");
        result.setBounds(50, 220, 300, 30);
        result.setForeground(Color.RED);
        rightPanel.add(result);

        // Add right panel to container
        container.add(rightPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        String usernametextediting = usertextField.getText();
//        String passwordtexttediting = new String(passwordField.getPassword());
//        if (usernametextediting.isEmpty() && passwordtexttediting.isEmpty()) {
//            result.setText("Please Enter your Username and Password");
//        } else if (passwordtexttediting.length() < 8) {
//            result.setText("Password must be 8 characters");
//        }
    }

    public void navigateSignup(ActionEvent e) {
        new SignUpScreen();
        dispose();
    }
    public void navigateHome(ActionEvent e){
        new HomeScreen();
        dispose();
    }

}
