package Lib.guiscreen;

import connection.MyDBConnection;
import constant.Constant;
import constant.Query;
import controller.CartController;
import model.ProductModel;
import model.ProfileModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Homescreenecom extends JFrame {
    private JPanel contentPanel;
    private JPanel searchPanel;
    private JPanel homePanel;
    private ProfileScreen profileScreen;
    private CartScreen cartScreen;
    private JTextField searchField;
    private List<ProductModel> product = new ArrayList<>();
    private List<ProductModel> filteredProducts = new ArrayList<>();
    private JPanel homeContentPanel;
    private CartController cartcontrol;
    private ProfileModel prof = new ProfileModel();

    Connection con = MyDBConnection.getInstance().getConnection();

    public void getProduct() {
        try {
            String query = Query.GET_PRODUCT;
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet resp = pst.executeQuery();
            while (resp.next()) {
                System.out.println("Fetching data:");
                System.out.println("id " + resp.getInt("product_id"));
                System.out.println("Name: " + resp.getString("name"));
                System.out.println("Price: " + resp.getDouble("price"));
                System.out.println("Quantity: " + resp.getInt("quantity"));
                System.out.println("Images: " + resp.getString("images"));
                System.out.println("Seller ID: " + resp.getString("seller_name"));
                ProductModel prod = new ProductModel(resp.getString("name"), resp.getDouble("price"),
                        resp.getString("images"),
                        resp.getString("seller_name"), resp.getInt("product_id"));
                product.add(prod);
            }
            filteredProducts.addAll(product); // Initially, display all products
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Homescreenecom() {
        // Set up the frame
        getProduct();
        setTitle("E-Commerce Platform");
        setSize(Constant.screenwidth, Constant.screenheight);
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

                cartScreen.refreshCartUI();
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
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                System.out.println("Searching for: " + query);
                performSearch(query);
            }
        });
        searchPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                System.out.println("Searching for: " + query);
                performSearch(query);
            }
        });
        searchPanel.add(searchButton);

        // Create the content panel with CardLayout
        contentPanel = new JPanel(new CardLayout());

        // Create and add the home panel with scroll pane
        homePanel = new JPanel(new BorderLayout());
        homeContentPanel = new JPanel();
        homeContentPanel.setLayout(new GridLayout(0, 3, 10, 10));
        cartcontrol = new CartController(con);
        displayProducts(filteredProducts); // Display initial products

        JScrollPane homeScrollPane = new JScrollPane(homeContentPanel);

        homePanel.add(searchPanel, BorderLayout.NORTH);
        homePanel.add(homeScrollPane, BorderLayout.CENTER);

        // Add the home panel to the contentPanel
        contentPanel.add(homePanel, "HomePanel");

        // Initialize profile and cart screens
        profileScreen = new ProfileScreen();
        cartScreen = new CartScreen(this);

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

    private void performSearch(String query) {
        filteredProducts.clear();
        for (ProductModel prod : product) {
            if (prod.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredProducts.add(prod);
            }
        }
        displayProducts(filteredProducts);
    }

    private void displayProducts(List<ProductModel> productsToDisplay) {
        homeContentPanel.removeAll(); // Clear the existing products
        System.out.println("----------=+++userid :"+ prof.getCustomid());
        for (ProductModel prod : productsToDisplay) {
            homeContentPanel.add(new CardGridview(new JButton("Add to Cart"), prod, e -> {
                int cartid = cartcontrol.getOrCreateCart(2);
                System.out.println(" display Cartid: " + cartid);
                if (cartid != -1) {
                    boolean isadd = cartcontrol.addItemToCart(
                            2, prod.getProductid(), prod.getQuantity()
                    );
                    if (isadd) {
                        JOptionPane.showMessageDialog(this, "Product added to cart successfully!");
                        System.out.println("Cartid: " + prof.getCustomid());
                    } else {
                        System.out.println("FailACc id : " + prof.getCustomid());
                        JOptionPane.showMessageDialog(this, "Failed to add product to cart.");
                    }
                }
            }
            ));
        }

        homeContentPanel.revalidate();
        homeContentPanel.repaint();
    }

}
