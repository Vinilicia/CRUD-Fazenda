package com.tpbancodedados.model;

public class RegistroVacinacao {
    private int idVacinacao;
    private int idAnimal;
    private String dataVacinacao;
    private String descricao;

    public int getIdVacina() {
        return idVacinacao;
    }

    public void setIdVacina(int idVacina) {
        this.idVacinacao = idVacina;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getDataVacinacao() {
        return dataVacinacao;
    }

    public void setDataVacinacao(String dataVacinacao) {
        this.dataVacinacao = dataVacinacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
