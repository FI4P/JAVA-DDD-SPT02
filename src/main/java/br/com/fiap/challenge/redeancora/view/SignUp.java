package br.com.fiap.challenge.redeancora.view;

import br.com.fiap.challenge.redeancora.controller.UserController;
import br.com.fiap.challenge.redeancora.db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class SignUp extends JFrame {

    private JTextField txtName;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JComboBox<String> cbRole;

    public SignUp() {
        super("User Registration");

        // Configure window
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        // Components
        JLabel lblName = new JLabel("Nome:");
        txtName = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();

        JLabel lblPassword = new JLabel("Senha:");
        txtPassword = new JPasswordField();

        JLabel lblRole = new JLabel("Role:");
        cbRole = new JComboBox<>(new String[]{"Mecanico", "Cliente"});

        JButton btnRegister = new JButton("Registrar");
        JButton btnLoginRedirect = new JButton("Login");

        //Button actions
        btnRegister.addActionListener(e -> registerUser());
        btnLoginRedirect.addActionListener(e -> {
            dispose(); // Close current SignUp window
            SwingUtilities.invokeLater(Login::new); // Open login screen
        });

        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(lblRole);
        panel.add(cbRole);
        panel.add(btnLoginRedirect);
        panel.add(btnRegister);
        add(panel);
        setVisible(true);
    }

    private void registerUser() {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String password = new String(txtPassword.getPassword());
        String role = cbRole.getSelectedItem().toString();

        try {
            Connection connection = new DBConnection().getConnection();
            UserController controller = new UserController(connection);
            controller.createUser(name, email, password, role);
            JOptionPane.showMessageDialog(this, "Usuario criado com sucesso!");
            dispose(); // Close SignUp window
            SwingUtilities.invokeLater(Login::new);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao tentar registrar usuario: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignUp::new);
    }
}
