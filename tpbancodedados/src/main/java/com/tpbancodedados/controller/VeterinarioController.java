package com.tpbancodedados.controller;

import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.model.Animal;
import com.tpbancodedados.model.Veterinario;
import com.tpbancodedados.model.VeterinarioAnimal;
import com.tpbancodedados.persistence.AnimalDAO;
import com.tpbancodedados.persistence.VeterinarioAnimalDAO;
import com.tpbancodedados.persistence.VeterinarioDAO;

public class VeterinarioController {
	private VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
	private VeterinarioAnimalDAO veterinarioAnimalDAO = new VeterinarioAnimalDAO();
	private AnimalDAO animalDAO = new AnimalDAO();
	
	public boolean inserirVeterinario(Veterinario veterinario) {
		return veterinarioDAO.inserirVeterinario(veterinario);
	}

	public List<Veterinario> listarVeterinarios() {
		return veterinarioDAO.listarVeterinarios();
	}

	public Veterinario buscarVeterinarioPorCRMV(String crmv) {
		return veterinarioDAO.buscarVeterinarioPorCRMV(crmv);
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
				if (veterinario.getSalario() >= salarioBase){
					veterinariosFiltrados.add(veterinario);
				}
			} else {
				if (veterinario.getSalario() <= salarioBase){
					veterinariosFiltrados.add(veterinario);
				}
			}
		}
		return veterinariosFiltrados;
	}

	public boolean associarAnimal(int idVeterinario, int idAnimal){
		Veterinario vet = veterinarioDAO.buscarVeterinarioPorId(idVeterinario);
		Animal animal = animalDAO.buscarAnimalPorId(idAnimal);

		if ( vet != null && animal != null){
			VeterinarioAnimal veterinarioAnimal = new VeterinarioAnimal();
			veterinarioAnimal.setIdAnimal(idAnimal);
			veterinarioAnimal.setIdVeterinario(idVeterinario);
			return veterinarioAnimalDAO.inserirVeterinarioAnimal(veterinarioAnimal);
		}
		
		return false;
	}

	public boolean deletarVeterinarioAnimal(int idVeterinario, int idAnimal){
		Veterinario vet = veterinarioDAO.buscarVeterinarioPorId(idVeterinario);
		Animal animal = animalDAO.buscarAnimalPorId(idAnimal);

		if ( vet != null && animal != null){
			return veterinarioAnimalDAO.deletarVeterinarioAnimal(idVeterinario, idAnimal);
		}
		
		return false;
	}

	public List<Animal> listarAnimaisPorVeterinario(int idVeterinario) {
        List<Animal> animais = new ArrayList<>();
        List<Integer> idAnimais = veterinarioAnimalDAO.buscarAnimalPorVeterinario(idVeterinario);
        for (int id : idAnimais) {
            animais.add(animalDAO.buscarAnimalPorId(id));
        }
        return animais;
    }


}
