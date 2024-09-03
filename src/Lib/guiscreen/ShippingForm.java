//package Lib.guiscreen;
//
//import Extension.Extension;
//import Navigator.Navigate;
//import connection.MyDBConnection;
//import constant.Constant;
//import constant.Query;
//import controller.CartController;
//import model.CartModel;
//import model.ProfileModel;
//
//import javax.swing.*;
//import java.awt.*;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class ShippingForm extends JFrame {
//    private JTextField cityTextField;
//    private JTextField streetTextField;
//    private JTextField khanTextField;
//    private JButton shippingButton;
//    private ProfileModel prof = ProfileModel.getInstance();
//    private List<CartModel> cart = new ArrayList<>();
//    private Connection con = MyDBConnection.getInstance().getConnection();
//    private CartController cartController = new CartController(con);
//    public void addShipping(String street, String city, String khan){
//        try{
//            PreparedStatement pst = con.prepareStatement(Query.ADD_SHIPPING_INFO);
//            pst.setString(1, city);
//            pst.setString(2, street);
//            pst.setString(3, khan);
//            int res =pst.executeUpdate();
//            if(res > 0){
//                System.out.println("----,,$$:add to shipping");
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//    public ShippingForm() {
//        // Set up JFrame
//        JFrame frame = new JFrame("Shipping Form");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(Constant.screenwidth, Constant.screenheight);
//        frame.setLayout(new GridBagLayout());
//
//        // GridBagConstraints setup for layout management
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        // Street Label and TextField
//        JLabel streetLabel = new JLabel("Street:");
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 1;
//        frame.add(streetLabel, gbc);
//
//        streetTextField = new JTextField(20);
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        gbc.gridwidth = 2;
//        frame.add(streetTextField, gbc);
//
//        // Khan Label and TextField
//        JLabel khanLabel = new JLabel("Khan:");
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        gbc.gridwidth = 1;
//        frame.add(khanLabel, gbc);
//
//        khanTextField = new JTextField(20);
//        gbc.gridx = 1;
//        gbc.gridy = 1;
//        gbc.gridwidth = 2;
//        frame.add(khanTextField, gbc);
//
//        // City Label and TextField
//        JLabel cityLabel = new JLabel("City:");
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        gbc.gridwidth = 1;
//        frame.add(cityLabel, gbc);
//
//        cityTextField = new JTextField(20);
//        gbc.gridx = 1;
//        gbc.gridy = 2;
//        gbc.gridwidth = 2;
//        frame.add(cityTextField, gbc);
//
//        // Shipping Button
//        shippingButton = new JButton("Confirm Shipping");
//        gbc.gridx = 1;
//        gbc.gridy = 3;
//        gbc.gridwidth = 2;
//        frame.add(shippingButton, gbc);
//
//        // Customize the Confirm Shipping Button
//        shippingButton.setForeground(Color.WHITE); // Set text color to white
//        shippingButton.setBackground(Color.BLUE);  // Set background color to blue
//        shippingButton.setOpaque(true);            // Make sure background color is visible
//        shippingButton.setBorderPainted(false);    // Remove the border
//        Date orderDate = new Date();
//        // Button action listener
//        Navigate nav = new Navigate(frame);
//        cartController.getCartInformation(cart);
//        shippingButton.addActionListener(e -> {
//            if (cart.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Your cart is empty. Please add items to proceed with checkout.");
//                return;
//            }
//            CartModel firstCartItem = cart.get(0);
//            int cartId = firstCartItem.getCartid();
//            if (cartId == -1) {
//                System.out.println("Invalid cart ID. Cannot proceed with order creation.");
//                return;
//            }
//            int orderId = cartController.createOrder(cartId, prof.getCustomid(),
//                    new java.sql.Date(orderDate.getTime()));
//            if(orderId != -1){
//                for (CartModel cartItem : cart) {
//                    boolean orderItemCreated = cartController.createOrderItem(orderId,
//                            cartItem.getProductid(),
//                            cartItem.getQuantity(),
//                            cartItem.getPrice());
//                    if (!orderItemCreated) {
//                        System.out.println("Failed to create order item for product ID: " + cartItem.getProductid());
//                        return;
//                    }
//                }
//
//            }
//            String street = streetTextField.getText();
//            String khan = khanTextField.getText();
//            String city = cityTextField.getText();
//            if (street.isEmpty() || khan.isEmpty() || city.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
//            } else {
//                System.out.println("Shipping Details: Street - " + street + ", Khan - " + khan + ", City - " + city);
//                addShipping(city, street, khan);
//                nav.navigateOrderDetails(e);
//            }
//        });
//
//        // Show JFrame
//        frame.setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        new ShippingForm();
//    }
//}
