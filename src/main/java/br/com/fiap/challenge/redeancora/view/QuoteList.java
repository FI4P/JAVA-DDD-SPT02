package br.com.fiap.challenge.redeancora.view;

import br.com.fiap.challenge.redeancora.controller.OrderController;
import br.com.fiap.challenge.redeancora.controller.QuoteController;
import br.com.fiap.challenge.redeancora.db.DBConnection;
import br.com.fiap.challenge.redeancora.model.Quote;
import br.com.fiap.challenge.redeancora.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class QuoteList extends JFrame {

    private final User mechanic;
    private JTable table;
    private DefaultTableModel tableModel;

    public QuoteList(User mechanic) {
        super("My Quotes");
        this.mechanic = mechanic;

        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Date", "Status"});

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnFinalize = new JButton("\uD83D\uDCE6 Finalize Quote");

        btnFinalize.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a quote.");
                return;
            }

            String status = tableModel.getValueAt(row, 2).toString();
            if (!status.equalsIgnoreCase("PENDENTE")) {
                JOptionPane.showMessageDialog(this, "Only pending quotes can be finalized.");
                return;
            }

            String quoteId = tableModel.getValueAt(row, 0).toString();

            try {
                Connection connection = new DBConnection().getConnection();
                OrderController controller = new OrderController(connection);
                controller.convertQuoteToOrder(quoteId, mechanic.getId());

                JOptionPane.showMessageDialog(this, "Quote finalized and order created!");
                loadQuotes();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error finalizing quote: " + ex.getMessage());
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnFinalize);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        loadQuotes();
        setVisible(true);
    }

    private void loadQuotes() {
        try {
            Connection connection = new DBConnection().getConnection();
            QuoteController controller = new QuoteController(connection);
            List<Quote> quotes = controller.findQuotesByMechanic(mechanic.getId());

            tableModel.setRowCount(0);
            for (Quote q : quotes) {
                tableModel.addRow(new Object[]{
                        q.getId(),
                        q.getDate(),
                        q.getStatus()
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading quotes: " + e.getMessage());
        }
    }
}
