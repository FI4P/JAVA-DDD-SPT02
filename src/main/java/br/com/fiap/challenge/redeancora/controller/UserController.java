package br.com.fiap.challenge.redeancora.controller;

import br.com.fiap.challenge.redeancora.dao.UserDAO;
import br.com.fiap.challenge.redeancora.model.User;
import br.com.fiap.challenge.redeancora.utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class UserController {

    private final UserDAO userDAO;

    public UserController(Connection connection) throws SQLException {
        this.userDAO = new UserDAO(connection);

    }

    public void createUser(String name, String email, String password, String role) throws SQLException {
        String id = Utils.generateUUID();

        User user = new User(id, name, email, password, role);

        if (!user.emailValidation(email)) {
            System.out.println("Email inválido!");
            return;
        }

        this.userDAO.insert(user);
        System.out.println("Usuário criado com sucesso!");
    }

    public User authenticateUser(String email, String password) throws SQLException {
        return userDAO.authenticate(email, password);
    }



}
