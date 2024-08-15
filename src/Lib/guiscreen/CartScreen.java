package Lib.guiscreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartScreen extends JPanel {
    private JFrame parentFrame; // Reference to the parent JFrame

    public CartScreen(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Add CardGridview components to the gridPanel
        JButton removeCartButton = new JButton("Remove Cart");
//        for (int i = 0; i < 10; i++) {
//            gridPanel.add(new CardGridview(new JButton(removeCartButton.getText())));
//        }

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
