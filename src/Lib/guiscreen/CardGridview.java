package Lib.guiscreen;

import model.CartModel;
import model.ProductModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

public class CardGridview extends JPanel {


    private int quantity = 0;

    public CardGridview(JButton actionButton, Object model, ActionListener onClick) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(300, 150));
        // Determine model type
        String productName = "";
        double price = 0;
        String productImage = "";
        String sellerName = "";


        if (model instanceof CartModel) {
            CartModel cartModel = (CartModel) model;
            productName = cartModel.getProductname();
            price = cartModel.getPrice();
            productImage = cartModel.getProductimages();
            sellerName = cartModel.getSellerName();
            quantity = cartModel.getQuantity();
        } else if (model instanceof ProductModel) {
            ProductModel productModel = (ProductModel) model;
            productName = productModel.getProductname();
            price = productModel.getPrice();
            productImage = productModel.getProductImage();
            sellerName = productModel.getSellerName();
            quantity = productModel.getQuantity();
        }
        ProductModel productModel;
        if (model instanceof ProductModel) {
            productModel = (ProductModel) model;
        } else {
            productModel = null;
        }
        // Top Panel for product name and action button (top row)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JLabel productNameLabel = new JLabel(productName);
        topPanel.add(productNameLabel, BorderLayout.WEST);

        actionButton.setFont(new Font("Arial", Font.BOLD, 10));
        actionButton.setPreferredSize(new Dimension(100, 30));
        actionButton.addActionListener(onClick);
        topPanel.add(actionButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Center Panel for the product image (boxPanel)
        JPanel boxPanel = new JPanel();
        boxPanel.setPreferredSize(new Dimension(200, 100));

        // Load and scale the image
        BufferedImage originalImage = null;
        try {
//            String formattedPath = productImage.replace("\\", "/");
            File imageFile = new File(productImage);

            if (imageFile.exists()) {
                originalImage = ImageIO.read(imageFile);
            } else {
                System.err.println("Image file not found: " + productImage);
            }
        } catch (IOException e) {
            System.err.println("Error reading the image file: " + e.getMessage());
        }

        if (originalImage != null) {
            Image scaledImage = originalImage.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel iconLabel = new JLabel(scaledIcon);
            boxPanel.add(iconLabel);
        } else {
            boxPanel.add(new JLabel("Image could not be read."));
        }


        add(boxPanel, BorderLayout.CENTER);

        // Bottom Panel for quantity controls and price label
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));

        JPanel firstRowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel sellerNameLabel = new JLabel("Seller: " + sellerName);
        sellerNameLabel.setForeground(Color.BLUE);
        firstRowPanel.add(sellerNameLabel);

        JLabel quantityLabel = new JLabel("Quantity: " + quantity );
        firstRowPanel.add(quantityLabel);

        controlsPanel.add(firstRowPanel);

        JPanel secondRowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton removeButton = new JButton("-");
        removeButton.addActionListener(e -> {
            productModel.decreaseQuantity();
            quantityLabel.setText("Quantity: " + productModel.getQuantity());
        });
        secondRowPanel.add(removeButton);

        JButton addButton = new JButton("+");
        addButton.addActionListener(e -> {
            productModel.increaseQuantity();
            quantityLabel.setText("Quantity: " + productModel.getQuantity());
        });
        secondRowPanel.add(addButton);

        JLabel priceLabel = new JLabel("$ " + price);
        secondRowPanel.add(priceLabel);

        controlsPanel.add(secondRowPanel);

        add(controlsPanel, BorderLayout.SOUTH);
    }
}
