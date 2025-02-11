package com.tpbancodedados.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.model.Funcionario;

public class FuncionarioDAO {

    // Insere um funcionário e retorna seu ID
    public int inserirFuncionario(Funcionario funcionario) {
        String query = "INSERT INTO Funcionario (nome, cpf, salario) VALUES (?, ?, ?)";
        int idGerado = -1;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getCpf());
            statement.setDouble(3, funcionario.getSalario());

            statement.executeUpdate();

            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    idGerado = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idGerado;
    }

    // Listar todos os funcionários
    public List<Funcionario> listarFuncionarios() {
        String query = "SELECT * FROM Funcionario";
        List<Funcionario> funcionarios = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultSet.getInt("id_funcionario"));
                funcionario.setNome(resultSet.getString("nome"));
                funcionario.setCpf(resultSet.getString("cpf"));
                funcionario.setSalario(resultSet.getDouble("salario"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcionarios;
    }

    // Buscar funcionário por ID
    public Funcionario buscarFuncionarioPorId(int id) {
        String query = "SELECT * FROM Funcionario WHERE id_funcionario = ?";
        Funcionario funcionario = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                funcionario = new Funcionario();
                funcionario.setId(resultSet.getInt("id_funcionario"));
                funcionario.setNome(resultSet.getString("nome"));
                funcionario.setCpf(resultSet.getString("cpf"));
                funcionario.setSalario(resultSet.getDouble("salario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcionario;
    }

	public Funcionario buscarFuncionarioPorNome(String nome) {
        String query = "SELECT * FROM Funcionario WHERE nome = ?";
        Funcionario funcionario = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, nome);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                funcionario = new Funcionario();
                funcionario.setId(resultSet.getInt("id_funcionario"));
                funcionario.setNome(resultSet.getString("nome"));
                funcionario.setCpf(resultSet.getString("cpf"));
                funcionario.setSalario(resultSet.getDouble("salario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcionario;
    }

	public Funcionario buscarFuncionarioPorCPF(String CPF) {
        String query = "SELECT * FROM Funcionario WHERE cpf = ?";
        Funcionario funcionario = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, CPF);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                funcionario = new Funcionario();
                funcionario.setId(resultSet.getInt("id_funcionario"));
                funcionario.setNome(resultSet.getString("nome"));
                funcionario.setCpf(resultSet.getString("cpf"));
                funcionario.setSalario(resultSet.getDouble("salario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return funcionario;
    }

    // Atualizar dados de um funcionário
    public boolean atualizarFuncionario(Funcionario funcionario) {
        String query = "UPDATE Funcionario SET nome = ?, cpf = ?, salario = ? WHERE id_funcionario = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getCpf());
            statement.setDouble(3, funcionario.getSalario());
            statement.setInt(4, funcionario.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Deletar um funcionário pelo ID
    public boolean deletarFuncionario(int id) {
        String query = "DELETE FROM Funcionario WHERE id_funcionario = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
