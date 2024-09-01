package Lib.guiscreen;

import Navigator.Navigate;
import constant.Constant;
import controller.OrderController;
import model.OrderDetailModel;
import model.ProfileModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class TableWithDetails extends  JFrame{
    private Vector<OrderDetailModel> orderDetailData = new Vector<>();
    private final OrderController control = new OrderController();
    private ProfileModel ProfileModel = new ProfileModel();
    public TableWithDetails(){

        SwingUtilities.invokeLater(() -> {
            // Create the main frame
            JFrame frame = new JFrame("Order Details Table");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(Constant.screenwidth, Constant.screenheight);
            frame.setLayout(new BorderLayout());

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
//            orderDetailData = control.getOrdersDetail(2);
//            // Define sample data
////            Object[][] data = {
////                    {"Product 1", 5, "Seller A", 20.0, 100.0, 100.0, "Credit Card", "user1", "2024-07-01"},
////                    {"Product 2", 2, "Seller B", 15.0, 30.0, 30.0, "PayPal", "user2", "2024-07-02"},
////                    {"Product 3", 10, "Seller C", 25.0, 250.0, 250.0, "Debit Card", "user3", "2024-07-03"}
////            };
//
//            // Create a JTable with data and column names
//            JTable table = new JTable();
//            DefaultTableModel mod = (DefaultTableModel) table.getModel();
//            mod.setRowCount(0);
//            mod.addRow(orderDetailData);
//            table.setModel(mod);
            DefaultTableModel mod = new DefaultTableModel(columnNames, 0);

            // Retrieve data from controller
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

            // Create a popup menu
//            JPopupMenu popupMenu = new JPopupMenu();
//
//            JMenuItem editProduct = new JMenuItem("Edit Product");
//            JMenuItem changeFeature = new JMenuItem("Change Feature");
//            JMenuItem addProduct = new JMenuItem("Add Product");
//            JMenuItem deleteProduct = new JMenuItem("Delete Product");
//            JMenuItem copyProduct = new JMenuItem("Copy Product");
//            JMenuItem pasteProduct = new JMenuItem("Paste Product");
//            JMenuItem selectAllProducts = new JMenuItem("Select All Products");
//            JMenuItem copySelectedProducts = new JMenuItem("Copy Selected Products");
//            JMenuItem exportSelectedProducts = new JMenuItem("Export Selected Products");
//            JMenuItem printSelectedProducts = new JMenuItem("Print Selected Products");
//            JMenuItem printSelectedProductsPDF = new JMenuItem("Print Selected Products as PDF");
//
//            popupMenu.add(editProduct);
//            popupMenu.add(changeFeature);
//            popupMenu.add(addProduct);
//            popupMenu.add(deleteProduct);
//            popupMenu.addSeparator();
//            popupMenu.add(copyProduct);
//            popupMenu.add(pasteProduct);
//            popupMenu.addSeparator();
//            popupMenu.add(selectAllProducts);
//            popupMenu.add(copySelectedProducts);
//            popupMenu.add(exportSelectedProducts);
//            popupMenu.add(printSelectedProducts);
//            popupMenu.add(printSelectedProductsPDF);

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
