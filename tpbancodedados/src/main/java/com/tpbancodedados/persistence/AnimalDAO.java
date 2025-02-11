package com.tpbancodedados.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.model.Animal;

public class AnimalDAO {

    private VeterinarioAnimalDAO veterinarioAnimalDAO = new VeterinarioAnimalDAO();

    // Insere um animal e retorna seu ID
    public int inserirAnimal(Animal animal) {
        String query = "INSERT INTO Animal (nome, especie, data_nascimento) VALUES (?, ?, ?)";
        int idGerado = -1;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, animal.getNome());
            statement.setString(2, animal.getEspecie());
            statement.setDate(3, Date.valueOf(animal.getDataNascimento()));

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
        List<Animal> animais = new ArrayList<Animal>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Animal animal = new Animal();
                animal.setIdAnimal(resultSet.getInt("id_animal"));
                animal.setNome(resultSet.getString("nome"));
                animal.setEspecie(resultSet.getString("especie"));
                animal.setDataNascimento(resultSet.getDate("data_nascimento").toLocalDate());
                animais.add(animal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
			return null;
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
                animal.setDataNascimento(resultSet.getDate("data_nascimento").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animal;
    }

    public Animal buscarAnimalPorNome(String nome) {
        String query = "SELECT * FROM Animal WHERE nome = ?";
        Animal animal = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, nome);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                animal = new Animal();
                animal.setIdAnimal(resultSet.getInt("id_animal"));
                animal.setNome(resultSet.getString("nome"));
                animal.setEspecie(resultSet.getString("especie"));
                animal.setDataNascimento(resultSet.getDate("data_nascimento").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animal;
    }

	public List<Animal> buscarAnimalPorDataNascimento(LocalDate dataNascimento) {
        String query = "SELECT * FROM Animal WHERE data_nascimento = ?";
        Animal animal = null;
		List<Animal> animais = new ArrayList<Animal>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDate(1, Date.valueOf(dataNascimento));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                animal = new Animal();
                animal.setIdAnimal(resultSet.getInt("id_animal"));
                animal.setNome(resultSet.getString("nome"));
                animal.setEspecie(resultSet.getString("especie"));
                animal.setDataNascimento(resultSet.getDate("data_nascimento").toLocalDate());
				animais.add(animal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
			return null;
        }

        return animais;
    }

    public List<Animal> buscarAnimaisPorVeterinario(int id_veterinario) {
        List<Animal> animais = new ArrayList<>();
        List<Integer> id_animais = veterinarioAnimalDAO.buscarAnimalPorVeterinario(id_veterinario);
        for (int id : id_animais) {
            animais.add(buscarAnimalPorId(id));
        }
        return animais;
    }

    public boolean atualizarAnimal(Animal animal) {
        String query = "UPDATE Animal SET nome = ?, especie = ?, data_nascimento = ? WHERE id_animal = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, animal.getNome());
            statement.setString(2, animal.getEspecie());
            statement.setDate(3, Date.valueOf(animal.getDataNascimento()));
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
