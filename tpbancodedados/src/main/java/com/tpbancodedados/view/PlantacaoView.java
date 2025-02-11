package com.tpbancodedados.view;

import java.util.Scanner;

public class PlantacaoView {

    public static void exibir() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE PLANTAÇÕES =====");
            System.out.println("1 - Cadastrar Plantação");
            System.out.println("2 - Buscar Plantações");
            System.out.println("3 - Editar Plantações");
            System.out.println("4 - Deletar Plantação");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Cadastrando Plantação...");
                    break;
                case 2:
                    System.out.println("Buscando Plantações...");
                    break;
                case 3:
                    System.out.println("Editando Plantações...");
                    break;
                case 4:
                    System.out.println("Deletando Plantação...");
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
