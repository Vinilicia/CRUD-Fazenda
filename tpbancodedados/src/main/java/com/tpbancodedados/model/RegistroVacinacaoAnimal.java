package com.tpbancodedados.model;

public class RegistroVacinacaoAnimal {
    private int idAnimal;
    private int idVacinacao;

    public RegistroVacinacaoAnimal() {}

    public RegistroVacinacaoAnimal(int idAnimal, int idVacinacao) {
        this.idAnimal = idAnimal;
        this.idVacinacao = idVacinacao;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getIdVacinacao() {
        return idVacinacao;
    }

    public void setIdVacinacao(int idVacinacao) {
        this.idVacinacao = idVacinacao;
    }

    @Override
    public String toString() {
        return "RegistroVacinacaocaocaoAnimal{" +
                "idAnimal=" + idAnimal +
                ", idVacinacaocao=" + idVacinacao +
                '}';
    }
}

