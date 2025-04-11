package br.com.fiap.challenge.redeancora;

import br.com.fiap.challenge.redeancora.view.SignUp;

import javax.swing.*;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args ) throws SQLException {
        SwingUtilities.invokeLater(SignUp::new);
    }
}