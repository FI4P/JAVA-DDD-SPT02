package br.com.fiap.challenge.redeancora.view;

import br.com.fiap.challenge.redeancora.controller.ProductController;
import br.com.fiap.challenge.redeancora.db.DBConnection;
import br.com.fiap.challenge.redeancora.model.Product;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class ProductEdit extends JFrame {

    private final Product product;
    private final JTextField txtName = new JTextField();
    private final JTextField txtDescription = new JTextField();
    private final JTextField txtBrand = new JTextField();
    private final JTextField txtApplication = new JTextField();
    private final JTextField txtVehicleCode = new JTextField();
    private final JTextField txtPrice = new JTextField();

    public ProductEdit(Product product) {
        super("Edit Product");
        this.product = product;

        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));

        panel.add(new JLabel("Name:"));
        txtName.setText(product.getName());
        panel.add(txtName);

        panel.add(new JLabel("Description:"));
        txtDescription.setText(product.getDescription());
        panel.add(txtDescription);

        panel.add(new JLabel("Brand:"));
        txtBrand.setText(product.getBrand());
        panel.add(txtBrand);

        panel.add(new JLabel("Application:"));
        txtApplication.setText(product.getApplication());
        panel.add(txtApplication);

        panel.add(new JLabel("Code:"));
        txtVehicleCode.setText(product.getCode());
        panel.add(txtVehicleCode);

        panel.add(new JLabel("Price:"));
        txtPrice.setText(String.valueOf(product.getPrice()));
        panel.add(txtPrice);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> saveChanges());
        panel.add(new JLabel());
        panel.add(btnSave);

        add(panel);
        setVisible(true);
    }

    private void saveChanges() {
        try {
            product.setName(txtName.getText());
            product.setDescription(txtDescription.getText());
            product.setBrand(txtBrand.getText());
            product.setApplication(txtApplication.getText());
            product.setCode(txtVehicleCode.getText());
            product.setPrice(Double.parseDouble(txtPrice.getText()));

            Connection connection = new DBConnection().getConnection();
            ProductController controller = new ProductController(connection);
            controller.updateProduct(product);

            JOptionPane.showMessageDialog(this, "Product updated successfully!");
            dispose();

        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error updating product: " + ex.getMessage());
        }
    }
}
