package com.tpbancodedados.Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.tpbancodedados.Model.Produto;

public class ProdutoDAO {

    // Insere um produto e retorna seu ID
    public int inserirProduto(Produto produto) {
        String query = "INSERT INTO Produto (nome, tipo, quantidade, unidade, id_plantacao) VALUES (?, ?, ?, ?, ?)";
        int idGerado = -1;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getTipo());
            statement.setFloat(3, produto.getQuantidade());
            statement.setString(4, produto.getUnidade());
            statement.setInt(5, produto.getIdPlantacao());

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

    // Lista todos os produtos
    public List<Produto> listarProdutos() {
        String query = "SELECT * FROM Produto";
        List<Produto> produtos = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(resultSet.getInt("id_produto"));
                produto.setNome(resultSet.getString("nome"));
                produto.setTipo(resultSet.getString("tipo"));
                produto.setQuantidade(resultSet.getFloat("quantidade"));
                produto.setUnidade(resultSet.getString("unidade"));
                produto.setIdPlantacao(resultSet.getInt("id_plantacao"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    // Busca um produto pelo ID
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
                produto.setNome(resultSet.getString("nome"));
                produto.setTipo(resultSet.getString("tipo"));
                produto.setQuantidade(resultSet.getFloat("quantidade"));
                produto.setUnidade(resultSet.getString("unidade"));
                produto.setIdPlantacao(resultSet.getInt("id_plantacao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produto;
    }

    // Atualiza os dados de um produto
    public boolean atualizarProduto(Produto produto) {
        String query = "UPDATE Produto SET nome = ?, tipo = ?, quantidade = ?, unidade = ?, id_plantacao = ? WHERE id_produto = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getTipo());
            statement.setFloat(3, produto.getQuantidade());
            statement.setString(4, produto.getUnidade());
            statement.setInt(5, produto.getIdPlantacao());
            statement.setInt(6, produto.getIdProduto());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Deleta um produto pelo ID
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
