package br.com.fiap.challenge.redeancora.controller;

import br.com.fiap.challenge.redeancora.dao.PromotionDAO;
import br.com.fiap.challenge.redeancora.model.Promotion;
import br.com.fiap.challenge.redeancora.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PromotionController {

    private final PromotionDAO promotionDAO;

    public PromotionController(Connection connection) {
        this.promotionDAO = new PromotionDAO(connection);
    }

    public void createPromotion(String productId, String description, double discount, LocalDate startDate, LocalDate endDate) throws SQLException {
        String id = Utils.generateUUID();
        Promotion promo = new Promotion(id, productId, description, discount, startDate, endDate);
        promotionDAO.insert(promo);
    }

    public void deletePromotion(String promotionId) throws SQLException {
        promotionDAO.delete(promotionId);
    }


    public List<Promotion> getAllPromotions() throws SQLException {
        return promotionDAO.findAll();
    }
}
