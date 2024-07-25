package Lib.GUIScreen;

import constant.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CardGridview extends JPanel {
    private int quantity = 0;

    public CardGridview(JButton actbutton) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(300, 150));

        // Panel for product name and add to cart button (top row)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JLabel productname = new JLabel("Nike Shoe");
        topPanel.add(productname, BorderLayout.WEST);

        actbutton.setFont(new Font("Arial", Font.BOLD, 10));
        actbutton.setPreferredSize(new Dimension(100, 30)); // Set button size as needed
        topPanel.add(actbutton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Center panel for main content (boxPanel)
        JPanel boxPanel = new JPanel();
        boxPanel.setPreferredSize(new Dimension(200, 100));

        // Load and scale the image
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(new File(Constant.fakeimage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (originalImage != null) {
            Image scaledImage = originalImage.getScaledInstance(200, 100, Image.SCALE_AREA_AVERAGING);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel iconLabel = new JLabel(scaledIcon);
            boxPanel.add(iconLabel);
        }

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
}
