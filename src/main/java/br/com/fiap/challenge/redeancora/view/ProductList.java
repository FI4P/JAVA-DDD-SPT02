package br.com.fiap.challenge.redeancora.view;

import br.com.fiap.challenge.redeancora.controller.ProductController;
import br.com.fiap.challenge.redeancora.db.DBConnection;
import br.com.fiap.challenge.redeancora.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductList extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public ProductList() {
        super("Registered Products");

        setSize(900, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{
                "ID", "Name", "Description", "Brand", "Application", "Vehicle Code", "Price"
        });

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Load product data into table
        loadProducts();

        // Create panel for bottom buttons
        JPanel bottomPanel = new JPanel();

        // Edit button
        JButton btnEdit = new JButton("✏️ Edit Selected Product");
        btnEdit.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a product to edit.");
                return;
            }

            try {
                Product selectedProduct = new Product(
                        tableModel.getValueAt(selectedRow, 0).toString(),
                        tableModel.getValueAt(selectedRow, 1).toString(),
                        tableModel.getValueAt(selectedRow, 2).toString(),
                        tableModel.getValueAt(selectedRow, 3).toString(),
                        tableModel.getValueAt(selectedRow, 4).toString(),
                        tableModel.getValueAt(selectedRow, 5).toString(),
                        Double.parseDouble(tableModel.getValueAt(selectedRow, 6).toString()
                                .replace("R$", "")
                                .replace(",", ".")
                                .trim())
                );

                new ProductEdit(selectedProduct);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error loading product for editing: " + ex.getMessage());
            }
        });

        // Delete button
        JButton btnDelete = new JButton("🗑️ Delete Selected Product");
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a product to delete.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this product?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    String productId = tableModel.getValueAt(selectedRow, 0).toString();
                    Connection connection = new DBConnection().getConnection();
                    ProductController controller = new ProductController(connection);
                    controller.deleteProduct(productId);

                    JOptionPane.showMessageDialog(this, "Product deleted successfully!");
                    tableModel.removeRow(selectedRow); // Remove from visual table

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error deleting product: " + ex.getMessage());
                }
            }
        });

        // Add buttons to bottom panel
        bottomPanel.add(btnEdit);
        bottomPanel.add(btnDelete);

        // Add components to frame
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadProducts() {
        try {
            Connection connection = new DBConnection().getConnection();
            ProductController controller = new ProductController(connection);
            List<Product> products = controller.searchByName(""); // empty string = all

            tableModel.setRowCount(0); // Clear current table rows

            for (Product p : products) {
                tableModel.addRow(new Object[]{
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getBrand(),
                        p.getApplication(),
                        p.getCode(),
                        String.format("R$ %.2f", p.getPrice())
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading products: " + e.getMessage());
        }
    }
}
