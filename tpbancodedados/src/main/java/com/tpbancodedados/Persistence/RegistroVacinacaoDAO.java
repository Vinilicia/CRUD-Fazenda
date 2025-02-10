package com.tpbancodedados.Persistence;

import com.tpbancodedados.Model.RegistroVacinacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistroVacinacaoDAO {

    // Inserir registro de vacinação
    public boolean inserirRegistroVacinacao(RegistroVacinacao registro) {
        String query = "INSERT INTO RegistroVacinacao (id_vacinacao, id_animal, data_vacinacao, descricao) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, registro.getIdVacina());
            statement.setInt(2, registro.getIdAnimal());
            statement.setString(3, registro.getDataVacinacao());
            statement.setString(4, registro.getDescricao());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Listar todos os registros de vacinação
    public List<RegistroVacinacao> listarRegistrosVacinacao() {
        String query = "SELECT * FROM RegistroVacinacao";
        List<RegistroVacinacao> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                RegistroVacinacao registro = new RegistroVacinacao();
                registro.setIdVacina(resultSet.getInt("id_vacinacao"));
                registro.setIdAnimal(resultSet.getInt("id_animal"));
                registro.setDataVacinacao(resultSet.getString("data_vacinacao"));
                registro.setDescricao(resultSet.getString("descricao"));
                lista.add(registro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Buscar registro de vacinação pelo ID
    public RegistroVacinacao buscarRegistroPorId(int idVacinacao) {
        String query = "SELECT * FROM RegistroVacinacao WHERE id_vacinacao = ?";
        RegistroVacinacao registro = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idVacinacao);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    registro = new RegistroVacinacao();
                    registro.setIdVacina(resultSet.getInt("id_vacinacao"));
                    registro.setIdAnimal(resultSet.getInt("id_animal"));
                    registro.setDataVacinacao(resultSet.getString("data_vacinacao"));
                    registro.setDescricao(resultSet.getString("descricao"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registro;
    }

    // Atualizar registro de vacinação
    public boolean atualizarRegistroVacinacao(RegistroVacinacao registro) {
        String query = "UPDATE RegistroVacinacao SET id_animal = ?, data_vacinacao = ?, descricao = ? WHERE id_vacinacao = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, registro.getIdAnimal());
            statement.setString(2, registro.getDataVacinacao());
            statement.setString(3, registro.getDescricao());
            statement.setInt(4, registro.getIdVacina());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Deletar registro de vacinação
    public boolean deletarRegistroVacinacao(int idVacinacao) {
        String query = "DELETE FROM RegistroVacinacao WHERE id_vacinacao = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idVacinacao);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
