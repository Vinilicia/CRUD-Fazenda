package com.tpbancodedados.model;

public class Caseiro extends Funcionario {
    

	@Override
	public String toString() {
		return "ID = " + getId() + 
			", Nome = " + getNome() + 
			", CPF = " + getCpf() + 
			", Salário = " + getSalario();
	}
}
