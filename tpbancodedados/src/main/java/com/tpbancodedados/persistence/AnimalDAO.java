package com.tpbancodedados.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.model.Animal;
import com.tpbancodedados.model.Animal;

public class AnimalDAO {

    // Insere um animal e retorna seu ID
    public int inserirAnimal(Animal animal) {
        String query = "INSERT INTO Animal (nome, especie, data_nascimento) VALUES (?, ?, ?)";
        int idGerado = -1;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, animal.getNome());
            statement.setString(2, animal.getEspecie());
            statement.setString(3, animal.getDataNascimento());

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

    // Lista todos os animais
    public List<Animal> listarAnimais() {
        String query = "SELECT * FROM Animal";
        List<Animal> animais = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Animal animal = new Animal();
                animal.setIdAnimal(resultSet.getInt("id_animal"));
                animal.setNome(resultSet.getString("nome"));
                animal.setEspecie(resultSet.getString("especie"));
                animal.setDataNascimento(resultSet.getString("data_nascimento"));
                animais.add(animal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animais;
    }

    // Busca um animal pelo ID
    public Animal buscarAnimalPorId(int id) {
        String query = "SELECT * FROM Animal WHERE id_animal = ?";
        Animal animal = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                animal = new Animal();
                animal.setIdAnimal(resultSet.getInt("id_animal"));
                animal.setNome(resultSet.getString("nome"));
                animal.setEspecie(resultSet.getString("especie"));
                animal.setDataNascimento(resultSet.getString("data_nascimento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animal;
    }

	public List<Animal> buscarAnimalPorVeterinario(int id_veterinario) {
		String query = "SELECT * FROM Animal WHERE id_veterinario = ?";
		List<Animal> plantacoes = null;
		Animal animal;

		try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

			plantacoes = new ArrayList<Animal>();
            statement.setInt(1, id_veterinario);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                animal = new Animal();
                animal.setIdAnimal(resultSet.getInt("id_animal"));
                animal.setIdVeterinario(resultSet.getInt("id_veterinario"));
                animal.setTipo(resultSet.getString("tipo"));
                animal.setArea(resultSet.getFloat("area"));
                animal.setDataPlantio(resultSet.getString("data_plantio"));
                animal.setDataColheita(resultSet.getString("data_colheita"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return plantacoes;
	}

    // Atualiza os dados de um animal
    public boolean atualizarAnimal(Animal animal) {
        String query = "UPDATE Animal SET nome = ?, especie = ?, data_nascimento = ? WHERE id_animal = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, animal.getNome());
            statement.setString(2, animal.getEspecie());
            statement.setString(3, animal.getDataNascimento());
            statement.setInt(4, animal.getIdAnimal());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Deleta um animal pelo ID
    public boolean deletarAnimal(int id) {
        String query = "DELETE FROM Animal WHERE id_animal = ?";

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
