package Lib.GUIScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {
    private JPanel mainPanel; // Declare mainPanel at the class level

    public HomeScreen() {
        // Set up the frame
        setTitle("E-Commerce Platform");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(Color.BLUE);

        // Add buttons to the sidebar
        JButton homeButton = new JButton("Home");
        homeButton.setForeground(Color.WHITE);
        homeButton.setBackground(Color.BLUE);
        JButton profileButton = new JButton("My Cart");
        profileButton.setForeground(Color.WHITE);
        profileButton.setBackground(Color.BLUE);
        JButton settingsButton = new JButton("Profile");
        settingsButton.setForeground(Color.WHITE);
        settingsButton.setBackground(Color.BLUE);
        JButton logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(Color.BLUE);

        // Add action listeners to the buttons
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPanel("Home");
            }
        });

        profileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPanel("My Cart");
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPanel("Profile");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add buttons to the sidebar
        sidebar.add(homeButton);
        sidebar.add(profileButton);
        sidebar.add(settingsButton);
        sidebar.add(logoutButton);

        // Create the main panel
        mainPanel = new JPanel(new CardLayout());

        // Add different panels for each section
        mainPanel.add(new JLabel("Home Panel"), "Home");
        mainPanel.add(new JLabel("Profile Panel"), "Profile");
        mainPanel.add(new JLabel("Settings Panel"), "Settings");

        // Add the sidebar and main panel to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Set default panel
        showPanel("Home");
    }

    private void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout(); // Use mainPanel's layout
        cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HomeScreen().setVisible(true);
            }
        });
    }
}
