package br.com.fiap.challenge.redeancora.view;

import br.com.fiap.challenge.redeancora.controller.ProductController;
import br.com.fiap.challenge.redeancora.controller.PromotionController;
import br.com.fiap.challenge.redeancora.db.DBConnection;
import br.com.fiap.challenge.redeancora.model.Product;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionRegister extends JFrame {

    private JComboBox<String> cbProducts;
    private JTextField txtDescription;
    private JTextField txtDiscount;
    private JTextField txtStartDate;
    private JTextField txtEndDate;

    private final Map<String, String> productMap = new HashMap<>();

    public PromotionRegister() {
        super("Register Promotion");

        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));

        // Components
        panel.add(new JLabel("Product:"));
        cbProducts = new JComboBox<>();
        loadProducts(); // fills cbProducts
        panel.add(cbProducts);

        panel.add(new JLabel("Description:"));
        txtDescription = new JTextField();
        panel.add(txtDescription);

        panel.add(new JLabel("Discount %:"));
        txtDiscount = new JTextField();
        panel.add(txtDiscount);

        panel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        txtStartDate = new JTextField();
        panel.add(txtStartDate);

        panel.add(new JLabel("End Date (YYYY-MM-DD):"));
        txtEndDate = new JTextField();
        panel.add(txtEndDate);

        JButton btnRegister = new JButton("Create Promotion");
        btnRegister.addActionListener(e -> createPromotion());

        panel.add(new JLabel());
        panel.add(btnRegister);

        add(panel);
        setVisible(true);
    }

    private void loadProducts() {
        try {
            Connection connection = new DBConnection().getConnection();
            ProductController controller = new ProductController(connection);
            List<Product> products = controller.searchByName("");

            for (Product p : products) {
                String display = p.getName() + " (" + p.getBrand() + ")";
                cbProducts.addItem(display);
                productMap.put(display, p.getId());
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading products: " + e.getMessage());
        }
    }

    private void createPromotion() {
        try {
            String selectedProduct = (String) cbProducts.getSelectedItem();
            if (selectedProduct == null || !productMap.containsKey(selectedProduct)) {
                JOptionPane.showMessageDialog(this, "Please select a valid product.");
                return;
            }

            String productId = productMap.get(selectedProduct);
            String description = txtDescription.getText();
            double discount = Double.parseDouble(txtDiscount.getText());
            LocalDate start = LocalDate.parse(txtStartDate.getText());
            LocalDate end = LocalDate.parse(txtEndDate.getText());

            Connection connection = new DBConnection().getConnection();
            PromotionController controller = new PromotionController(connection);
            controller.createPromotion(productId, description, discount, start, end);

            JOptionPane.showMessageDialog(this, "Promotion registered successfully!");
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error registering promotion: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PromotionRegister::new);
    }
}
