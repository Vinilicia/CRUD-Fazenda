package com.tpbancodedados.Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.Model.Funcionario;
import com.tpbancodedados.Model.Agronomo;

public class AgronomoDAO {

    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public boolean inserirAgronomo(Agronomo agronomo) {
		Funcionario funcionario = new Funcionario();

		funcionario.setCpf(agronomo.getCpf());
		funcionario.setNome(agronomo.getNome());
		funcionario.setSalario(agronomo.getSalario());
        int idFuncionario = funcionarioDAO.inserirFuncionario(funcionario);
        if (idFuncionario == -1) {
            System.out.println("Erro ao inserir funcionário base para agronomo.");
            return false;
        }

        String queryAgronomo = "INSERT INTO Agronomo (id_funcionario, area_especializacao) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(queryAgronomo)) {

            statement.setInt(1, idFuncionario);
            statement.setString(2, agronomo.getAreaEspecializacao());

            statement.executeUpdate();
            System.out.println("Agrônomo inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
			return false;
        }
		return true;
    }

	public List<Agronomo> listarAgronomos() {
		String query = "SELECT * FROM Agronomo";
		List<Agronomo> agronomos = new ArrayList<>();

		try (Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery()){


			while (resultSet.next()) {
				Funcionario funcionario = funcionarioDAO.buscarFuncionarioPorId(resultSet.getInt("id_funcionario"));
				Agronomo agronomo = new Agronomo();
				agronomo.setNome(funcionario.getNome());
				agronomo.setCpf(funcionario.getCpf());
				agronomo.setSalario(funcionario.getSalario());

				agronomos.add(agronomo);
			}	
		} catch (SQLException e) {
            e.printStackTrace();
        }

		return agronomos;
	}

	public Agronomo buscarAgronomoPorId(int id){
		String query = "SELECT * FROM Agronomo WHERE id_funcionario = ?";
		Agronomo agronomo = null;

		try (Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery()){

			Funcionario funcionario = funcionarioDAO.buscarFuncionarioPorId(id);

			if (resultSet.next()){
				agronomo = new Agronomo();
				agronomo.setId(resultSet.getInt("id_funcionario"));
				agronomo.setAreaEspecializacao(resultSet.getString("area_especializacao"));
				agronomo.setNome(funcionario.getNome());
				agronomo.setCpf(funcionario.getCpf());
				agronomo.setSalario(funcionario.getSalario());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return agronomo;
	}

	public boolean atualizarAgronomo(Agronomo agronomo) {
		String query = "UPDATE Agronomo SET area_especializacao = ? WHERE id_funcionario = ?";
		
		Funcionario funcionario = new Funcionario();
		funcionario.setCpf(agronomo.getCpf());
		funcionario.setId(agronomo.getId());
		funcionario.setNome(agronomo.getNome());
		funcionario.setSalario(agronomo.getSalario());

		funcionarioDAO.atualizarFuncionario(funcionario);

		try (Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement(query)){
			
			statement.setString(1, agronomo.getAreaEspecializacao());
			statement.setInt(2, agronomo.getId());

			int rowsUpdated = statement.executeUpdate();
			return rowsUpdated > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

    public boolean deletarAgronomo(int id) {
        String query = "DELETE FROM Agronomo WHERE id_funcionario = ?";

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
