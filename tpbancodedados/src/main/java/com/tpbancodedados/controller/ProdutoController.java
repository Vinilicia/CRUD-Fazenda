package com.tpbancodedados.controller;

import com.tpbancodedados.model.Produto;
import com.tpbancodedados.persistence.ProdutoDAO;

import java.util.List;

public class ProdutoController {
    
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public boolean inserirProduto(Produto produto) {
        return (produtoDAO.inserirProduto(produto) != -1);
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listarProdutos();
    }

    public boolean atualizarProduto(Produto produto) {
        return produtoDAO.atualizarProduto(produto);
    }

    public boolean deletarProduto(int id) {
        return produtoDAO.deletarProduto(id);
    }

	public List<Produto> listaProdutosPorTipo(String tipo){
		return produtoDAO.listarProdutoPorTipo(tipo);
	}

	public List<Produto> listaProdutosPorPlantacao(int idPlantacao){
		return produtoDAO.listarProdutoPorPlantacao(idPlantacao);
	}
}
