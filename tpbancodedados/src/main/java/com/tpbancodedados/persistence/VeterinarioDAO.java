package com.tpbancodedados.Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.Model.Funcionario;
import com.tpbancodedados.Model.Veterinario;

public class VeterinarioDAO {

    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public boolean inserirVeterinario(Veterinario veterinario) {
		Funcionario funcionario = new Funcionario();

		funcionario.setCpf(veterinario.getCpf());
		funcionario.setNome(veterinario.getNome());
		funcionario.setSalario(veterinario.getSalario());
        int idFuncionario = funcionarioDAO.inserirFuncionario(funcionario);

        if (idFuncionario == -1) {
            System.out.println("Erro ao inserir funcionário base para veterinário.");
            return false;
        }

        String queryVeterinario = "INSERT INTO Veterinario (id_funcionario, registro_crmv) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(queryVeterinario)) {

            statement.setInt(1, idFuncionario);
            statement.setString(2, veterinario.getRegistroCrmv());

            statement.executeUpdate();
            System.out.println("Veterinário inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
			return false;
        }
		return true;
    }

	public List<Veterinario> listarVeterinarios() {
		String query = "SELECT * FROM Veterinario";
		List<Veterinario> veterinarios = new ArrayList<>();

		try (Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery()){


			while (resultSet.next()) {
				Funcionario funcionario = funcionarioDAO.buscarFuncionarioPorId(resultSet.getInt("id_funcionario"));
				Veterinario veterinario = new Veterinario();
				veterinario.setNome(funcionario.getNome());
				veterinario.setCpf(funcionario.getCpf());
				veterinario.setSalario(funcionario.getSalario());

				veterinarios.add(veterinario);
			}	
		} catch (SQLException e) {
            e.printStackTrace();
        }

		return veterinarios;
	}

	public Veterinario buscarVeterinarioPorId(int id){
		String query = "SELECT * FROM Veterinario WHERE id_funcionario = ?";
		Veterinario veterinario = null;

		try (Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery()){

			Funcionario funcionario = funcionarioDAO.buscarFuncionarioPorId(id);

			if (resultSet.next()){
				veterinario = new Veterinario();
				veterinario.setId(resultSet.getInt("id_funcionario"));
				veterinario.setRegistroCrmv(resultSet.getString("registro_CRMV"));
				veterinario.setNome(funcionario.getNome());
				veterinario.setCpf(funcionario.getCpf());
				veterinario.setSalario(funcionario.getSalario());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return veterinario;
	}

	public boolean atualizarVeterinario(Veterinario veterinario) {
		String query = "UPDATE Veterinario SET registro_CRMV = ? WHERE id_funcionario = ?";
		
		Funcionario funcionario = new Funcionario();
		funcionario.setCpf(veterinario.getCpf());
		funcionario.setId(veterinario.getId());
		funcionario.setNome(veterinario.getNome());
		funcionario.setSalario(veterinario.getSalario());

		funcionarioDAO.atualizarFuncionario(funcionario);

		try (Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement(query)){
			
			statement.setString(1, veterinario.getRegistroCrmv());
			statement.setInt(2, veterinario.getId());

			int rowsUpdated = statement.executeUpdate();
			return rowsUpdated > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

    public boolean deletarVeterinario(int id) {
        String query = "DELETE FROM Veterinario WHERE id_funcionario = ?";

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
