package br.com.fiap.challenge.redeancora.controller;

import br.com.fiap.challenge.redeancora.dao.QuoteDAO;
import br.com.fiap.challenge.redeancora.dao.QuoteItemDAO;
import br.com.fiap.challenge.redeancora.model.Quote;
import br.com.fiap.challenge.redeancora.model.QuoteItem;
import br.com.fiap.challenge.redeancora.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class QuoteController {

    private final QuoteDAO quoteDAO;
    private final QuoteItemDAO quoteItemDAO;

    public QuoteController(Connection connection) {
        this.quoteDAO = new QuoteDAO(connection);
        this.quoteItemDAO = new QuoteItemDAO(connection);
    }

    public void createQuote(String mechanicId, List<QuoteItem> items) throws SQLException {
        String id = Utils.generateUUID();
        LocalDate today = LocalDate.now();
        String status = "PENDENTE";

        Quote quote = new Quote(id, mechanicId, today, status);
        quoteDAO.insert(quote);

        for (QuoteItem item : items) {
            item.setQuoteId(id);
            quoteItemDAO.insert(item);
        }
    }

    public List<Quote> findQuotesByMechanic(String mechanicId) throws SQLException {
        return quoteDAO.findByMechanicId(mechanicId);
    }



}
