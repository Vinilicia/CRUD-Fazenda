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

    public boolean inserirCaseiro(Caseiro caseiro) {
		Funcionario funcionario = new Funcionario();

		funcionario.setCpf(caseiro.getCpf());
		funcionario.setNome(caseiro.getNome());
		funcionario.setSalario(caseiro.getSalario());
        int idFuncionario = funcionarioDAO.inserirFuncionario(funcionario);
        if (idFuncionario == -1) {
            System.out.println("Erro ao inserir funcionário base para caseiro.");
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
		List<Caseiro> caseiros = new ArrayList<>();

		try (Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery()){


			while (resultSet.next()) {
				int idFuncionario = resultSet.getInt("id_funcionario");
				Funcionario funcionario = funcionarioDAO.buscarFuncionarioPorId(resultSet.getInt("id_funcionario"));
				Caseiro caseiro = new Caseiro();
				caseiro.setId(idFuncionario);
				caseiro.setNome(funcionario.getNome());
				caseiro.setCpf(funcionario.getCpf());
				caseiro.setSalario(funcionario.getSalario());

				caseiros.add(caseiro);
			}	
		} catch (SQLException e) {
            e.printStackTrace();
        }

		return caseiros;
	}

	public Caseiro buscarCaseiroPorId(int id){
		String query = "SELECT * FROM Caseiro WHERE id_funcionario = ?";
		Caseiro caseiro = null;

		try (Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			Funcionario funcionario = funcionarioDAO.buscarFuncionarioPorId(id);

			if (resultSet.next()){
				caseiro = new Caseiro();
				caseiro.setId(resultSet.getInt("id_funcionario"));
				caseiro.setNome(funcionario.getNome());
				caseiro.setCpf(funcionario.getCpf());
				caseiro.setSalario(funcionario.getSalario());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return caseiro;
	}

	public boolean atualizarCaseiro(Caseiro caseiro) {
		
		Funcionario funcionario = new Funcionario();
		funcionario.setCpf(caseiro.getCpf());
		funcionario.setId(caseiro.getId());
		funcionario.setNome(caseiro.getNome());
		funcionario.setSalario(caseiro.getSalario());

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
