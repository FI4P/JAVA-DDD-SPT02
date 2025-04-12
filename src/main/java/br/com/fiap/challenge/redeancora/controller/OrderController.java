package br.com.fiap.challenge.redeancora.controller;

import br.com.fiap.challenge.redeancora.dao.OrderDAO;
import br.com.fiap.challenge.redeancora.dao.OrderItemDAO;
import br.com.fiap.challenge.redeancora.dao.QuoteDAO;
import br.com.fiap.challenge.redeancora.dao.QuoteItemDAO;
import br.com.fiap.challenge.redeancora.model.Order;
import br.com.fiap.challenge.redeancora.model.OrderItem;
import br.com.fiap.challenge.redeancora.model.QuoteItem;
import br.com.fiap.challenge.redeancora.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderController {

    private final OrderDAO orderDAO;
    private final OrderItemDAO orderItemDAO;
    private final QuoteItemDAO quoteItemDAO;
    private final QuoteDAO quoteDAO;

    public OrderController(Connection connection) {
        this.orderDAO = new OrderDAO(connection);
        this.orderItemDAO = new OrderItemDAO(connection);
        this.quoteItemDAO = new QuoteItemDAO(connection);
        this.quoteDAO = new QuoteDAO(connection);
    }

    public void convertQuoteToOrder(String quoteId, String mechanicId) throws SQLException {
        // 1. Criar pedido
        String orderId = Utils.generateUUID();
        LocalDate today = LocalDate.now();
        String status = "ABERTO";

        Order order = new Order(orderId, mechanicId, today, status);
        orderDAO.insert(order);

        // 2. Buscar itens da cotação
        List<QuoteItem> quoteItems = quoteItemDAO.findByQuoteId(quoteId);

        // 3. Inserir itens no pedido
        for (QuoteItem item : quoteItems) {
            OrderItem orderItem = new OrderItem(item.getProductId(), item.getQuantity(), item.getUnitPrice());
            orderItem.setOrderId(orderId);
            orderItemDAO.insert(orderItem);
        }

        // 4. Atualizar status da cotação
        quoteDAO.updateStatus(quoteId, "CONVERTIDA");
    }

    public void createOrderDirect(String mechanicId, List<OrderItem> items) throws SQLException {
        // 1. Criar pedido
        String orderId = Utils.generateUUID();
        LocalDate today = LocalDate.now();
        String status = "ABERTO";

        Order order = new Order(orderId, mechanicId, today, status);
        orderDAO.insert(order);

        // 2. Adicionar os itens ao pedido
        for (OrderItem item : items) {
            item.setOrderId(orderId); // associar ao pedido
            orderItemDAO.insert(item);
        }
    }

}
