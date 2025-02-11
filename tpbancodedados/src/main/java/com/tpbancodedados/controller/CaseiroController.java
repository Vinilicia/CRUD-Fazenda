package com.tpbancodedados.controller;

import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.model.Caseiro;
import com.tpbancodedados.persistence.CaseiroDAO;
import com.tpbancodedados.persistence.CaseiroEquipamentoDAO;

public class CaseiroController {
	private CaseiroDAO caseiroDAO = new CaseiroDAO();
	private CaseiroEquipamentoDAO caseiroEquipamentoDAO = new CaseiroEquipamentoDAO();
	
	public boolean inserirCaseiro(Caseiro caseiro) {
		return caseiroDAO.inserirCaseiro(caseiro);
	}

	public List<Caseiro> listarCaseiros() {
		return caseiroDAO.listarCaseiros();
	}

	public boolean atualizarCaseiro(Caseiro novoCaseiro) {
		return caseiroDAO.atualizarCaseiro(novoCaseiro);
	}

	public boolean deletarCaseiro(int id) {
		return caseiroDAO.deletarCaseiro(id);
	}

	public List<Caseiro> filtrarPorSalario(double salarioBase, boolean maior) {
		List<Caseiro> caseiros = caseiroDAO.listarCaseiros();
		List<Caseiro> caseirosFiltrados = new ArrayList<Caseiro>();
		for (Caseiro caseiro : caseiros){
			if (maior) {
				if (caseiro.getSalario() > salarioBase){
					caseirosFiltrados.add(caseiro);
				}
			} else {
				if (caseiro.getSalario() < salarioBase){
					caseirosFiltrados.add(caseiro);
				}
			}
		}
		return caseirosFiltrados;
	}

}

