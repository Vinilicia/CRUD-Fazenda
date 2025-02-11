package com.tpbancodedados.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.model.Vacina;

public class VacinaDAO {

    public boolean inserirVacina(Vacina vacina) {
        String query = "INSERT INTO Vacina (id_vacinacao, descricao) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, vacina.getIdVacina());
            statement.setString(2, vacina.getDescricao());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Vacina> listarVacinas() {
        String query = "SELECT * FROM Vacina";
        List<Vacina> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Vacina vacina = new Vacina();
                vacina.setIdVacina(resultSet.getInt("id_vacinacao"));
                vacina.setDescricao(resultSet.getString("descricao"));
                lista.add(vacina);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Vacina buscarVacinaPorId(int idVacina) {
        String query = "SELECT * FROM Vacina WHERE id_vacinacao = ?";
        Vacina vacina = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idVacina);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    vacina = new Vacina();
                    vacina.setIdVacina(resultSet.getInt("id_vacinacao"));
                    vacina.setDescricao(resultSet.getString("descricao"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vacina;
    }

	public Vacina buscarVacinaPorDescricao(String descricaoVacina) {
        String query = "SELECT * FROM Vacina WHERE descricao = ?";
        Vacina vacina = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, descricaoVacina);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    vacina = new Vacina();
                    vacina.setIdVacina(resultSet.getInt("id_vacinacao"));
                    vacina.setDescricao(resultSet.getString("descricao"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
			return null;
        }

        return vacina;
    }

    public boolean atualizarVacina(Vacina vacina) {
        String query = "UPDATE Vacina SET descricao = ? WHERE id_vacinacao = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, vacina.getDescricao());
            statement.setInt(2, vacina.getIdVacina());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deletarVacina(int idVacina) {
        String query = "DELETE FROM Vacina WHERE id_vacinacao = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idVacina);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

	
}
