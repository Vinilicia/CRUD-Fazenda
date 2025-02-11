package com.tpbancodedados.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import com.tpbancodedados.model.RegistroVacinacao;

public class RegistroVacinacaoDAO {

    public boolean inserirRegistroVacinacao(RegistroVacinacao registro) {
        String query = "INSERT INTO RegistroVacinacao (id_animal, id_vacina, data_vacinacao) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, registro.getIdAnimal());
            statement.setInt(2, registro.getIdVacinacao());
            
            statement.setDate(3, Date.valueOf(registro.getDataVacinacao()));

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<RegistroVacinacao> listarRegistrosVacinacao() {
        String query = "SELECT * FROM RegistroVacinacao";
        List<RegistroVacinacao> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                RegistroVacinacao registro = new RegistroVacinacao();
                registro.setIdAnimal(resultSet.getInt("id_animal"));
                registro.setIdVacinacao(resultSet.getInt("id_vacina"));
                
                Date dataVacinacaoSQL = resultSet.getDate("data_vacinacao");
                if (dataVacinacaoSQL != null) {
                    registro.setDataVacinacao(dataVacinacaoSQL.toLocalDate());
                }
                
                lista.add(registro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

	public List<RegistroVacinacao> listarRegistrosVacinacaoPorData(LocalDate dataDesejada) {
        String query = "SELECT * FROM RegistroVacinacao WHERE data_vacinacao = ?";
        List<RegistroVacinacao> lista = new ArrayList<>();
		RegistroVacinacao registro = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setDate(1, Date.valueOf(dataDesejada));

			ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                registro = new RegistroVacinacao();
                registro.setIdAnimal(resultSet.getInt("id_animal"));
                registro.setIdVacinacao(resultSet.getInt("id_vacina"));
                
                registro.setDataVacinacao(resultSet.getDate("data_vacinacao").toLocalDate());
                
                lista.add(registro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
			return null;
        }

        return lista;
    }

	public List<RegistroVacinacao> listarRegistrosVacinacaoPorVacina(int idVacina) {
        String query = "SELECT * FROM RegistroVacinacao WHERE id_vacina = ?";
        List<RegistroVacinacao> lista = new ArrayList<>();
		RegistroVacinacao registro = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, idVacina);

			ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                registro = new RegistroVacinacao();
                registro.setIdAnimal(resultSet.getInt("id_animal"));
                registro.setIdVacinacao(resultSet.getInt("id_vacina"));
                
                registro.setDataVacinacao(resultSet.getDate("data_vacina").toLocalDate());
                
                lista.add(registro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
			return null;
        }

        return lista;
    }

	public List<RegistroVacinacao> listarRegistrosVacinacaoPorAnimal(int idAnimal) {
        String query = "SELECT * FROM RegistroVacinacao WHERE id_animal = ?";
        List<RegistroVacinacao> lista = new ArrayList<>();
		RegistroVacinacao registro = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, idAnimal);

			ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                registro = new RegistroVacinacao();
                registro.setIdAnimal(resultSet.getInt("id_animal"));
                registro.setIdVacinacao(resultSet.getInt("id_vacina"));
                
                registro.setDataVacinacao(resultSet.getDate("data_vacinacao").toLocalDate());
                
                lista.add(registro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
			return null;
        }

        return lista;
    }

    public boolean atualizarRegistroVacinacao(RegistroVacinacao registro) {
        String query = "UPDATE RegistroVacinacao SET id_vacina = ?, data_vacinacao = ? WHERE id_animal = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, registro.getIdVacinacao());
            
            statement.setDate(2, Date.valueOf(registro.getDataVacinacao()));
            statement.setInt(3, registro.getIdAnimal());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deletarRegistroVacinacao(int idAnimal, int idVacina) {
        String query = "DELETE FROM RegistroVacinacao WHERE id_animal = ? AND id_vacina = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idAnimal);
            statement.setInt(2, idVacina);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
