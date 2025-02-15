package com.tpbancodedados.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.model.Produto;

public class ProdutoDAO {

    public int inserirProduto(Produto produto) {
        String query = "INSERT INTO Produto (tipo, quantidade, unidade, id_plantacao) VALUES (?, ?, ?, ?)";
        int idGerado = -1;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, produto.getTipo());
            statement.setFloat(2, (float)produto.getQuantidade());
            statement.setString(3, produto.getUnidade());
            statement.setInt(4, produto.getIdPlantacao());

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

    public List<Produto> listarProdutos() {
        String query = "SELECT * FROM Produto";
        List<Produto> produtos = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(resultSet.getInt("id_produto"));
                produto.setTipo(resultSet.getString("tipo"));
                produto.setQuantidade((double)resultSet.getFloat("quantidade"));
                produto.setUnidade(resultSet.getString("unidade"));
                produto.setIdPlantacao(resultSet.getInt("id_plantacao"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public Produto buscarProdutoPorId(int id) {
        String query = "SELECT * FROM Produto WHERE id_produto = ?";
        Produto produto = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                produto = new Produto();
                produto.setIdProduto(resultSet.getInt("id_produto"));
                produto.setTipo(resultSet.getString("tipo"));
                produto.setQuantidade((double)resultSet.getFloat("quantidade"));
                produto.setUnidade(resultSet.getString("unidade"));
                produto.setIdPlantacao(resultSet.getInt("id_plantacao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produto;
    }

	public List<Produto> listarProdutoPorTipo(String tipo) {
        String query = "SELECT * FROM Produto WHERE tipo = ?";
        Produto produto = null;
		List<Produto> produtos = new ArrayList<Produto>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, tipo);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                produto = new Produto();
                produto.setIdProduto(resultSet.getInt("id_produto"));
                produto.setTipo(resultSet.getString("tipo"));
                produto.setQuantidade((double)resultSet.getFloat("quantidade"));
                produto.setUnidade(resultSet.getString("unidade"));
                produto.setIdPlantacao(resultSet.getInt("id_plantacao"));
				produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
			return null;
        }

        return produtos;
    }

	public List<Produto> listarProdutoPorPlantacao(int idPlantacao) {
        String query = "SELECT * FROM Produto WHERE id_plantacao = ?";
        Produto produto = null;
		List<Produto> produtos = new ArrayList<Produto>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idPlantacao);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                produto = new Produto();
                produto.setIdProduto(resultSet.getInt("id_produto"));
                produto.setTipo(resultSet.getString("tipo"));
                produto.setQuantidade((double)(resultSet.getFloat("quantidade")));
                produto.setUnidade(resultSet.getString("unidade"));
                produto.setIdPlantacao(resultSet.getInt("id_plantacao"));
				produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
			return null;
        }

        return produtos;
    }

    public boolean atualizarProduto(Produto produto) {
        String query = "UPDATE Produto SET tipo = ?, quantidade = ?, unidade = ?, id_plantacao = ? WHERE id_produto = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, produto.getTipo());
            statement.setFloat(2, (float)produto.getQuantidade());
            statement.setString(3, produto.getUnidade());
            statement.setInt(4, produto.getIdPlantacao());
            statement.setInt(5, produto.getIdProduto());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deletarProduto(int id) {
        String query = "DELETE FROM Produto WHERE id_produto = ?";

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
