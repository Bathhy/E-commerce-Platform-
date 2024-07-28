package ScreenOrder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TableWithDetails {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Order Details Table");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new BorderLayout());

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

            // Define sample data
            Object[][] data = {
                    {"Product 1", 5, "Seller A", 20.0, 100.0, 100.0, "Credit Card", "user1", "2024-07-01"},
                    {"Product 2", 2, "Seller B", 15.0, 30.0, 30.0, "PayPal", "user2", "2024-07-02"},
                    {"Product 3", 10, "Seller C", 25.0, 250.0, 250.0, "Debit Card", "user3", "2024-07-03"}
            };

            // Create a JTable with data and column names
            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);

            // Create a popup menu
            JPopupMenu popupMenu = new JPopupMenu();

            JMenuItem editProduct = new JMenuItem("Edit Product");
            JMenuItem changeFeature = new JMenuItem("Change Feature");
            JMenuItem addProduct = new JMenuItem("Add Product");
            JMenuItem deleteProduct = new JMenuItem("Delete Product");
            JMenuItem copyProduct = new JMenuItem("Copy Product");
            JMenuItem pasteProduct = new JMenuItem("Paste Product");
            JMenuItem selectAllProducts = new JMenuItem("Select All Products");
            JMenuItem copySelectedProducts = new JMenuItem("Copy Selected Products");
            JMenuItem exportSelectedProducts = new JMenuItem("Export Selected Products");
            JMenuItem printSelectedProducts = new JMenuItem("Print Selected Products");
            JMenuItem printSelectedProductsPDF = new JMenuItem("Print Selected Products as PDF");

            popupMenu.add(editProduct);
            popupMenu.add(changeFeature);
            popupMenu.add(addProduct);
            popupMenu.add(deleteProduct);
            popupMenu.addSeparator();
            popupMenu.add(copyProduct);
            popupMenu.add(pasteProduct);
            popupMenu.addSeparator();
            popupMenu.add(selectAllProducts);
            popupMenu.add(copySelectedProducts);
            popupMenu.add(exportSelectedProducts);
            popupMenu.add(printSelectedProducts);
            popupMenu.add(printSelectedProductsPDF);

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
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            });

            // Display the frame
            frame.setVisible(true);
        });
    }
}
