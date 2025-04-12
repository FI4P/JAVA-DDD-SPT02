package br.com.fiap.challenge.redeancora.view;

import br.com.fiap.challenge.redeancora.controller.ProductController;
import br.com.fiap.challenge.redeancora.db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class ProductRegister extends JFrame {

    private JTextField txtName;
    private JTextField txtDescription;
    private JTextField txtBrand;
    private JTextField txtApplication;
    private JTextField txtCode;
    private JTextField txtPrice;

    public ProductRegister() {
        super("Product Registration");

        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));

        // Labels and inputs
        panel.add(new JLabel("Name:"));
        txtName = new JTextField();
        panel.add(txtName);

        panel.add(new JLabel("Description:"));
        txtDescription = new JTextField();
        panel.add(txtDescription);

        panel.add(new JLabel("Brand:"));
        txtBrand = new JTextField();
        panel.add(txtBrand);

        panel.add(new JLabel("Application:"));
        txtApplication = new JTextField();
        panel.add(txtApplication);

        panel.add(new JLabel("Code:"));
        txtCode = new JTextField();
        panel.add(txtCode);

        panel.add(new JLabel("Price:"));
        txtPrice = new JTextField();
        panel.add(txtPrice);

        // Buttons
        JButton btnRegister = new JButton("Register Product");
        btnRegister.addActionListener(e -> registerProduct());

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(e -> clearFields());

        panel.add(btnClear);
        panel.add(btnRegister);

        add(panel);
        setVisible(true);
    }

    private void registerProduct() {
        String name = txtName.getText();
        String description = txtDescription.getText();
        String brand = txtBrand.getText();
        String application = txtApplication.getText();
        String code = txtCode.getText();

        double price;
        try {
            price = Double.parseDouble(txtPrice.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price format.");
            return;
        }

        try {
            Connection connection = new DBConnection().getConnection();
            ProductController controller = new ProductController(connection);
            controller.createProduct(name, description, brand, application, code, price);
            JOptionPane.showMessageDialog(this, "Product registered successfully!");
            clearFields();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error registering product: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtDescription.setText("");
        txtBrand.setText("");
        txtApplication.setText("");
        txtCode.setText("");
        txtPrice.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProductRegister::new);
    }
}
