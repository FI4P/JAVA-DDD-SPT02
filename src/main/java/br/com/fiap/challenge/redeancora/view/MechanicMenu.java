package br.com.fiap.challenge.redeancora.view;

import br.com.fiap.challenge.redeancora.model.User;

import javax.swing.*;
import java.awt.*;

public class MechanicMenu extends JFrame {

    private final User mechanic;

    public MechanicMenu(User mechanic) {
        super("Mechanic Dashboard");
        this.mechanic = mechanic;

        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 1, 10, 10));

        JLabel lblWelcome = new JLabel("Welcome, " + mechanic.getName(), SwingConstants.CENTER);
        panel.add(lblWelcome);

        JButton btnSearchParts = new JButton("🔍 Search Parts");
        btnSearchParts.addActionListener(e -> new ProductSearch());

        JButton btnViewPromotions = new JButton("💬 View Promotions");
        btnViewPromotions.addActionListener(e -> new MechanicPromotionList());

        JButton btnCreateQuote = new JButton("🧾 Create Quotation");
        btnCreateQuote.addActionListener(e -> new QuoteRegister(mechanic));

        JButton btnCreateOrder = new JButton("📦 Create Order (Direct)");
        btnCreateOrder.addActionListener(e -> new OrderRegister(mechanic));

        JButton btnViewHistory = new JButton("🕘 View History");
        btnViewHistory.addActionListener(e -> new QuoteList(mechanic));

        JButton btnLogout = new JButton("🔙 Logout");
        btnLogout.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(Login::new);
        });

        panel.add(btnSearchParts);
        panel.add(btnViewPromotions);
        panel.add(btnCreateQuote);
        panel.add(btnCreateOrder);
        panel.add(btnViewHistory);
        panel.add(btnLogout);

        add(panel);
        setVisible(true);
    }
}
