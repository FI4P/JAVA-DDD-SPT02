package br.com.fiap.challenge.redeancora.view;

import br.com.fiap.challenge.redeancora.controller.OrderController;
import br.com.fiap.challenge.redeancora.controller.ProductController;
import br.com.fiap.challenge.redeancora.db.DBConnection;
import br.com.fiap.challenge.redeancora.model.OrderItem;
import br.com.fiap.challenge.redeancora.model.Product;
import br.com.fiap.challenge.redeancora.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRegister extends JFrame {

    private final User mechanic;
    private JTable tableProducts;
    private JTable tableOrderItems;
    private DefaultTableModel modelProducts;
    private DefaultTableModel modelOrderItems;
    private final List<OrderItem> orderItems = new ArrayList<>();
    private JLabel lblTotal;

    public OrderRegister(User mechanic) {
        super("Create Order");
        this.mechanic = mechanic;

        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        // Product Table
        modelProducts = new DefaultTableModel(new String[]{
                "ID", "Name", "Brand", "Application", "Vehicle Code", "Price"
        }, 0);
        tableProducts = new JTable(modelProducts);
        loadProducts();
        JScrollPane scrollProducts = new JScrollPane(tableProducts);

        // Order Items Table
        modelOrderItems = new DefaultTableModel(new String[]{
                "Product ID", "Name", "Quantity", "Unit Price", "Subtotal"
        }, 0);
        tableOrderItems = new JTable(modelOrderItems);
        JScrollPane scrollOrderItems = new JScrollPane(tableOrderItems);

        // Add product to order section
        JPanel addPanel = new JPanel(new FlowLayout());
        JTextField txtQuantity = new JTextField(5);
        JButton btnAdd = new JButton("➕ Add to Order");
        addPanel.add(new JLabel("Quantity:"));
        addPanel.add(txtQuantity);
        addPanel.add(btnAdd);

        btnAdd.addActionListener(e -> {
            int row = tableProducts.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a product first.");
                return;
            }

            try {
                int quantity = Integer.parseInt(txtQuantity.getText());
                if (quantity <= 0) throw new NumberFormatException();

                String productId = modelProducts.getValueAt(row, 0).toString();
                String name = modelProducts.getValueAt(row, 1).toString();
                double unitPrice = Double.parseDouble(modelProducts.getValueAt(row, 5).toString().replace("R$", "").replace(",", ".").trim());

                double subtotal = quantity * unitPrice;

                modelOrderItems.addRow(new Object[]{
                        productId, name, quantity, String.format("R$ %.2f", unitPrice), String.format("R$ %.2f", subtotal)
                });

                orderItems.add(new OrderItem(productId, quantity, unitPrice));
                updateTotal();

                txtQuantity.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid quantity.");
            }
        });

        // Total + Save
        JPanel bottomPanel = new JPanel(new BorderLayout());
        lblTotal = new JLabel("Total: R$ 0.00");
        JButton btnCreate = new JButton("📦 Create Order");
        bottomPanel.add(lblTotal, BorderLayout.WEST);
        bottomPanel.add(btnCreate, BorderLayout.EAST);

        btnCreate.addActionListener(e -> {
            if (orderItems.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No items added to the order.");
                return;
            }

            try {
                Connection connection = new DBConnection().getConnection();
                OrderController controller = new OrderController(connection);
                controller.createOrderDirect(mechanic.getId(), orderItems);
                JOptionPane.showMessageDialog(this, "Order created successfully!");
                dispose();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error creating order: " + ex.getMessage());
            }
        });

        // Layout
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(addPanel, BorderLayout.NORTH);
        centerPanel.add(scrollOrderItems, BorderLayout.CENTER);

        mainPanel.add(scrollProducts, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void loadProducts() {
        try {
            Connection connection = new DBConnection().getConnection();
            ProductController controller = new ProductController(connection);
            List<Product> products = controller.searchByName("");

            for (Product p : products) {
                modelProducts.addRow(new Object[]{
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

    private void updateTotal() {
        double total = 0.0;
        for (OrderItem item : orderItems) {
            total += item.getQuantity() * item.getUnitPrice();
        }
        lblTotal.setText(String.format("Total: R$ %.2f", total));
    }
}
