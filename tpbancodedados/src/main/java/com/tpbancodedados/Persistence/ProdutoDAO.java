package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoDAO {

    // Método para inserir um produto
    public void inserirProduto(Produto produto) {
        String query = "INSERT INTO Produto (nome, tipo, quantidade, unidade, id_plantacao) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Definir os parâmetros da consulta
            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getTipo());
            statement.setFloat(3, produto.getQuantidade());
            statement.setString(4, produto.getUnidade());
            statement.setInt(5, produto.getIdPlantacao());

            // Executa a operação
            statement.executeUpdate();
            System.out.println("Produto inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
