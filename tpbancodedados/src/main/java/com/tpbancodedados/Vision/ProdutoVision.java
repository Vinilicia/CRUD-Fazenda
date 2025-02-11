package com.tpbancodedados.vision;

import java.util.Scanner;

public class ProdutoVision {

    public static void exibir() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE PRODUTOS =====");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Buscar Produtos");
            System.out.println("3 - Editar Produtos");
            System.out.println("4 - Deletar Produto");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Cadastrando Produto...");
                    break;
                case 2:
                    System.out.println("Buscando Produtos...");
                    break;
                case 3:
                    System.out.println("Editando Produtos...");
                    break;
                case 4:
                    System.out.println("Deletando Produto...");
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
