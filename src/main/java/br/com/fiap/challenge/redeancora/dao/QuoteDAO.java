package br.com.fiap.challenge.redeancora.dao;

import br.com.fiap.challenge.redeancora.model.Quote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuoteDAO {

    private final Connection connection;

    public QuoteDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Quote quote) throws SQLException {
        String sql = "INSERT INTO cotacao (id, id_mecanico, data, status) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, quote.getId());
            stmt.setString(2, quote.getMechanicId());
            stmt.setDate(3, Date.valueOf(quote.getDate()));
            stmt.setString(4, quote.getStatus());
            stmt.executeUpdate();
        }
    }

    public List<Quote> findByMechanicId(String mechanicId) throws SQLException {
        String sql = "SELECT * FROM cotacao WHERE id_mecanico = ?";
        List<Quote> list = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, mechanicId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Quote quote = new Quote(
                        rs.getString("id"),
                        rs.getString("id_mecanico"),
                        rs.getDate("data").toLocalDate(),
                        rs.getString("status")
                );
                list.add(quote);
            }
        }

        return list;
    }

    public void updateStatus(String quoteId, String newStatus) throws SQLException {
        String sql = "UPDATE cotacao SET status = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setString(2, quoteId);
            stmt.executeUpdate();
        }
    }


}
