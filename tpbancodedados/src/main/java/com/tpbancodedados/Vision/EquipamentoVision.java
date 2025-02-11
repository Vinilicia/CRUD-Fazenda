package com.tpbancodedados.vision;

import java.util.Scanner;

public class EquipamentoVision {

    public static void exibir() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE EQUIPAMENTOS =====");
            System.out.println("1 - Cadastrar Equipamento");
            System.out.println("2 - Buscar Equipamentos");
            System.out.println("3 - Editar Equipamentos");
            System.out.println("4 - Escolher Caseiro");
            System.out.println("5 - Deletar Equipamento");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Cadastrando Equipamento...");
                    break;
                case 2:
                    System.out.println("Buscando Equipamentos...");
                    break;
                case 3:
                    System.out.println("Editando Equipamentos...");
                    break;
                case 4:
                    System.out.println("Escolhendo Caseiro...");
                    // Chame aqui o método para buscar os funcionários
                    break;
                case 5:
                    System.out.println("Deletando Equipamento...");
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
