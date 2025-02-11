package com.tpbancodedados.Model;

public class Funcionario {
    private int idFuncionario;
    private String nome;
    private String cpf;
    private double salario;

    // Getters e Setters
    public int getId() {
        return idFuncionario;
    }

    public void setId(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
