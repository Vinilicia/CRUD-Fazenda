package com.tpbancodedados.Persistence;

import com.tpbancodedados.Model.RegistroVacinacaoAnimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistroVacinacaoAnimalDAO {

    // Inserir registro de vacinação
    public boolean inserirRegistroVacinacao(RegistroVacinacaoAnimal registro) {
        String query = "INSERT INTO RegistroVacinacaoAnimal (id_animal, id_vacinacao) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, registro.getIdAnimal());
            statement.setInt(2, registro.getIdVacinacao());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Listar todos os registros de vacinação
    public List<RegistroVacinacaoAnimal> listarRegistrosVacinacao() {
        String query = "SELECT * FROM RegistroVacinacaoAnimal";
        List<RegistroVacinacaoAnimal> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                RegistroVacinacaoAnimal registro = new RegistroVacinacaoAnimal();
                registro.setIdAnimal(resultSet.getInt("id_animal"));
                registro.setIdVacinacao(resultSet.getInt("id_vacinacao"));
                lista.add(registro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Atualizar registro de vacinação
    public boolean atualizarRegistroVacinacao(int idAnimal, int idVacinacaoNovo) {
        String query = "UPDATE RegistroVacinacaoAnimal SET id_vacinacao = ? WHERE id_animal = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idVacinacaoNovo);
            statement.setInt(2, idAnimal);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Deletar registro de vacinação
    public boolean deletarRegistroVacinacao(int idAnimal, int idVacinacao) {
        String query = "DELETE FROM RegistroVacinacaoAnimal WHERE id_animal = ? AND id_vacinacao = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idAnimal);
            statement.setInt(2, idVacinacao);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
