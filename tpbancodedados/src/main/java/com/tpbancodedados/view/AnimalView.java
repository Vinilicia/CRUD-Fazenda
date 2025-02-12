package com.tpbancodedados.view;

import java.util.List;
import java.util.Scanner;

import com.tpbancodedados.model.Animal;

import com.tpbancodedados.view.RecebedorInput;

public class AnimalView {

    public static void exibir() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE ANIMAIS =====");
            System.out.println("1 - Cadastrar Animal");
            System.out.println("2 - Buscar Animais");
            System.out.println("3 - Editar Animais");
            System.out.println("4 - Registrar Vacinação");
            System.out.println("5 - Buscar Registros de Vacinação por Animal");
            System.out.println("6 - Deletar Registros de Vacinação por Animal");
            System.out.println("7 - Deletar Animal");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            if(scanner.hasNextInt()){
                opcao = scanner.nextInt();
            }
            else{
                opcao = -1;
            }
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Cadastrando Animal...");
                    break;
                case 2:
                    System.out.println("Buscando Animais...");
                    break;
                case 3:
                    System.out.println("Editando Animais...");
                    break;
                case 4:
                    System.out.println("Registrando Vacinação...");
                    // Chame aqui o método para buscar os funcionários
                    break;
                case 5:
                    System.out.println("Escolhendo Veterinário...");
                    break;
                case 6:
                    System.out.println("Digite o ID do Animal: ");
                    break;
                case 7:
                    System.out.println("Deletando Animal...");
                    break;
                case 0:
                    System.out.println("Voltando...");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    public static void exibirAnimais(List<Animal> animais){
        if (animais.isEmpty()){
            System.out.println("Nenhum Animal encontrado.");
            return;
        }
        for (Animal animal : animais){
            System.out.println("ID: " + animal.getIdAnimal() + ", Nome: " + animal.getNome() +
                    ", Espécie: " + animal.getEspecie() + ", Data de Nascimento: " + animal.getDataNascimento());
        }
    }
}
