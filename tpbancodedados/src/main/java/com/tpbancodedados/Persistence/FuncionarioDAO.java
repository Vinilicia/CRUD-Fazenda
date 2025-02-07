package com.tpbancodedados.Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tpbancodedados.Model.Funcionario;

public class FuncionarioDAO {

    // Método para inserir um funcionário no banco
    public void inserirFuncionario(Funcionario funcionario) {
        String query = "INSERT INTO Funcionario (nome, cpf, salario) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Definir os parâmetros da consulta
            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getCpf());
            statement.setDouble(3, funcionario.getSalario());

            // Executa a operação
            statement.executeUpdate();
            System.out.println("Funcionário inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um funcionário
    public void atualizarFuncionario(Funcionario funcionario) {
        String query = "UPDATE Funcionario SET nome = ?, cpf = ?, salario = ? WHERE id_funcionario = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Definir os parâmetros da consulta
            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getCpf());
            statement.setDouble(3, funcionario.getSalario());
            statement.setInt(4, funcionario.getIdFuncionario());

            // Executa a operação
            statement.executeUpdate();
            System.out.println("Funcionário atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um funcionário
    public void excluirFuncionario(int idFuncionario) {
        String query = "DELETE FROM Funcionario WHERE id_funcionario = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Definir os parâmetros da consulta
            statement.setInt(1, idFuncionario);

            // Executa a operação
            statement.executeUpdate();
            System.out.println("Funcionário excluído com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
