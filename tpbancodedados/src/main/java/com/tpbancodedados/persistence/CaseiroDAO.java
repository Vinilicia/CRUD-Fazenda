package com.tpbancodedados.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.model.Caseiro;
import com.tpbancodedados.model.Funcionario;

public class CaseiroDAO {

    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public boolean inserirCaseiro(Caseiro agronomo) {
		Funcionario funcionario = new Funcionario();

		funcionario.setCpf(agronomo.getCpf());
		funcionario.setNome(agronomo.getNome());
		funcionario.setSalario(agronomo.getSalario());
        int idFuncionario = funcionarioDAO.inserirFuncionario(funcionario);
        if (idFuncionario == -1) {
            System.out.println("Erro ao inserir funcionário base para agronomo.");
            return false;
        }

        String queryCaseiro = "INSERT INTO Caseiro (id_funcionario) VALUES (?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(queryCaseiro)) {

            statement.setInt(1, idFuncionario);

            statement.executeUpdate();
            System.out.println("Caseiro inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
			return false;
        }
		return true;
    }

	public List<Caseiro> listarCaseiros() {
		String query = "SELECT * FROM Caseiro";
		List<Caseiro> agronomos = new ArrayList<>();

		try (Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery()){


			while (resultSet.next()) {
				Funcionario funcionario = funcionarioDAO.buscarFuncionarioPorId(resultSet.getInt("id_funcionario"));
				Caseiro agronomo = new Caseiro();
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

	public Caseiro buscarCaseiroPorId(int id){
		String query = "SELECT * FROM Caseiro WHERE id_funcionario = ?";
		Caseiro agronomo = null;

		try (Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery()){

			Funcionario funcionario = funcionarioDAO.buscarFuncionarioPorId(id);

			if (resultSet.next()){
				agronomo = new Caseiro();
				agronomo.setId(resultSet.getInt("id_funcionario"));
				agronomo.setNome(funcionario.getNome());
				agronomo.setCpf(funcionario.getCpf());
				agronomo.setSalario(funcionario.getSalario());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return agronomo;
	}

	public boolean atualizarCaseiro(Caseiro agronomo) {
		
		Funcionario funcionario = new Funcionario();
		funcionario.setCpf(agronomo.getCpf());
		funcionario.setId(agronomo.getId());
		funcionario.setNome(agronomo.getNome());
		funcionario.setSalario(agronomo.getSalario());

		return funcionarioDAO.atualizarFuncionario(funcionario);
	}

    public boolean deletarCaseiro(int id) {
        String query = "DELETE FROM Caseiro WHERE id_funcionario = ?";

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
