package com.tpbancodedados.view;

import java.util.Scanner;

public class AgronomoView {

    public static void exibir() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE AGRÔNOMOS =====");
            System.out.println("1 - Cadastrar Agrônomo");
            System.out.println("2 - Buscar Agrônomos");
            System.out.println("3 - Editar Agrônomo");
            System.out.println("4 - Escolher Plantação");
            System.out.println("5 - Deletar Agrônomo");
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
                    System.out.println("Cadastrar Agrônomos...");
                    // Chame aqui o método que cadastra o Agrônomo
                    break;
                case 2:
                    System.out.println("Buscando Agrônomos...");
                    // Chame aqui o método para buscar os Agrônomos
                    break;
                case 3:
                    System.out.println("Editando Agrônomo...");
                    // Chame aqui o método para deletar o Agrônomo
                    break;
                case 4:
                    System.out.println("Escolhendo Plantação...");
                    // Chame aqui o método para deletar o Agrônomo
                    break;
                case 5:
                    System.out.println("Deletando Agrônomo...");
                    // Chame aqui o método para deletar o Agrônomo
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
