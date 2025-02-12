package com.tpbancodedados.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.tpbancodedados.model.Plantacao;

public class PlantacaoDAO {

    public int inserirPlantacao(Plantacao plantacao) {
        String query = "INSERT INTO Plantacao (id_agronomo, cultura, area, data_plantio) VALUES (?, ?, ?, ?)";
        int idGerado = -1;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, plantacao.getIdAgronomo());
            statement.setString(2, plantacao.getCultura());
            statement.setFloat(3, plantacao.getArea());
			
            statement.setDate(4, Date.valueOf(plantacao.getDataPlantio()));

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
				plantacao.setCultura(resultSet.getString("cultura"));
				plantacao.setArea(resultSet.getFloat("area"));
				plantacao.setDataPlantio(resultSet.getDate("data_plantio").toLocalDate());
	
				Date dataColheita = resultSet.getDate("data_colheita");
				if (dataColheita != null) {
					plantacao.setDataColheita(dataColheita.toLocalDate());
				} else {
					plantacao.setDataColheita(null);
				}
	
				plantacoes.add(plantacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return plantacoes;
	}
	

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
				plantacao.setCultura(resultSet.getString("cultura"));
				plantacao.setArea(resultSet.getFloat("area"));
				plantacao.setDataPlantio(resultSet.getDate("data_plantio").toLocalDate());
	
				Date dataColheita = resultSet.getDate("data_colheita");
				if (dataColheita != null) {
					plantacao.setDataColheita(dataColheita.toLocalDate());
				} else {
					plantacao.setDataColheita(null);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return plantacao;
	}
	

    public List<Plantacao> listarPlantacaoPorAgronomo(int idAgronomo) {
		String query = "SELECT * FROM Plantacao WHERE id_agronomo = ?";
		Plantacao plantacao = null;
		List<Plantacao> plantacoes = new ArrayList<>();
	
		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(query)) {
	
			statement.setInt(1, idAgronomo);
			ResultSet resultSet = statement.executeQuery();
	
			while (resultSet.next()) {
				plantacao = new Plantacao();
				plantacao.setIdPlantacao(resultSet.getInt("id_plantacao"));
				plantacao.setIdAgronomo(resultSet.getInt("id_agronomo"));
				plantacao.setCultura(resultSet.getString("cultura"));
				plantacao.setArea(resultSet.getFloat("area"));
				plantacao.setDataPlantio(resultSet.getDate("data_plantio").toLocalDate());
	
				Date dataColheita = resultSet.getDate("data_colheita");
				if (dataColheita != null) {
					plantacao.setDataColheita(dataColheita.toLocalDate());
				} else {
					plantacao.setDataColheita(null);
				}
	
				plantacoes.add(plantacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
		return plantacoes;
	}
	

	public List<Plantacao> listarPlantacaoPorCultura(String cultura) {
		String query = "SELECT * FROM Plantacao WHERE cultura = ?";
		Plantacao plantacao = null;
		List<Plantacao> plantacoes = new ArrayList<>();
	
		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(query)) {
	
			statement.setString(1, cultura);
			ResultSet resultSet = statement.executeQuery();
	
			while (resultSet.next()) {
				plantacao = new Plantacao();
				plantacao.setIdPlantacao(resultSet.getInt("id_plantacao"));
				plantacao.setIdAgronomo(resultSet.getInt("id_agronomo"));
				plantacao.setCultura(resultSet.getString("cultura"));
				plantacao.setArea(resultSet.getFloat("area"));
				plantacao.setDataPlantio(resultSet.getDate("data_plantio").toLocalDate());
	
				Date dataColheita = resultSet.getDate("data_colheita");
				if (dataColheita != null) {
					plantacao.setDataColheita(dataColheita.toLocalDate());
				} else {
					plantacao.setDataColheita(null);
				}
	
				plantacoes.add(plantacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	
		return plantacoes;
	}
	


    public boolean atualizarPlantacao(Plantacao plantacao) {
		String query;
	
		// Se a data de colheita for null, omitimos a parte de "data_colheita" na query
		if (plantacao.getDataColheita() != null) {
			query = "UPDATE Plantacao SET id_agronomo = ?, cultura = ?, area = ?, data_plantio = ?, data_colheita = ? WHERE id_plantacao = ?";
		} else {
			query = "UPDATE Plantacao SET id_agronomo = ?, cultura = ?, area = ?, data_plantio = ? WHERE id_plantacao = ?";
		}
	
		try (Connection connection = DatabaseConnection.getConnection();
			 PreparedStatement statement = connection.prepareStatement(query)) {
	
			// Preenchemos os parâmetros comuns
			statement.setInt(1, plantacao.getIdAgronomo());
			statement.setString(2, plantacao.getCultura());
			statement.setFloat(3, plantacao.getArea());
			statement.setDate(4, Date.valueOf(plantacao.getDataPlantio()));
	
			// Se a data de colheita não for null, preenchemos o parâmetro correspondente
			if (plantacao.getDataColheita() != null) {
				statement.setDate(5, Date.valueOf(plantacao.getDataColheita()));
				statement.setInt(6, plantacao.getIdPlantacao());
			} else {
				statement.setInt(5, plantacao.getIdPlantacao());
			}
	
			int rowsUpdated = statement.executeUpdate();
			return rowsUpdated > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return false;
	}
	

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
