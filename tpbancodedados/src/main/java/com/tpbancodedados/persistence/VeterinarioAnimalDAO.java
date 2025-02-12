package com.tpbancodedados.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.model.VeterinarioAnimal;

public class VeterinarioAnimalDAO {

    public boolean inserirVeterinarioAnimal(VeterinarioAnimal veterinarioAnimal) {
        String query = "INSERT INTO VeterinarioAnimal (id_funcionario, id_animal) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, veterinarioAnimal.getIdVeterinario());
            statement.setInt(2, veterinarioAnimal.getIdAnimal());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<VeterinarioAnimal> listarVeterinarioAnimal() {
        String query = "SELECT * FROM VeterinarioAnimal";
        List<VeterinarioAnimal> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                VeterinarioAnimal va = new VeterinarioAnimal();
                va.setIdVeterinario(resultSet.getInt("id_funcionario"));
                va.setIdAnimal(resultSet.getInt("id_animal"));
                lista.add(va);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

	public List<Integer> buscarAnimalPorVeterinario(int id_funcionario) {
		String query = "SELECT * FROM VeterinarioAnimal WHERE id_funcionario = ?";
		ArrayList<Integer> id_animais = null;

		try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id_funcionario);
            ResultSet resultSet = statement.executeQuery();
			id_animais = new ArrayList<Integer>();

            if (resultSet.next()) {
                int novoId = resultSet.getInt("id_animal");
                id_animais.add(novoId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
			return null;
        }

		return id_animais;
	}

	public List<Integer> buscarVeterinarioPorAnimal(int id_animal) {
		String query = "SELECT * FROM VeterinarioAnimal WHERE id_animal = ?";
		ArrayList<Integer> id_animais = null;

		try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id_animal);
            ResultSet resultSet = statement.executeQuery();
			id_animais = new ArrayList<Integer>();

            if (resultSet.next()) {
                int novoId = resultSet.getInt("id_funcionario");
                id_animais.add(novoId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
			return null;
        }

		return id_animais;
	}

    public boolean atualizarVeterinarioAnimal(int idVeterinario, int idAnimalNovo) {
        String query = "UPDATE VeterinarioAnimal SET id_animal = ? WHERE id_funcionario = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idAnimalNovo);
            statement.setInt(2, idVeterinario);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deletarVeterinarioAnimal(int idVeterinario, int idAnimal) {
        String query = "DELETE FROM VeterinarioAnimal WHERE id_funcionario = ? AND id_animal = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idVeterinario);
            statement.setInt(2, idAnimal);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
