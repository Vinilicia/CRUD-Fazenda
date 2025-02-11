package com.tpbancodedados.controller;

import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.model.Veterinario;
import com.tpbancodedados.persistence.VeterinarioAnimalDAO;
import com.tpbancodedados.persistence.VeterinarioDAO;

public class VeterinarioController {
	private VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
	private VeterinarioAnimalDAO veterinarioAnimalDAO = new VeterinarioAnimalDAO();
	
	public boolean inserirVeterinario(Veterinario veterinario) {
		return veterinarioDAO.inserirVeterinario(veterinario);
	}

	public List<Veterinario> listarVeterinarios() {
		return veterinarioDAO.listarVeterinarios();
	}

	public boolean atualizarVeterinario(Veterinario novoVeterinario) {
		return veterinarioDAO.atualizarVeterinario(novoVeterinario);
	}

	public boolean deletarVeterinario(int id) {
		return veterinarioDAO.deletarVeterinario(id);
	}

	public List<Veterinario> filtrarPorSalario(double salarioBase, boolean maior) {
		List<Veterinario> veterinarios = veterinarioDAO.listarVeterinarios();
		List<Veterinario> veterinariosFiltrados = new ArrayList<Veterinario>();
		for (Veterinario veterinario : veterinarios){
			if (maior) {
				if (veterinario.getSalario() > salarioBase){
					veterinariosFiltrados.add(veterinario);
				}
			} else {
				if (veterinario.getSalario() < salarioBase){
					veterinariosFiltrados.add(veterinario);
				}
			}
		}
		return veterinariosFiltrados;
	}

}
