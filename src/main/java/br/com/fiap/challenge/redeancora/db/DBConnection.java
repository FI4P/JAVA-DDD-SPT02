package br.com.fiap.challenge.redeancora.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public Connection getConnection() throws SQLException {
        String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
        String USER = "rm553377";
        String PASSWORD = "fiap#2025";

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
