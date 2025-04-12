package br.com.fiap.challenge.redeancora.dao;

import br.com.fiap.challenge.redeancora.model.Promotion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromotionDAO {

    private final Connection connection;

    public PromotionDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Promotion promo) throws SQLException {
        String sql = "INSERT INTO promocao (id, id_produto, descricao, desconto_percentual, data_inicio, data_fim) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, promo.getId());
            stmt.setString(2, promo.getProductId());
            stmt.setString(3, promo.getDescription());
            stmt.setDouble(4, promo.getDiscountPercent());
            stmt.setDate(5, Date.valueOf(promo.getStartDate()));
            stmt.setDate(6, Date.valueOf(promo.getEndDate()));
            stmt.executeUpdate();
        }
    }

    public void delete(String promotionId) throws SQLException {
        String sql = "DELETE FROM promocao WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, promotionId);
            stmt.executeUpdate();
        }
    }

    public List<Promotion> findAll() throws SQLException {
        String sql = "SELECT * FROM promocao";
        List<Promotion> list = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Promotion(
                        rs.getString("id"),
                        rs.getString("id_produto"),
                        rs.getString("descricao"),
                        rs.getDouble("desconto_percentual"),
                        rs.getDate("data_inicio").toLocalDate(),
                        rs.getDate("data_fim").toLocalDate()
                ));
            }
        }

        return list;
    }
}
