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

public class ProductSearch extends JFrame {

    private JTextField txtSearch;
    private JTable table;
    private DefaultTableModel tableModel;

    public ProductSearch() {
        super("Search Parts");

        setSize(900, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        JLabel lblSearch = new JLabel("Search by Name or Vehicle Code:");
        txtSearch = new JTextField();
        JButton btnSearch = new JButton("🔍 Search");

        btnSearch.addActionListener(e -> loadProducts());

        topPanel.add(lblSearch, BorderLayout.WEST);
        topPanel.add(txtSearch, BorderLayout.CENTER);
        topPanel.add(btnSearch, BorderLayout.EAST);

        // Table
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{
                "ID", "Name", "Brand", "Application", "Vehicle Code", "Price"
        });

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private void loadProducts() {
        String searchTerm = txtSearch.getText().trim();

        try {
            Connection connection = new DBConnection().getConnection();
            ProductController controller = new ProductController(connection);

            List<Product> products = controller.searchByName(searchTerm);

            tableModel.setRowCount(0); // clear table

            for (Product p : products) {
                tableModel.addRow(new Object[]{
                        p.getId(),
                        p.getName(),
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
