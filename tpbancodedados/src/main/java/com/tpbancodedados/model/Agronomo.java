package com.tpbancodedados.model;

public class Agronomo extends Funcionario {
    private String areaEspecializacao;

    public String getAreaEspecializacao() {
        return areaEspecializacao;
    }

    public void setAreaEspecializacao(String areaEspecializacao) {
        this.areaEspecializacao = areaEspecializacao;
    }

	@Override
	public String toString() {
		return "ID = " + getId() + 
			", Nome = " + getNome() + 
			", CPF = " + getCpf() + 
			", Salário = " + getSalario() + 
			", Area de especializacao = " + areaEspecializacao;
	}

}
