package Lib.guiscreen;

import Extension.Extension;
import Navigator.Navigate;
import connection.MyDBConnection;
import constant.Constant;
import controller.CartController;
import controller.OrderController;
import model.CartModel;
import model.OrderDetailModel;
import model.OrderModel;
import model.ProfileModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TableWithDetails extends  JFrame{
    private Vector<OrderDetailModel> orderDetailData = new Vector<>();
    private OrderModel ord = OrderModel.getInstance();
    private final OrderController control = new OrderController();
    private ProfileModel ProfileModel = model.ProfileModel.getInstance();
    private Connection con = MyDBConnection.getInstance().getConnection();
    private CartController cartController = new CartController(con);
    private List<CartModel> cart = new ArrayList<>();
    public TableWithDetails(){

        SwingUtilities.invokeLater(() -> {
            // Create the main frame
            cartController.getCartInformation(cart);
            try {
                Extension.setFrameIcon(this, Constant.imgurl);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
            JFrame frame = new JFrame("Order Details Table");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(Constant.screenwidth, Constant.screenheight);
            frame.setLayout(new BorderLayout());
            frame.setLocationRelativeTo(null);
            // Set frame background color
            frame.getContentPane().setBackground(Color.WHITE);

            // Define column names
            String[] columnNames = {
                    "Product Name",
                    "Product Order Qty",
                    "Seller Name",
                    "Individual Product Price",
                    "Total Individual Product Price",
                    "Total Price of Order",
                    "Payment Method",
                    "Customer Username",
                    "Order Date"
            };
            DefaultTableModel mod = new DefaultTableModel(columnNames, 0);
//            orderDetailData = control.getOrdersDetail(ProfileModel.getCustomid());
            CartModel firstCartItem = cart.get(0);
            int cartId = firstCartItem.getCartid();
            System.out.println("XXXOrderID: "+ord.getOrderID());
            System.out.println("XXXuseID: "+ProfileModel.getCustomid());
            System.out.println("XXXCartID: "+cartId);
//            orderDetailData = control.getOrdersDetail(ord.getOrderID(),ProfileModel.getCustomid(),cartId);
            orderDetailData = control.getOrdersDetail(ProfileModel.getCustomid());

            for (OrderDetailModel orderDetail : orderDetailData) {
                Object[] row = new Object[]{
                        orderDetail.getProductName(),
                        orderDetail.getOrderQty(),
                        orderDetail.getSellerName(),
                        orderDetail.getIndividualPrice(),
                        orderDetail.getTotalIndividualPrice(),
                        orderDetail.getTotalAmount(),
                        orderDetail.getPaymentMethod(),
                        orderDetail.getCustomerName(),
                        orderDetail.getOrderDate()
                };
                mod.addRow(row);
            }

            // Set the model to the table
            JTable table = new JTable(mod);
            // Set table background and foreground color
            // Set table background and foreground color2
            table.setBackground(Color.WHITE);
            table.setForeground(Color.BLACK);

            // Set table header background and foreground color
            JTableHeader header = table.getTableHeader();
            header.setBackground(Color.BLUE);
            header.setForeground(Color.WHITE);

            // Set cell renderer to center-align text
            DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);

            // Add table to a scroll pane
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);


            // Add a mouse listener to the table for the popup menu
            table.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        showPopup(e);
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        showPopup(e);
                    }
                }

                private void showPopup(MouseEvent e) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        table.setRowSelectionInterval(row, row);
//                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            });
            Navigate nav = new Navigate(frame);
            JButton continueShoppingButton = new JButton("Continue Shopping");
            continueShoppingButton.setBackground(Color.BLUE);
            continueShoppingButton.setForeground(Color.WHITE);
            continueShoppingButton.addActionListener(e -> {
                control.deleteCart(ProfileModel.getCustomid());
                nav.navigateHome(e);
            });

            // Create a panel for the button and add the button to the panel
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.WHITE); // Set panel background color to match the frame
            buttonPanel.add(continueShoppingButton);

            // Add the button panel to the frame at the bottom
            frame.add(buttonPanel, BorderLayout.SOUTH);

            // Display the frame
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        new TableWithDetails();
    }
}
