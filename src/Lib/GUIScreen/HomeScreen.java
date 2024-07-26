package Lib.GUIScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {
    private JPanel mainPanel;
    private JPanel panel1;
    private JButton button1;
    private ProfileScreen profileScreen;
    private CartScreen cartScreen;
    private JTextField searchField;

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
        homeButton.setBorderPainted(false);

        JButton profileButton = new JButton("My Cart");
        profileButton.setForeground(Color.WHITE);
        profileButton.setBackground(Color.BLUE);
        profileButton.setBorderPainted(false);

        JButton settingsButton = new JButton("Profile");
        settingsButton.setForeground(Color.WHITE);
        settingsButton.setBackground(Color.BLUE);
        settingsButton.setBorderPainted(false);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(Color.BLUE);
        logoutButton.setBorderPainted(false);

        // Add action listeners to the buttons
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

        sidebar.add(homeButton);
        sidebar.add(profileButton);
        sidebar.add(settingsButton);
        sidebar.add(logoutButton);

        // Create the main panel with search bar
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        searchField = new JTextField("Search...");
        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                // Implement search functionality here
                System.out.println("Searching for: " + query);
            }
        });
        searchPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                // Implement search functionality here
                System.out.println("Searching for: " + query);
            }
        });
        searchPanel.add(searchButton);

        // Add the searchPanel to the mainPanel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        // Create and add the home panel with scroll pane
        JPanel homePanel = new JPanel();
        homePanel.setLayout(new GridLayout(0, 3, 10, 10));
        for (int i = 0; i < 9; i++) {
            homePanel.add(new CardGridview(new JButton("Add To Cart")));
        }
        JScrollPane homeScrollPane = new JScrollPane(homePanel);
        mainPanel.add(homeScrollPane, BorderLayout.CENTER);

        // Add the sidebar and main panel to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        setVisible(true);

        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                mainPanel.add(searchPanel, BorderLayout.NORTH);
                mainPanel.add(homeScrollPane, BorderLayout.CENTER);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
    }

    private void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        profileScreen = new ProfileScreen();
        cartScreen = new CartScreen();
        switch (panelName) {
            case "Profile":
                mainPanel.add(profileScreen, panelName);
                break;
            case "My Cart":
                mainPanel.add(cartScreen, panelName);
                break;
        }
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
