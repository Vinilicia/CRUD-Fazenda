package com.tpbancodedados.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Método para obter a conexão com o banco
    public static Connection getConnection() throws SQLException {
        try {
            // Carregar o driver do banco de dados (MariaDB, MySQL)
            Class.forName("org.mariadb.jdbc.Driver");
            // URL do banco, usuário e senha
            String url = "jdbc:mariadb://localhost:3306/nome_do_banco";
            String username = "root"; // ou outro usuário
            String password = "senha_do_banco";
            // Estabelecendo a conexão
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do banco não encontrado.", e);
        }
    }
}
