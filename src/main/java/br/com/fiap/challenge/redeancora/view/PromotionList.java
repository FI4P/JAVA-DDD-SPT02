package br.com.fiap.challenge.redeancora.view;

import br.com.fiap.challenge.redeancora.controller.ProductController;
import br.com.fiap.challenge.redeancora.controller.PromotionController;
import br.com.fiap.challenge.redeancora.db.DBConnection;
import br.com.fiap.challenge.redeancora.model.Product;
import br.com.fiap.challenge.redeancora.model.Promotion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionList extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public PromotionList() {
        super("Registered Promotions");

        setSize(850, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{
                "Promotion ID", "Product", "Description", "Discount (%)", "Start Date", "End Date"
        });

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Load promotions into table
        loadPromotions();

        // Bottom panel with Delete button
        JPanel bottomPanel = new JPanel();
        JButton btnDelete = new JButton("🗑️ Delete Selected Promotion");

        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a promotion to delete.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this promotion?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    String promotionId = tableModel.getValueAt(selectedRow, 0).toString();

                    Connection connection = new DBConnection().getConnection();
                    PromotionController controller = new PromotionController(connection);
                    controller.deletePromotion(promotionId);

                    tableModel.removeRow(selectedRow); // Remove row from UI
                    JOptionPane.showMessageDialog(this, "Promotion deleted successfully!");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error deleting promotion: " + ex.getMessage());
                }
            }
        });

        bottomPanel.add(btnDelete);

        // Layout
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadPromotions() {
        try {
            Connection connection = new DBConnection().getConnection();

            PromotionController promoController = new PromotionController(connection);
            ProductController productController = new ProductController(connection);

            // Map productId → "name (brand)"
            Map<String, String> productMap = new HashMap<>();
            for (Product p : productController.searchByName("")) {
                String display = p.getName() + " (" + p.getBrand() + ")";
                productMap.put(p.getId(), display);
            }

            List<Promotion> promotions = promoController.getAllPromotions();
            tableModel.setRowCount(0); // Clear existing rows

            for (Promotion promo : promotions) {
                String productName = productMap.getOrDefault(promo.getProductId(), "Unknown Product");

                tableModel.addRow(new Object[]{
                        promo.getId(),
                        productName,
                        promo.getDescription(),
                        String.format("%.2f%%", promo.getDiscountPercent()),
                        promo.getStartDate(),
                        promo.getEndDate()
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading promotions: " + e.getMessage());
        }
    }
}
