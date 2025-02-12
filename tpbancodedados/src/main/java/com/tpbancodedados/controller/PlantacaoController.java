package com.tpbancodedados.controller;

import com.tpbancodedados.model.Plantacao;
import com.tpbancodedados.persistence.PlantacaoDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlantacaoController {

    private PlantacaoDAO plantacaoDAO = new PlantacaoDAO();

    public boolean inserirPlantacao(Plantacao plantacao) {
        return (plantacaoDAO.inserirPlantacao(plantacao) != -1);
    }

    public List<Plantacao> listarPlantacoes() {
        return plantacaoDAO.listarPlantacoes();
    }

    public boolean atualizarPlantacao(Plantacao plantacao) {
        return plantacaoDAO.atualizarPlantacao(plantacao);
    }

    public boolean deletarPlantacao(int id) {
        return plantacaoDAO.deletarPlantacao(id);
    }

    public List<Plantacao> listarPlantacaoPorAgronomo(int idAgronomo) {
        return plantacaoDAO.listarPlantacaoPorAgronomo(idAgronomo);
    }

    public List<Plantacao> listarPlantacaoPorCultura(String cultura) {
        return plantacaoDAO.listarPlantacaoPorCultura(cultura);
    }

	public List<Plantacao> filtrarPorData(LocalDate data, boolean antes){
		List<Plantacao> plantacoes = new ArrayList<Plantacao>();
		plantacoes = plantacaoDAO.listarPlantacoes();
		List<Plantacao> plantacoesFiltradas = new ArrayList<Plantacao>();

		for( Plantacao plantacao : plantacoes){
			if (antes){
				if (data.isAfter(plantacao.getDataPlantio())){
					plantacoesFiltradas.add(plantacao);
				}
			} else {
				if (data.isBefore(plantacao.getDataPlantio())){
					plantacoesFiltradas.add(plantacao);
				}
			}
		}
		if (plantacoesFiltradas.isEmpty()) {
			return null;
		}
		return plantacoesFiltradas;
	}
}
