package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EquipamentoDAO {

    // Método para inserir um equipamento
    public void inserirEquipamento(Equipamento equipamento) {
        String query = "INSERT INTO Equipamento (descricao, estado) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Definir os parâmetros da consulta
            statement.setString(1, equipamento.getDescricao());
            statement.setString(2, equipamento.getEstado());

            // Executa a operação
            statement.executeUpdate();
            System.out.println("Equipamento inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
