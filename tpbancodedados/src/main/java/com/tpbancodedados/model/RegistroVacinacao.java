package com.tpbancodedados.model;

import java.time.LocalDate;

public class RegistroVacinacao {
    private int idAnimal;
    private int idVacinacao;
    private LocalDate dataVacinacao;

    public RegistroVacinacao() {}

    public RegistroVacinacao(int idAnimal, int idVacinacao, LocalDate dataVacinacao) {
        this.idAnimal = idAnimal;
        this.idVacinacao = idVacinacao;
        this.dataVacinacao = dataVacinacao;
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

    public LocalDate getDataVacinacao() {
        return dataVacinacao;
    }

    public void setDataVacinacao(LocalDate dataVacinacao) {
        this.dataVacinacao = dataVacinacao;
    }

    @Override
    public String toString() {
        return "RegistroVacinacaoAnimal{" +
                "idAnimal=" + idAnimal +
                ", idVacinacao=" + idVacinacao +
                ", dataVacinacao=" + dataVacinacao +
                '}';
    }
}
