package com.tpbancodedados.vision;

import java.util.Scanner;

public class VeterinarioVision {

    public static void exibir() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE VETERINÁRIOS =====");
            System.out.println("1 - Cadastrar Veterinário");
            System.out.println("2 - Buscar Veterinários");
            System.out.println("3 - Editar Veterinário");
            System.out.println("4 - Escolher Animal");
            System.out.println("5 - Deletar Veterinário");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Cadastrar Veterinários...");
                    // Chame aqui o método que cadastra o Veterinário
                    break;
                case 2:
                    System.out.println("Buscando Veterinários...");
                    // Chame aqui o método para buscar os Veterinários
                    break;
                case 3:
                    System.out.println("Editando Veterinário...");
                    // Chame aqui o método para deletar o Veterinário
                    break;
                case 4:
                    System.out.println("Escolhendo Animal...");
                    // Chame aqui o método para deletar o Veterinário
                    break;
                case 5:
                    System.out.println("Deletando Veterinário...");
                    // Chame aqui o método para deletar o Veterinário
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
