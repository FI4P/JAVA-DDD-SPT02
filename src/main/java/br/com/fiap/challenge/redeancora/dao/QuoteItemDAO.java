package br.com.fiap.challenge.redeancora.dao;

import br.com.fiap.challenge.redeancora.model.QuoteItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuoteItemDAO {

    private final Connection connection;

    public QuoteItemDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(QuoteItem item) throws SQLException {
        String sql = "INSERT INTO item_cotacao (id_cotacao, id_produto, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, item.getQuoteId());
            stmt.setString(2, item.getProductId());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getUnitPrice());
            stmt.executeUpdate();
        }
    }

    public List<QuoteItem> findByQuoteId(String quoteId) throws SQLException {
        String sql = "SELECT * FROM item_cotacao WHERE id_cotacao = ?";
        List<QuoteItem> items = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, quoteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                QuoteItem item = new QuoteItem(
                        rs.getString("id_produto"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco_unitario")
                );
                item.setQuoteId(rs.getString("id_cotacao"));
                items.add(item);
            }
        }

        return items;
    }
}
