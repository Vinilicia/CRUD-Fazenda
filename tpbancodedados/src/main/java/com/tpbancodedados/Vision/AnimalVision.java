package com.tpbancodedados.vision;

import java.util.Scanner;

public class AnimalVision {

    public static void exibir() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE ANIMAIS =====");
            System.out.println("1 - Cadastrar Animal");
            System.out.println("2 - Buscar Animais");
            System.out.println("3 - Editar Animais");
            System.out.println("4 - Registrar Vacinação");
            System.out.println("5 - Registrar Vacinação");
            System.out.println("6 - Deletar Animal");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
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
                    System.out.println("Escolher Veterinário...");
                    // Chame aqui o método para deletar o funcionário
                    break;
                case 6:
                    System.out.println("Deletando Animal...");
                    // Chame aqui o método para deletar o funcionário
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
}
