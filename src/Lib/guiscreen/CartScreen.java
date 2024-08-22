package Lib.guiscreen;

import connection.MyDBConnection;
import controller.CartController;
import model.CartModel;
import model.ProductModel;
import model.ProfileModel;
import model.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CartScreen extends JPanel {
    private JFrame parentFrame;
    private List<CartModel> cart = new ArrayList<>();
    private Connection con = MyDBConnection.getInstance().getConnection();
    private CartController cartController = new CartController(con);
    private JPanel gridPanel;

    public CartScreen(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        initializeCartScreen();
    }

    private void initializeCartScreen() {
        gridPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        refreshCartUI();  // Initial population of grid panel

        JScrollPane scrollPane = new JScrollPane(gridPanel);

        JLabel cartLabel = new JLabel("My Cart");
        cartLabel.setFont(new Font("Arial", Font.BOLD, 24));
        cartLabel.setForeground(Color.BLUE);
        cartLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        JLabel totalPriceLabel = new JLabel("Total Price: $"+cart.getFirst().getTotalcart_amount());
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 15));
        totalPriceLabel.setForeground(Color.BLUE);
        bottomPanel.add(totalPriceLabel);

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

        add(cartLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void refreshCartUI() {
        gridPanel.removeAll();
        cart.clear();
        cartController.getCartInformation(cart);
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
        }
        revalidate();
        repaint();
    }
}

