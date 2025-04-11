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
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        // Components
        JLabel lblName = new JLabel("Name:");
        txtName = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();

        JLabel lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField();

        JLabel lblRole = new JLabel("Role:");
        cbRole = new JComboBox<>(new String[]{"MECANICO", "CLIENTE"});

        JButton btnRegister = new JButton("Register");

        // Button click action
        btnRegister.addActionListener(e -> registerUser());

        // Add components to panel
        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(lblRole);
        panel.add(cbRole);
        panel.add(new JLabel()); // empty placeholder
        panel.add(btnRegister);

        // Add panel to frame and show
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
            JOptionPane.showMessageDialog(this, "User registered successfully!");
            clearFields();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error during registration: " + ex.getMessage());
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        cbRole.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignUp::new);
    }
}
