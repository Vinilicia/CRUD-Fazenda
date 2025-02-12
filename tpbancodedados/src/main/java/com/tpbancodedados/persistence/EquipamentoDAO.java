package com.tpbancodedados.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.model.Equipamento;
import com.tpbancodedados.model.EstadoEquipamento;

public class EquipamentoDAO {

    public int inserirEquipamento(Equipamento equipamento) {
        String query = "INSERT INTO Equipamento (descricao, estado) VALUES (?, ?)";
        int idGerado = -1;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, equipamento.getDescricao());
            statement.setString(2, equipamento.getEstado().name());

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
	
				String estadoStr = resultSet.getString("estado");
				EstadoEquipamento estado = EstadoEquipamento.fromDescricao(estadoStr);
				equipamento.setEstado(estado);
	
				equipamentos.add(equipamento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return equipamentos;
	}
	

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

                String estadoStr = resultSet.getString("estado");
                EstadoEquipamento estado = EstadoEquipamento.valueOf(estadoStr);
                equipamento.setEstado(estado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipamento;
    }

	public List<Equipamento> buscarEquipamentosPorEstado(EstadoEquipamento estado) {
        String query = "SELECT * FROM Equipamento WHERE estado = ?";
        List<Equipamento> equipamentos = new ArrayList<>();
		Equipamento equipamento = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, estado.name());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                equipamento = new Equipamento();
                equipamento.setIdEquipamento(resultSet.getInt("id_equipamento"));
                equipamento.setDescricao(resultSet.getString("descricao"));

                String estadoStr = resultSet.getString("estado");
                EstadoEquipamento estadoEquipamento = EstadoEquipamento.valueOf(estadoStr);
                equipamento.setEstado(estadoEquipamento);

                equipamentos.add(equipamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
			return null;
        }

        return equipamentos;
    }

    public boolean atualizarEquipamento(Equipamento equipamento) {
        String query = "UPDATE Equipamento SET descricao = ?, estado = ? WHERE id_equipamento = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, equipamento.getDescricao());
            statement.setString(2, equipamento.getEstado().name());
            statement.setInt(3, equipamento.getIdEquipamento());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

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
