package com.tpbancodedados.controller;

import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.model.Equipamento;
import com.tpbancodedados.model.Caseiro;
import com.tpbancodedados.model.CaseiroEquipamento;
import com.tpbancodedados.persistence.CaseiroDAO;
import com.tpbancodedados.persistence.EquipamentoDAO;
import com.tpbancodedados.persistence.CaseiroEquipamentoDAO;

public class CaseiroController {
	private CaseiroDAO caseiroDAO = new CaseiroDAO();
	private EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
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
				if (caseiro.getSalario() >= salarioBase){
					caseirosFiltrados.add(caseiro);
				}
			} else {
				if (caseiro.getSalario() <= salarioBase){
					caseirosFiltrados.add(caseiro);
				}
			}
		}
		return caseirosFiltrados;
	}

	public boolean associarEquipamento(int idCaseiro, int idEquipamento){
		Caseiro caseiro = caseiroDAO.buscarCaseiroPorId(idCaseiro);
		Equipamento equipamento = equipamentoDAO.buscarEquipamentoPorId(idEquipamento);

		if ( caseiro != null && equipamento != null){
			CaseiroEquipamento caseiroEquipamento = new CaseiroEquipamento();
			caseiroEquipamento.setIdEquipamento(idEquipamento);
			caseiroEquipamento.setIdCaseiro(idCaseiro);
			return caseiroEquipamentoDAO.inserirCaseiroEquipamento(caseiroEquipamento);
		}
		
		return false;
	}

	public boolean deletarCaseiroEquipamento(int idCaseiro, int idEquipamento){
		Caseiro caseiro = caseiroDAO.buscarCaseiroPorId(idCaseiro);
		Equipamento equipamento = equipamentoDAO.buscarEquipamentoPorId(idEquipamento);

		if ( caseiro != null && equipamento != null){
			CaseiroEquipamento caseiroEquipamento = new CaseiroEquipamento();
			caseiroEquipamento.setIdEquipamento(idEquipamento);
			caseiroEquipamento.setIdCaseiro(idCaseiro);
			return caseiroEquipamentoDAO.deletarCaseiroEquipamento(idCaseiro, idEquipamento);
		}
		
		return false;
	}

	public List<Equipamento> listarEquipamentosDoCaseiro(int idCaseiro){
       	List<Equipamento> equipamentos = new ArrayList<>();
    	List<Integer> idEquipamentos = caseiroEquipamentoDAO.listarEquipamentosPorCaseiro(idCaseiro);
        for (int id : idEquipamentos) {
            equipamentos.add(equipamentoDAO.buscarEquipamentoPorId(id));
        }

		return equipamentos;
	}
}

