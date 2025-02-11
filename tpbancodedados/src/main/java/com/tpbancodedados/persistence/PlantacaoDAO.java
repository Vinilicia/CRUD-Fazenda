package com.tpbancodedados.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.tpbancodedados.model.Plantacao;

public class PlantacaoDAO {

    // Insere uma plantação e retorna seu ID
    public int inserirPlantacao(Plantacao plantacao) {
        String query = "INSERT INTO Plantacao (id_agronomo, tipo, area, data_plantio, data_colheita) VALUES (?, ?, ?, ?, ?)";
        int idGerado = -1;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, plantacao.getIdAgronomo());
            statement.setString(2, plantacao.getTipo());
            statement.setFloat(3, plantacao.getArea());
            statement.setString(4, plantacao.getDataPlantio());
            statement.setString(5, plantacao.getDataColheita());

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

    // Lista todas as plantações
    public List<Plantacao> listarPlantacoes() {
        String query = "SELECT * FROM Plantacao";
        List<Plantacao> plantacoes = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Plantacao plantacao = new Plantacao();
                plantacao.setIdPlantacao(resultSet.getInt("id_plantacao"));
                plantacao.setIdAgronomo(resultSet.getInt("id_agronomo"));
                plantacao.setTipo(resultSet.getString("tipo"));
                plantacao.setArea(resultSet.getFloat("area"));
                plantacao.setDataPlantio(resultSet.getString("data_plantio"));
                plantacao.setDataColheita(resultSet.getString("data_colheita"));
                plantacoes.add(plantacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plantacoes;
    }

    // Busca uma plantação pelo ID
    public Plantacao buscarPlantacaoPorId(int id) {
        String query = "SELECT * FROM Plantacao WHERE id_plantacao = ?";
        Plantacao plantacao = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                plantacao = new Plantacao();
                plantacao.setIdPlantacao(resultSet.getInt("id_plantacao"));
                plantacao.setIdAgronomo(resultSet.getInt("id_agronomo"));
                plantacao.setTipo(resultSet.getString("tipo"));
                plantacao.setArea(resultSet.getFloat("area"));
                plantacao.setDataPlantio(resultSet.getString("data_plantio"));
                plantacao.setDataColheita(resultSet.getString("data_colheita"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return plantacao;
    }


	public List<Plantacao> buscarPlantacaoPorAgronomo(int id_agronomo) {
		String query = "SELECT * FROM Plantacao WHERE id_agronomo = ?";
		List<Plantacao> plantacoes = null;
		Plantacao plantacao;

		try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

			plantacoes = new ArrayList<Plantacao>();
            statement.setInt(1, id_agronomo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                plantacao = new Plantacao();
                plantacao.setIdPlantacao(resultSet.getInt("id_plantacao"));
                plantacao.setIdAgronomo(resultSet.getInt("id_agronomo"));
                plantacao.setTipo(resultSet.getString("tipo"));
                plantacao.setArea(resultSet.getFloat("area"));
                plantacao.setDataPlantio(resultSet.getString("data_plantio"));
                plantacao.setDataColheita(resultSet.getString("data_colheita"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return plantacoes;
	}

    // Atualiza os dados de uma plantação
    public boolean atualizarPlantacao(Plantacao plantacao) {
        String query = "UPDATE Plantacao SET id_agronomo = ?, tipo = ?, area = ?, data_plantio = ?, data_colheita = ? WHERE id_plantacao = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, plantacao.getIdAgronomo());
            statement.setString(2, plantacao.getTipo());
            statement.setFloat(3, plantacao.getArea());
            statement.setString(4, plantacao.getDataPlantio());
            statement.setString(5, plantacao.getDataColheita());
            statement.setInt(6, plantacao.getIdPlantacao());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Deleta uma plantação pelo ID
    public boolean deletarPlantacao(int id) {
        String query = "DELETE FROM Plantacao WHERE id_plantacao = ?";

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
