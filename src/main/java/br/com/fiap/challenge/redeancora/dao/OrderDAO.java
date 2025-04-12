package br.com.fiap.challenge.redeancora.dao;

import br.com.fiap.challenge.redeancora.model.Order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO {

    private final Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Order order) throws SQLException {
        String sql = "INSERT INTO pedido (id, id_mecanico, data, status) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, order.getId());
            stmt.setString(2, order.getMechanicId());
            stmt.setDate(3, Date.valueOf(order.getDate()));
            stmt.setString(4, order.getStatus());
            stmt.executeUpdate();
        }
    }
}
