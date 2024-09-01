package Lib.guiscreen;

import connection.MyDBConnection;
import controller.CartController;
import model.CartModel;
import model.ProfileModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartScreen extends JPanel {
    private JFrame parentFrame;
    private List<CartModel> cart = new ArrayList<>();
    private Connection con = MyDBConnection.getInstance().getConnection();
    private CartController cartController = new CartController(con);
    private ProfileModel prof = ProfileModel.getInstance();
    private JPanel gridPanel;
    private JLabel totalPriceLabel = new JLabel();

    public CartScreen(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        initializeCartScreen();
    }

    private void initializeCartScreen() {
        gridPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        refreshCartUI();

        JScrollPane scrollPane = new JScrollPane(gridPanel);

        JLabel cartLabel = new JLabel("My Cart");
        cartLabel.setFont(new Font("Arial", Font.BOLD, 24));
        cartLabel.setForeground(Color.BLUE);
        cartLabel.setHorizontalAlignment(SwingConstants.CENTER);
        String totalprice;
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));


        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 15));
        totalPriceLabel.setForeground(Color.BLUE);
        bottomPanel.add(totalPriceLabel);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 15));
        checkoutButton.setForeground(Color.WHITE);

        Date orderDate = new Date();

        checkoutButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                if (cart.isEmpty()) {
                    JOptionPane.showMessageDialog(parentFrame, "Your cart is empty. Please add items to proceed with checkout.");
                    return;
                }
                CartModel firstCartItem = cart.get(0);
                int cartId = firstCartItem.getCartid();

                if (cartId == -1) {
                    System.out.println("Invalid cart ID. Cannot proceed with order creation.");
                    return;
                }
                 int orderId = cartController.createOrder(cartId, prof.getCustomid(),
                        new java.sql.Date(orderDate.getTime()));
                if(orderId != -1){
                    for (CartModel cartItem : cart) {
                        boolean orderItemCreated = cartController.createOrderItem(orderId,
                                cartItem.getProductid(),
                                cartItem.getQuantity(),
                                cartItem.getPrice());
                        if (!orderItemCreated) {
                            System.out.println("Failed to create order item for product ID: " + cartItem.getProductid());
                            return;
                        }
                    }

                    new CreditCardPaymentForm();
                    parentFrame.dispose();
                }
            }
        });
        bottomPanel.add(checkoutButton);

        add(cartLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void refreshCartUI() {
        gridPanel.removeAll();
        cart.clear();
        cartController.getCartInformation(cart);
        double totalPrice = 0.0;
        for (CartModel cartData : cart) {
            gridPanel.add(new CardGridview(new JButton("Remove Cart"), cartData, e -> {
                boolean removeCart = cartController.removeItemCart(cartData.getCartid(), cartData.getProductid());
                if (removeCart) {
                    JOptionPane.showMessageDialog(this, "Product Removed from cart successfully!");
                    refreshCartUI();  // Refresh the UI
                    revalidate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to remove product.");
                }
            }));
            totalPrice = cartData.getTotalcart_amount();
        }
        totalPriceLabel.setText("Total Price: $" + totalPrice);
        revalidate();
        repaint();
    }
}

