package com.tpbancodedados.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.tpbancodedados.controller.AnimalController;
import com.tpbancodedados.model.Animal;

public class AnimalView {
    private static AnimalController animalController = new AnimalController();
    private static Scanner scanner = new Scanner(System.in);

    public static void exibir() {
        int opcao;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE ANIMAIS =====");
            System.out.println("1 - Cadastrar Animal");
            System.out.println("2 - Listar Animais");
            System.out.println("3 - Editar Animal");
            System.out.println("4 - Deletar Animal");
            System.out.println("5 - Filtrar Animais por Data de Nascimento");
            System.out.println("6 - Associar Veterinário ao Animal");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            
            List<Animal> animais = animalController.listarAnimais();

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
            } else {
                opcao = -1;
            }
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    Animal animal = cadastrarAnimal();
                    if (animalController.inserirAnimal(animal)) {
                        System.out.println("Animal cadastrado com sucesso!");
                    } else {
                        System.out.println("Falha ao cadastrar animal.");
                    }
                    break;
                case 2:
                    exibirAnimais(animais);
                    break;
                case 3:
                    exibirAnimais(animais);
                    editarAnimal();
                    break;
                case 4:
                    exibirAnimais(animais);
                    deletarAnimal();
                    break;
                case 5:
                    filtrarAnimaisPorDataNascimento();
                    break;
                case 6:
                    associarVeterinario();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static Animal cadastrarAnimal() {
        Animal animal = new Animal();

        System.out.print("Nome: ");
        animal.setNome(scanner.nextLine());
        System.out.print("Espécie: ");
        animal.setEspecie(scanner.nextLine());
        System.out.print("Data de Nascimento (yyyy-MM-dd): ");
        animal.setDataNascimento(RecebedorInput.receberInputValidado(LocalDate.class));
        
        return animal;
    }

    private static void editarAnimal() {
        System.out.print("Digite o ID do Animal a ser editado: ");
        int idAnimal = RecebedorInput.receberInputValidado(Integer.class);

        Animal animal = new Animal();
        animal.setIdAnimal(idAnimal);

        System.out.print("Nome: ");
        animal.setNome(scanner.nextLine());
        System.out.print("Espécie: ");
        animal.setEspecie(scanner.nextLine());
        System.out.print("Data de Nascimento (yyyy-MM-dd): ");
        animal.setDataNascimento(RecebedorInput.receberInputValidado(LocalDate.class));

        if (animalController.atualizarAnimal(animal)) {
            System.out.println("Animal atualizado com sucesso!");
        } else {
            System.out.println("Falha ao atualizar animal.");
        }
    }

    private static void deletarAnimal() {
        System.out.print("Digite o ID do Animal a ser deletado: ");
        int idAnimal = RecebedorInput.receberInputValidado(Integer.class);
        
        if (animalController.deletarAnimal(idAnimal)) {
            System.out.println("Animal deletado com sucesso!");
        } else {
            System.out.println("Falha ao deletar animal.");
        }
    }

    private static void filtrarAnimaisPorDataNascimento() {
        System.out.print("Digite a data de referência (yyyy-MM-dd): ");
        String dataReferencia = scanner.nextLine();
        System.out.print("Filtrar após a data? (s/n): ");
        boolean depois = scanner.nextLine().equalsIgnoreCase("s");

        List<Animal> animaisFiltrados = animalController.filtrarDataNascimento(dataReferencia, depois);
        
        if (animaisFiltrados == null || animaisFiltrados.isEmpty()) {
            System.out.println("Nenhum animal encontrado.");
        } else {
            exibirAnimais(animaisFiltrados);
        }
    }

    private static void associarVeterinario() {
        System.out.print("Digite o ID do Veterinário: ");
        int idVeterinario = RecebedorInput.receberInputValidado(Integer.class);

        System.out.print("Digite o ID do Animal: ");
        int idAnimal = RecebedorInput.receberInputValidado(Integer.class);

        if (animalController.associarVeterinario(idVeterinario, idAnimal)) {
            System.out.println("Veterinário associado com sucesso ao animal!");
        } else {
            System.out.println("Falha ao associar veterinário ao animal.");
        }
    }

    public static void exibirAnimais(List<Animal> animais) {
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal encontrado.");
            return;
        }
        for (Animal animal : animais) {
            System.out.println(animal);
        }
    }
}
