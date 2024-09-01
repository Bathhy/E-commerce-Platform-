package Lib.guiscreen;

import Navigator.Navigate;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import connection.MyDBConnection;
import constant.Constant;
import constant.Query;
import controller.PaymentController;
import model.OrderModel;
import model.ProfileModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCardPaymentForm extends JFrame{
    PaymentController paycontrol = new PaymentController();
    private JRadioButton visaButton;
    private JRadioButton mastercardButton;
    private JRadioButton discoverButton;
    private OrderModel orderModel = new OrderModel();
    private ProfileModel prf = new ProfileModel();
    public int getOrder(int customerId) {
        Connection con = MyDBConnection.getInstance().getConnection();
        try {
            PreparedStatement pst = con.prepareStatement(Query.GET_ORDER);
            pst.setInt(1, 2);
            ResultSet res = pst.executeQuery();
            if (res.next()) { // Ensure we check if there is any order
                int id = res.getInt("order_id"); // Fetch order_id
                orderModel.setOrderID(id);
                System.out.println("XXXXXXX Order ID fetched from DB: " + id);
                return id; // Return the fetched order ID
            } else {
                System.out.println("No order found for customer ID: " + customerId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public  CreditCardPaymentForm() {


        int id =getOrder(prf.getCustomid());


        JFrame frame = new JFrame("Credit Card Payment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constant.screenwidth, Constant.screenheight); // Adjusted size to fit the new layout
        frame.setLayout(new GridBagLayout());

        // Create a GridBagConstraints object for layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;



        // Payment Method
        JLabel paymentMethodLabel = new JLabel("Payment Method");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        frame.add(paymentMethodLabel, gbc);

        JPanel cardPanel = new JPanel(new GridLayout(1, 3, 5, 5)); // Adjusted for 3 buttons
         visaButton = new JRadioButton("Visa");
         mastercardButton = new JRadioButton("Mastercard");
         discoverButton = new JRadioButton("Discover");

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

        DatePickerSettings dateSetting = new DatePickerSettings();
        dateSetting.setFormatForDatesCommonEra("yyyy-MM-dd");
        DatePicker datePicker = new DatePicker(dateSetting);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 2;

        frame.add(datePicker, gbc);

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
        Navigate nav = new Navigate(frame);
        // Handle button clicks
        ownerField.setText("Goldamy");
        phoneField.setText("012345435");
        cardNumberField.setText("992-11-168");
        cvvField.setText("16879");

        confirmPaymentButton.addActionListener(e -> {
            // Retrieve input values
            String owner = ownerField.getText();
            String phone = phoneField.getText();
            String cardNumber = cardNumberField.getText();
            String cvv = cvvField.getText();
            String cardExpireDate = datePicker.getText();

            // Print or process the payment details
            System.out.println("Confirm Payment button clicked");
            System.out.println("Phone: " + phone);
            System.out.println("Card Number: " + cardNumber);
            System.out.println("CVV: " + cvv);
            System.out.println("Date picker data :"+cardExpireDate);

            int paymentType = getRadioPaymentType();
            if(phone.isEmpty()|| cardNumber.isEmpty()||cvv.isEmpty()||cardExpireDate.isEmpty() ||paymentType == 0 ){
                JOptionPane.showMessageDialog(this
                        ,"Please Fill all the information");
            }
            Boolean isAddPayment = paycontrol.createPayment(id
                    , paymentType,cardNumber, cvv,cardExpireDate
            );
            System.out.println("===========> order id"+orderModel.getOrderID());
            if(isAddPayment){
                System.out.println("Payment done nav to order detail");
                nav.navigateOrderDetails(e);
            }else{
                System.out.println("error");
            }
        });

        frame.setVisible(true);
    }
    private int getRadioPaymentType(){
        if(visaButton.isSelected()){
            System.out.println("1");
            return  1;
        }
        if(mastercardButton.isSelected()){
            System.out.println("radio 3");
            return  2;
        }
        if(discoverButton.isSelected()){
            System.out.println("radio 3");
            return  3;
        }
        return  -1;
    }

    public static void main(String[] args) {
        new CreditCardPaymentForm();
    }
}
