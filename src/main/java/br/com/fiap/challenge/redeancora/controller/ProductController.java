package br.com.fiap.challenge.redeancora.controller;

import br.com.fiap.challenge.redeancora.dao.ProductDAO;
import br.com.fiap.challenge.redeancora.model.Product;
import br.com.fiap.challenge.redeancora.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductController {

    private final ProductDAO productDAO;

    public ProductController(Connection connection) {
        this.productDAO = new ProductDAO(connection);
    }

    public void createProduct(String name, String description, String brand, String application, String code, double price) throws SQLException {
        String id = Utils.generateUUID();
        Product product = new Product(id, name, description, brand, application, code, price);
        productDAO.insert(product);
    }

    public void updateProduct(Product product) throws SQLException {
        productDAO.update(product);
    }

    public void deleteProduct(String productId) throws SQLException {
        productDAO.delete(productId);
    }



    public List<Product> searchByName(String name) throws SQLException {
        return productDAO.findByName(name);
    }
}
