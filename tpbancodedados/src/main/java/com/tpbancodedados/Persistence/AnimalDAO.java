package com.tpbancodedados.Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.Model.Animal;

public class AnimalDAO {

    // Método para inserir um animal
    public void inserirAnimal(Animal animal) {
        String query = "INSERT INTO Animal (nome, especie, data_nascimento) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Definir os parâmetros da consulta
            statement.setString(1, animal.getNome());
            statement.setString(2, animal.getEspecie());
            statement.setString(3, animal.getDataNascimento());

            // Executa a operação
            statement.executeUpdate();
            System.out.println("Animal inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os animais
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
}
