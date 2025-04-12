package br.com.fiap.challenge.redeancora.view;

import br.com.fiap.challenge.redeancora.model.User;

import javax.swing.*;
import java.awt.*;

public class ClientMenu extends JFrame {

    private final User client;

    public ClientMenu(User client) {
        super("Client Dashboard");

        this.client = client;

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));

        JLabel welcome = new JLabel("Welcome, " + client.getName(), SwingConstants.CENTER);
        panel.add(welcome);

        JButton btnRegisterProduct = new JButton("📦 Register New Product");
        btnRegisterProduct.addActionListener(e -> new ProductRegister());

        JButton btnListProducts = new JButton("📦️✏️List and Edit Products");
        btnListProducts.addActionListener(e -> new ProductList());


        JButton btnCreatePromotion = new JButton("📢 Create Promotion");
        btnCreatePromotion.addActionListener(e -> new PromotionRegister());

        JButton btnListPromotions = new JButton("📋 View Promotions");
        btnListPromotions.addActionListener(e -> new PromotionList());


        JButton btnLogout = new JButton("🔙 Logout");
        btnLogout.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(Login::new);
        });

        panel.add(btnRegisterProduct);
        panel.add(btnListProducts);
        panel.add(btnCreatePromotion);
        panel.add(btnListPromotions);
        panel.add(btnLogout);

        add(panel);
        setVisible(true);
    }
}
