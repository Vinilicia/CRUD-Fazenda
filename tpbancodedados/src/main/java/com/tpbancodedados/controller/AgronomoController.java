package com.tpbancodedados.Controller;

import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.Model.Agronomo;
import com.tpbancodedados.Persistence.AgronomoDAO;
import com.tpbancodedados.Persistence.FuncionarioDAO;

public class AgronomoController {
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
	private AgronomoDAO agronomoDAO = new AgronomoDAO();

	public boolean inserirAgronomo(Agronomo agronomo) {
		return agronomoDAO.inserirAgronomo(agronomo);
	}

	public List<Agronomo> listarAgronomos() {
		return agronomoDAO.listarAgronomos();
	}

	public boolean atualizarAgronomo(Agronomo novoAgronomo) {
		return agronomoDAO.atualizarAgronomo(novoAgronomo);
	}

	public boolean deletarAgronomo(int id) {
		return agronomoDAO.deletarAgronomo(id);
	}

	public List<Agronomo> filtrarPorSalario(double salarioBase, boolean maior) {
		List<Agronomo> agronomos = agronomoDAO.listarAgronomos();
		List<Agronomo> agronomosFiltrados = new ArrayList<Agronomo>();
		for (Agronomo agronomo : agronomos){
			if (maior) {
				if (agronomo.getSalario() > salarioBase){
					agronomosFiltrados.add(agronomo);
				}
			} else {
				if (agronomo.getSalario() < salarioBase){
					agronomosFiltrados.add(agronomo);
				}
			}
		}
		return agronomosFiltrados;
	}

}
