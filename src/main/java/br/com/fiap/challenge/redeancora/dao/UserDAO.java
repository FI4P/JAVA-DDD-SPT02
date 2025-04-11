package br.com.fiap.challenge.redeancora.dao;

import br.com.fiap.challenge.redeancora.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public void insert(User user) throws SQLException {

        String query = "INSERT INTO usuario (id, name, email, password, role ) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getRole());

            stmt.executeUpdate();

            System.out.println("Created user - " + user.getId());

        }







    }

}
