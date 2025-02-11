package com.tpbancodedados.controller;

import java.time.LocalDate;
import java.util.List;

import com.tpbancodedados.model.RegistroVacinacao;
import com.tpbancodedados.persistence.RegistroVacinacaoDAO;

public class RegistroVacinacaoController {
	private RegistroVacinacaoDAO registroVacinacaoDAO = new RegistroVacinacaoDAO();
	
	public boolean inserirRegistroVacinacao(RegistroVacinacao registro) {
		return registroVacinacaoDAO.inserirRegistroVacinacao(registro);
	}

	public boolean atualizarRegistroVacinacao(RegistroVacinacao registro) {
		return registroVacinacaoDAO.atualizarRegistroVacinacao(registro);
	}

	public boolean deletarRegistroVacinacao(int idAnimal, int idVacina) {
		return registroVacinacaoDAO.deletarRegistroVacinacao(idAnimal, idVacina);
	}

	public List<RegistroVacinacao> listarRegistroVacinacao() {
		return registroVacinacaoDAO.listarRegistrosVacinacao();
	}

	public List<RegistroVacinacao> listarRegistrosPorData(LocalDate data) {
		return registroVacinacaoDAO.listarRegistrosVacinacaoPorData(data);
	}

	public List<RegistroVacinacao> listarRegistrosPorVacina(int idVacina) {
		return registroVacinacaoDAO.listarRegistrosVacinacaoPorVacina(idVacina);
	}

	public List<RegistroVacinacao> listarRegistrosPorAnimal(int idAnimal) {
		return registroVacinacaoDAO.listarRegistrosVacinacaoPorAnimal(idAnimal);
	}
}
