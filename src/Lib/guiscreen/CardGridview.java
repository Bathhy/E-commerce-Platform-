package Lib.guiscreen;

import constant.Constant;
import model.ProductModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CardGridview extends JPanel {
    public CardGridview(JButton actbutton, ProductModel productModel) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(300, 150));

        // Top Panel for product name and add to cart button (top row)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JLabel productname = new JLabel(productModel.getProductname());
        topPanel.add(productname, BorderLayout.WEST);

        actbutton.setFont(new Font("Arial", Font.BOLD, 10));
        actbutton.setPreferredSize(new Dimension(100, 30)); // Set button size as needed
        topPanel.add(actbutton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Center Panel for the product image (boxPanel)
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

        // Bottom Panel for quantity controls and price label
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));

        JPanel firstRowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel sellername = new JLabel("Seller: " + productModel.getSellername());
        sellername.setForeground(Color.BLUE);
        firstRowPanel.add(sellername);

        JLabel quantityLabel = new JLabel("Quantity: " + productModel.getQuantity());
        firstRowPanel.add(quantityLabel);

        controlsPanel.add(firstRowPanel);

        JPanel secondRowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton removeButton = new JButton("-");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productModel.decreaseQuantity();
                quantityLabel.setText("Quantity: " + productModel.getQuantity());
            }
        });
        secondRowPanel.add(removeButton);

        JButton addButton = new JButton("+");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productModel.increaseQuantity();
                quantityLabel.setText("Quantity: " + productModel.getQuantity());
            }
        });
        secondRowPanel.add(addButton);

        JLabel priceLabel = new JLabel("$ " + productModel.getPrice());
        secondRowPanel.add(priceLabel);

        controlsPanel.add(secondRowPanel);

        add(controlsPanel, BorderLayout.SOUTH);
    }
}

