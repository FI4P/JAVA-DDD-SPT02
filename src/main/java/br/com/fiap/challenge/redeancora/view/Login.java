package br.com.fiap.challenge.redeancora.view;

import br.com.fiap.challenge.redeancora.controller.UserController;
import br.com.fiap.challenge.redeancora.db.DBConnection;
import br.com.fiap.challenge.redeancora.model.User;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Login extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;

    public Login() {
        super("Login");

        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();

        JLabel lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField();

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> loginUser());

        // Add components to panel
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(new JLabel()); // empty space
        panel.add(btnLogin);

        add(panel);
        setVisible(true);
    }

    private void loginUser() {
        String email = txtEmail.getText();
        String password = new String(txtPassword.getPassword());

        try {
            Connection connection = new DBConnection().getConnection();
            UserController controller = new UserController(connection);
            User user = controller.authenticateUser(email, password);

            if (user != null) {
                JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");

                dispose(); // close login window

                // Redirect based on role
                switch (user.getRole().toUpperCase()) {
                    case "MECANICO":
                        new MechanicMenu(user).setVisible(true);
                        break;
                    case "CLIENTE":
                        new ClientMenu(user).setVisible(true);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Unknown role: " + user.getRole());
                }

            } else {
                JOptionPane.showMessageDialog(this, "Email ou senha incorreto. Tente novamente!.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }
}
