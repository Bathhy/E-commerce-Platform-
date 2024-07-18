package Lib.GUIScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardGridview extends JPanel {
    private int quantity = 0;

    public CardGridview() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Panel for product name and add to cart button (top row)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JLabel productname = new JLabel("Nike Shoe");
        topPanel.add(productname, BorderLayout.WEST);

        JButton addtocartButton = new JButton("Add To Cart");
        addtocartButton.setFont(new Font("Arial", Font.BOLD, 10));
        addtocartButton.setPreferredSize(new Dimension(100, 30)); // Set button size as needed
        topPanel.add(addtocartButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Center panel for main content (boxPanel)
        JPanel boxPanel = new JPanel();
        boxPanel.setPreferredSize(new Dimension(200, 100));
        boxPanel.setBackground(Color.LIGHT_GRAY);
        add(boxPanel, BorderLayout.CENTER);

        // Bottom panel for quantity controls and price label
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(controlsPanel, BorderLayout.SOUTH);
        JLabel sellername = new JLabel("Seller:Bruh");
        sellername.setForeground(Color.BLUE);
        controlsPanel.add(sellername);
        JLabel quantityLabel = new JLabel("Quantity: " + quantity);
        controlsPanel.add(quantityLabel);

        JButton removeButton = new JButton("-");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantity > 0) {
                    quantity--;
                    quantityLabel.setText("Quantity: " + quantity);
                }
            }
        });
        controlsPanel.add(removeButton);

        JButton addButton = new JButton("+");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantity++;
                quantityLabel.setText("Quantity: " + quantity);
            }
        });
        controlsPanel.add(addButton);

        JLabel textLabel = new JLabel("$169.00");
        controlsPanel.add(textLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Grid List of Cards");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(600, 600);
                JPanel mainPanel = new JPanel();
                mainPanel.setLayout(new GridLayout(3, 3, 10, 10));
                frame.add(new JScrollPane(mainPanel));
                for (int i = 0; i < 9; i++) { // Create 9 cards
                    mainPanel.add(new CardGridview());
                }
                frame.setVisible(true);
            }
        });
    }
}
