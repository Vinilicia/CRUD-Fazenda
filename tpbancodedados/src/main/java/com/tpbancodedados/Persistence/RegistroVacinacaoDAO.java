package com.tpbancodedados.Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tpbancodedados.Model.RegistroVacinacao;

public class RegistroVacinacaoDAO {

    // Método para inserir um registro de vacinação
    public void inserirRegistroVacinacao(RegistroVacinacao registro) {
        String query = "INSERT INTO RegistroVacinacao (id_animal, data_vacinacao, descricao) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Definir os parâmetros da consulta
            statement.setInt(1, registro.getIdAnimal());
            statement.setString(2, registro.getDataVacinacao());
            statement.setString(3, registro.getDescricao());

            // Executa a operação
            statement.executeUpdate();
            System.out.println("Registro de vacinação inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um registro de vacinação
    public void excluirRegistroVacinacao(int idVacina) {
        String query = "DELETE FROM RegistroVacinacao WHERE id_vacina = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Definir os parâmetros da consulta
            statement.setInt(1, idVacina);

            // Executa a operação
            statement.executeUpdate();
            System.out.println("Registro de vacinação excluído com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
