package com.tpbancodedados.model;

public class Veterinario extends Funcionario {
    private String registroCrmv;

    public String getRegistroCrmv() {
        return registroCrmv;
    }

    public void setRegistroCrmv(String registroCrmv) {
        this.registroCrmv = registroCrmv;
    }

	@Override
	public String toString() {
		return "ID=" + getId() + 
			", Nome='" + getNome() + 
			"', CPF='" + getCpf() + 
			"', Salario=" + getSalario() + 
			", RegistroCRMV='" + registroCrmv + "'";
	}

}
