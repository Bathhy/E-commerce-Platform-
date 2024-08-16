package Lib.guiscreen;
import Navigator.Navigate;
import constant.Constant;

import javax.swing.*;
import java.awt.*;

public class CreditCardPaymentForm extends JFrame{
    Navigate nav = new Navigate(this);
    public  CreditCardPaymentForm() {
        // Create the main frame
        JFrame frame = new JFrame("Credit Card Payment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constant.screenwidth, Constant.screenheight); // Adjusted size to fit the new layout
        frame.setLayout(new GridBagLayout());

        // Create a GridBagConstraints object for layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Error label
        JLabel errorLabel = new JLabel("Error: Card type name not specified!");
        errorLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(errorLabel, gbc);

        // Payment Method
        JLabel paymentMethodLabel = new JLabel("Payment Method");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        frame.add(paymentMethodLabel, gbc);

        JPanel cardPanel = new JPanel(new GridLayout(1, 3, 5, 5)); // Adjusted for 3 buttons
        JRadioButton visaButton = new JRadioButton("Visa");
        JRadioButton mastercardButton = new JRadioButton("Mastercard");
        JRadioButton discoverButton = new JRadioButton("Discover");

        ButtonGroup cardGroup = new ButtonGroup();
        cardGroup.add(visaButton);
        cardGroup.add(mastercardButton);
        cardGroup.add(discoverButton);

        cardPanel.add(visaButton);
        cardPanel.add(mastercardButton);
        cardPanel.add(discoverButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        frame.add(cardPanel, gbc);

        // Owner Name
        JLabel ownerLabel = new JLabel("Name");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        frame.add(ownerLabel, gbc);

        JTextField ownerField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        frame.add(ownerField, gbc);

        // Phone Number
        JLabel phoneLabel = new JLabel("Phone Number");
        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(phoneLabel, gbc);

        JTextField phoneField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        frame.add(phoneField, gbc);

        // Credit Card Number
        JLabel numberLabel = new JLabel("Card Number");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        frame.add(numberLabel, gbc);

        JTextField cardNumberField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        frame.add(cardNumberField, gbc);

        // CVV
        JLabel cvvLabel = new JLabel("CVV");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        frame.add(cvvLabel, gbc);

        JTextField cvvField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 6;
        frame.add(cvvField, gbc);

        // Expiration Date
        JLabel expirationLabel = new JLabel("Expiration Date");
        gbc.gridx = 0;
        gbc.gridy = 7;
        frame.add(expirationLabel, gbc);

        JPanel expirationPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JTextField monthField = new JTextField();
        JTextField yearField = new JTextField();
        expirationPanel.add(new JLabel("Month:"));
        expirationPanel.add(monthField);
        expirationPanel.add(new JLabel("Year:"));
        expirationPanel.add(yearField);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        frame.add(expirationPanel, gbc);

        // Confirm Payment Button
        JButton confirmPaymentButton = new JButton("Confirm Payment");
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        frame.add(confirmPaymentButton, gbc);

        // Customize the Confirm Payment Button
        confirmPaymentButton.setForeground(Color.WHITE); // Set text color to white
        confirmPaymentButton.setBackground(Color.BLUE);  // Set background color to blue
        confirmPaymentButton.setOpaque(true);            // Make sure background color is visible
        confirmPaymentButton.setBorderPainted(false);    // Remove the border

        // Handle button clicks
        confirmPaymentButton.addActionListener(e -> {
            // Retrieve input values
            String owner = ownerField.getText();
            String phone = phoneField.getText();
            String cardNumber = cardNumberField.getText();
            String cvv = cvvField.getText();
            String month = monthField.getText();
            String year = yearField.getText();

            // Print or process the payment details
            System.out.println("Confirm Payment button clicked");
            System.out.println("Owner: " + owner);
            System.out.println("Phone: " + phone);
            System.out.println("Card Number: " + cardNumber);
            System.out.println("CVV: " + cvv);
            System.out.println("Expiration Date: " + month + "/" + year);
            nav.navigateOrderDetails(e);
            // Add any additional validation or processing logic here
        });

        // Display the frame
        frame.setVisible(true);
    }

}