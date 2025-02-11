package com.tpbancodedados.controller;

import com.tpbancodedados.model.Equipamento;
import com.tpbancodedados.model.EstadoEquipamento;
import com.tpbancodedados.persistence.CaseiroEquipamentoDAO;
import com.tpbancodedados.persistence.EquipamentoDAO;

import java.util.ArrayList;
import java.util.List;

public class EquipamentoController {
    private  EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
	private CaseiroEquipamentoDAO caseiroEquipamentoDAO = new CaseiroEquipamentoDAO();

    public boolean adicionarEquipamento(Equipamento equipamento) {
        return (equipamentoDAO.inserirEquipamento(equipamento) != -1);
    }

    public List<Equipamento> listarTodosEquipamentos() {
        return equipamentoDAO.listarEquipamentos();
    }

    public List<Equipamento> buscarEquipamentosPorEstado(EstadoEquipamento estado) {
        return equipamentoDAO.buscarEquipamentosPorEstado(estado);
    }

    public boolean atualizarEquipamento(Equipamento equipamento) {
        return equipamentoDAO.atualizarEquipamento(equipamento);
    }

    public boolean removerEquipamento(int idEquipamento) {
        return equipamentoDAO.deletarEquipamento(idEquipamento);
    }

	public List<Equipamento> listarEquipamentosPorCaseiro(int idCaseiro){
		List<Integer> idEquipamentos = caseiroEquipamentoDAO.listarEquipamentosPorCaseiro(idCaseiro);
		List<Equipamento> equipamentos = new ArrayList<Equipamento>();

		for ( int id : idEquipamentos){
			equipamentos.add(equipamentoDAO.buscarEquipamentoPorId(id));
		}
		return equipamentos;
	}
}
