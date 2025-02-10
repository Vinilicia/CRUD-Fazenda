package com.tpbancodedados.Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.tpbancodedados.Model.CaseiroEquipamento;

public class CaseiroEquipamentoDAO {

    // Insere um registro na tabela relacional
    public boolean inserirCaseiroEquipamento(CaseiroEquipamento caseiroEquipamento) {
        String query = "INSERT INTO CaseiroEquipamento (id_caseiro, id_equipamento) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, caseiroEquipamento.getIdCaseiro());
            statement.setInt(2, caseiroEquipamento.getIdEquipamento());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Lista todos os registros da tabela
    public List<CaseiroEquipamento> listarCaseiroEquipamentos() {
        String query = "SELECT * FROM CaseiroEquipamento";
        List<CaseiroEquipamento> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                CaseiroEquipamento ce = new CaseiroEquipamento();
                ce.setIdCaseiro(resultSet.getInt("id_caseiro"));
                ce.setIdEquipamento(resultSet.getInt("id_equipamento"));
                lista.add(ce);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Atualiza um registro existente
    public boolean atualizarCaseiroEquipamento(int idCaseiro, int idEquipamentoNovo) {
        String query = "UPDATE CaseiroEquipamento SET id_equipamento = ? WHERE id_caseiro = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idEquipamentoNovo);
            statement.setInt(2, idCaseiro);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Deleta um registro pelo ID do caseiro e equipamento
    public boolean deletarCaseiroEquipamento(int idCaseiro, int idEquipamento) {
        String query = "DELETE FROM CaseiroEquipamento WHERE id_caseiro = ? AND id_equipamento = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idCaseiro);
            statement.setInt(2, idEquipamento);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
