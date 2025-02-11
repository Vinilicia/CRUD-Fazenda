package com.tpbancodedados.controller;

import com.tpbancodedados.model.Vacina;
import com.tpbancodedados.persistence.VacinaDAO;

import java.util.List;

public class VacinaController {
    
    private VacinaDAO vacinaDAO = new VacinaDAO();

    public boolean criarVacina(Vacina vacina) {
        return vacinaDAO.inserirVacina(vacina);
    }

    public boolean atualizarVacina(Vacina vacina) {
        return vacinaDAO.atualizarVacina(vacina);
    }

    public List<Vacina> listarTodasVacinas() {
        return vacinaDAO.listarVacinas();
    }

    public boolean deletarVacina(int idVacina) {
        return vacinaDAO.deletarVacina(idVacina);
    }

	public Vacina buscarPelaDescricao(String descricao){
		return vacinaDAO.buscarVacinaPorDescricao(descricao);
	}
}
