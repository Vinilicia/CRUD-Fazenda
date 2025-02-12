package com.tpbancodedados.model;

import java.time.LocalDate;
public class Animal {
    private int idAnimal;
    private String nome;
    private String especie;
    private LocalDate dataNascimento;

    // Getters e Setters
    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

	@Override
	public String toString() {
		return "ID = " + idAnimal + 
			", Nome = " + nome + 
			", Espécie = " + especie + 
			", Data de nascimento = " + dataNascimento;
	}

}
