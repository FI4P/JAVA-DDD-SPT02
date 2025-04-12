package br.com.fiap.challenge.redeancora.dao;

import br.com.fiap.challenge.redeancora.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private final Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Product product) throws SQLException {
        String sql = "INSERT INTO produto (id, name, description, brand, application, code, price) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getId());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, product.getBrand());
            stmt.setString(5, product.getApplication());
            stmt.setString(6, product.getCode());
            stmt.setDouble(7, product.getPrice());
            stmt.executeUpdate();
        }
    }

    public void update(Product product) throws SQLException {
        String sql = "UPDATE produto SET name = ?, description = ?, brand = ?, application = ?, code = ?, price = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setString(3, product.getBrand());
            stmt.setString(4, product.getApplication());
            stmt.setString(5, product.getCode());
            stmt.setDouble(6, product.getPrice());
            stmt.setString(7, product.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(String productId) throws SQLException {
        String sql = "DELETE FROM produto WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, productId);
            stmt.executeUpdate();
        }
    }


    public List<Product> findByName(String name) throws SQLException {
        String sql = "SELECT * FROM produto WHERE LOWER(name) LIKE ?";
        List<Product> products = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + name.toLowerCase() + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("brand"),
                        rs.getString("application"),
                        rs.getString("code"),
                        rs.getDouble("price")
                ));
            }
        }

        return products;
    }
}
