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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MechanicPromotionList extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public MechanicPromotionList() {
        super("Active Promotions");

        setSize(850, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{
                "Product", "Description", "Discount (%)", "Start Date", "End Date"
        });

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        loadActivePromotions();

        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private void loadActivePromotions() {
        try {
            Connection connection = new DBConnection().getConnection();
            PromotionController promoController = new PromotionController(connection);
            ProductController productController = new ProductController(connection);

            Map<String, String> productMap = new HashMap<>();
            for (Product p : productController.searchByName("")) {
                productMap.put(p.getId(), p.getName() + " (" + p.getBrand() + ")");
            }

            List<Promotion> promotions = promoController.getAllPromotions();
            LocalDate today = LocalDate.now();

            tableModel.setRowCount(0);

            for (Promotion promo : promotions) {
                    String productName = productMap.getOrDefault(promo.getProductId(), "Unknown Product");

                    tableModel.addRow(new Object[]{
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
