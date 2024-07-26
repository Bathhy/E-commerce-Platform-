package Lib.GUIScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homescreenecom extends JFrame {
    private JPanel contentPanel;
    private JPanel searchPanel;
    private JPanel homePanel;
    private ProfileScreen profileScreen;
    private CartScreen cartScreen;
    private JTextField searchField;

    public Homescreenecom() {
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

        JButton cartButton = new JButton("My Cart");
        cartButton.setForeground(Color.WHITE);
        cartButton.setBackground(Color.BLUE);
        cartButton.setBorderPainted(false);

        JButton profileButton = new JButton("Profile");
        profileButton.setForeground(Color.WHITE);
        profileButton.setBackground(Color.BLUE);
        profileButton.setBorderPainted(false);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(Color.BLUE);
        logoutButton.setBorderPainted(false);

        // Add action listeners to the buttons
        cartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPanel("My Cart");
            }
        });

        profileButton.addActionListener(new ActionListener() {
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
        sidebar.add(cartButton);
        sidebar.add(profileButton);
        sidebar.add(logoutButton);

        // Create the search panel
        searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchField = new JTextField("Search...");
        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                System.out.println("Searching for: " + query);
            }
        });
        searchPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                System.out.println("Searching for: " + query);
            }
        });
        searchPanel.add(searchButton);

        // Create the content panel with CardLayout
        contentPanel = new JPanel(new CardLayout());

        // Create and add the home panel with scroll pane
        homePanel = new JPanel(new BorderLayout());
        JPanel homeContentPanel = new JPanel();
        homeContentPanel.setLayout(new GridLayout(0, 3, 10, 10));
        for (int i = 0; i < 9; i++) {
            homeContentPanel.add(new CardGridview(new JButton("Add To Cart")));
        }
        JScrollPane homeScrollPane = new JScrollPane(homeContentPanel);

        homePanel.add(searchPanel, BorderLayout.NORTH);
        homePanel.add(homeScrollPane, BorderLayout.CENTER);

        // Add the home panel to the contentPanel
        contentPanel.add(homePanel, "HomePanel");

        // Initialize profile and cart screens
        profileScreen = new ProfileScreen();
        cartScreen = new CartScreen();

        // Add profile and cart screens to the contentPanel
        contentPanel.add(profileScreen, "Profile");
        contentPanel.add(cartScreen, "My Cart");

        // Add the sidebar and contentPanel to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        setVisible(true);

        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) contentPanel.getLayout();
                cl.show(contentPanel, "HomePanel");
            }
        });
    }

    private void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, panelName);
    }
}
