package Lib.guiscreen;

import connection.MyDBConnection;
import controller.CartController;
import model.CartModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CartScreen extends JPanel {
    private JFrame parentFrame; // Reference to the parent JFrame
    List<CartModel> cart = new ArrayList<>();
    Connection con = MyDBConnection.getInstance().getConnection();
    CartController cartController = new CartController(con);
    public CartScreen(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        cartController.getCartInformation(cart);
        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Add CardGridview components to the gridPanel
        JButton removeCartButton = new JButton("Remove Cart");

        for(CartModel cartdata : cart) {
            gridPanel.add(new CardGridview(removeCartButton, cartdata, e -> {

            }));
        }
        // Create a scroll pane to make the gridPanel scrollable
        JScrollPane scrollPane = new JScrollPane(gridPanel);

        // Add the cart label at the top
        JLabel cartLabel = new JLabel("My Cart");
        cartLabel.setFont(new Font("Arial", Font.BOLD, 24));
        cartLabel.setForeground(Color.BLUE);
        cartLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create the bottom panel for total price and checkout button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));

        JLabel totalPriceLabel = new JLabel("Total Price: $0.00"); // Total price
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 15));
        totalPriceLabel.setForeground(Color.BLUE);
        bottomPanel.add(totalPriceLabel);

        // Checkout button
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 15));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setBackground(Color.BLUE);
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.dispose();
                new CreditCardPaymentForm();
            }
        });
        bottomPanel.add(checkoutButton);

        // Add the components to the CartScreen
        add(cartLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
