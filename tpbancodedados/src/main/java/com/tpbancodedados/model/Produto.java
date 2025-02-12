package com.tpbancodedados.model;

public class Produto {
    private int idProduto;
    private String tipo;
    private double quantidade;
    private String unidade;
    private int idPlantacao;

    // Getters e Setters
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public int getIdPlantacao() {
        return idPlantacao;
    }

    public void setIdPlantacao(int idPlantacao) {
        this.idPlantacao = idPlantacao;
    }

	@Override
public String toString() {
    return "ID = " + idProduto + 
	" , Tipo = " + tipo + 
	" , ID Plantacao = " + idPlantacao;
}

}
