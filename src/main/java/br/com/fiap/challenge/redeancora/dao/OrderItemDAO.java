package br.com.fiap.challenge.redeancora.dao;

import br.com.fiap.challenge.redeancora.model.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemDAO {

    private final Connection connection;

    public OrderItemDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(OrderItem item) throws SQLException {
        String sql = "INSERT INTO item_pedido (id_pedido, id_produto, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, item.getOrderId());
            stmt.setString(2, item.getProductId());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getUnitPrice());
            stmt.executeUpdate();
        }
    }
}
