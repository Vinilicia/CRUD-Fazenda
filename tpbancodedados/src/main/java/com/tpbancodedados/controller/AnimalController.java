package com.tpbancodedados.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.tpbancodedados.model.Animal;
import com.tpbancodedados.model.Veterinario;
import com.tpbancodedados.model.VeterinarioAnimal;
import com.tpbancodedados.persistence.AnimalDAO;
import com.tpbancodedados.persistence.VeterinarioAnimalDAO;
import com.tpbancodedados.persistence.VeterinarioDAO;

public class AnimalController {
	private AnimalDAO animalDAO = new AnimalDAO();
	private VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
	private VeterinarioAnimalDAO veterinarioAnimalDAO = new VeterinarioAnimalDAO();

	public boolean inserirAnimal(Animal animal){
		return (animalDAO.inserirAnimal(animal) != -1);
	}

	public List<Animal> listarAnimais() {
		return animalDAO.listarAnimais();
	}

	public boolean atualizarAnimal(Animal novoAnimal) {
		return animalDAO.atualizarAnimal(novoAnimal);
	}

	public boolean deletarAnimal(int id) {
		return animalDAO.deletarAnimal(id);
	}

	public List<Veterinario> listarVeterinariosPorAnimal(int id_animal) {
		List<Veterinario> veterinarios = new ArrayList<Veterinario>();
		List<Integer> id_veterinarios = veterinarioAnimalDAO.buscarVeterinarioPorAnimal(id_animal);
		for (int id : id_veterinarios) {
			veterinarios.add(veterinarioDAO.buscarVeterinarioPorId(id));
		}
		return veterinarios;
	}

	public Animal buscarPorNome(String nome){
		return animalDAO.buscarAnimalPorNome(nome);
	}


	public List<Animal> filtrarDataNascimento(String data, boolean depois){
		List<Animal> animais = animalDAO.listarAnimais();
		List<Animal> animaisFiltrados = new ArrayList<Animal>();
		LocalDate dataParaFiltro = LocalDate.parse(data);

		for ( Animal animal : animais){
			LocalDate dataNascimento = animal.getDataNascimento();

			if (depois) {
				if (dataNascimento.isAfter(dataParaFiltro)){
					animaisFiltrados.add(animal);
				}
			} else {
				if (dataNascimento.isBefore(dataParaFiltro)){
					animaisFiltrados.add(animal);
				}
			}
		}

		return animais;
	}

	public boolean associarVeterinario(int idVeterinario, int idAnimal){
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


}
