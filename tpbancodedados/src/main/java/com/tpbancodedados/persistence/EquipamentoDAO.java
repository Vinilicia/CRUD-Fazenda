package com.tpbancodedados.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.model.Equipamento;

public class EquipamentoDAO {

    // Insere um equipamento e retorna seu ID
    public int inserirEquipamento(Equipamento equipamento) {
        String query = "INSERT INTO Equipamento (descricao, estado) VALUES (?, ?)";
        int idGerado = -1;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, equipamento.getDescricao());
            statement.setString(2, equipamento.getEstado());

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

    // Lista todos os equipamentos
    public List<Equipamento> listarEquipamentos() {
        String query = "SELECT * FROM Equipamento";
        List<Equipamento> equipamentos = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Equipamento equipamento = new Equipamento();
                equipamento.setIdEquipamento(resultSet.getInt("id_equipamento"));
                equipamento.setDescricao(resultSet.getString("descricao"));
                equipamento.setEstado(resultSet.getString("estado"));
                equipamentos.add(equipamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipamentos;
    }

    // Busca um equipamento pelo ID
    public Equipamento buscarEquipamentoPorId(int id) {
        String query = "SELECT * FROM Equipamento WHERE id_equipamento = ?";
        Equipamento equipamento = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                equipamento = new Equipamento();
                equipamento.setIdEquipamento(resultSet.getInt("id_equipamento"));
                equipamento.setDescricao(resultSet.getString("descricao"));
                equipamento.setEstado(resultSet.getString("estado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipamento;
    }

    // Atualiza os dados de um equipamento
    public boolean atualizarEquipamento(Equipamento equipamento) {
        String query = "UPDATE Equipamento SET descricao = ?, estado = ? WHERE id_equipamento = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, equipamento.getDescricao());
            statement.setString(2, equipamento.getEstado());
            statement.setInt(3, equipamento.getIdEquipamento());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Deleta um equipamento pelo ID
    public boolean deletarEquipamento(int id) {
        String query = "DELETE FROM Equipamento WHERE id_equipamento = ?";

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
