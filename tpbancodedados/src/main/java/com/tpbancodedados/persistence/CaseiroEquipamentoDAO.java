package com.tpbancodedados.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.model.CaseiroEquipamento;

public class CaseiroEquipamentoDAO {

    public boolean inserirCaseiroEquipamento(CaseiroEquipamento caseiroEquipamento) {
        String query = "INSERT INTO CaseiroEquipamento (id_funcionario, id_equipamento) VALUES (?, ?)";

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

    public List<CaseiroEquipamento> listarCaseiroEquipamentos() {
        String query = "SELECT * FROM CaseiroEquipamento";
        List<CaseiroEquipamento> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                CaseiroEquipamento ce = new CaseiroEquipamento();
                ce.setIdCaseiro(resultSet.getInt("id_funcionario"));
                ce.setIdEquipamento(resultSet.getInt("id_equipamento"));
                lista.add(ce);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

	public List<Integer> listarEquipamentosPorCaseiro(int idCaseiro) {
		String query = "SELECT * FROM CaseiroEquipamento WHERE id_funcionario = ?";
		List<Integer> idEquipamentos = null;
	
		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(query)) {
	
			statement.setInt(1, idCaseiro);
			ResultSet resultSet = statement.executeQuery();
			idEquipamentos = new ArrayList<>();
	
			while (resultSet.next()) {
				int idEquipamento = resultSet.getInt("id_equipamento");
				idEquipamentos.add(idEquipamento);
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
		return idEquipamentos;
	}
	
	public List<Integer> listarCaseirosPorEquipamento(int idEquipamento) {
		String query = "SELECT * FROM CaseiroEquipamento WHERE id_equipamento = ?";
		List<Integer> idCaseiros = null;
	
		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(query)) {
	
			statement.setInt(1, idEquipamento);
			ResultSet resultSet = statement.executeQuery();
			idCaseiros = new ArrayList<>();
	
			while (resultSet.next()) {
				int idCaseiro = resultSet.getInt("id_funcionario");
				idCaseiros.add(idCaseiro);
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
		return idCaseiros;
	}	

    public boolean atualizarCaseiroEquipamento(int idCaseiro, int idEquipamentoNovo) {
        String query = "UPDATE CaseiroEquipamento SET id_equipamento = ? WHERE id_funcionario = ?";

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

    public boolean deletarCaseiroEquipamento(int idCaseiro, int idEquipamento) {
        String query = "DELETE FROM CaseiroEquipamento WHERE id_funcionario = ? AND id_equipamento = ?";

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
