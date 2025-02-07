package com.tpbancodedados.Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tpbancodedados.Model.Plantacao;

public class PlantacaoDAO {

    // Método para inserir uma plantação
    public void inserirPlantacao(Plantacao plantacao) {
        String query = "INSERT INTO Plantacao (id_agronomo, tipo, area, data_plantio, data_colheita) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Definir os parâmetros da consulta
            statement.setInt(1, plantacao.getIdAgronomo());
            statement.setString(2, plantacao.getTipo());
            statement.setFloat(3, plantacao.getArea());
            statement.setString(4, plantacao.getDataPlantio());
            statement.setString(5, plantacao.getDataColheita());

            // Executa a operação
            statement.executeUpdate();
            System.out.println("Plantação inserida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todas as plantações
    public void listarPlantas() {
        String query = "SELECT * FROM Plantacao";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Aqui seria necessário preencher uma lista de plantações
                System.out.println("ID: " + resultSet.getInt("id_plantacao"));
                System.out.println("Tipo: " + resultSet.getString("tipo"));
                System.out.println("Área: " + resultSet.getFloat("area"));
                System.out.println("Data de Plantio: " + resultSet.getString("data_plantio"));
                System.out.println("Data de Colheita: " + resultSet.getString("data_colheita"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
