package com.tpbancodedados.vision;

import com.tpbancodedados.vision.FuncionarioVision;
import com.tpbancodedados.vision.AnimalVision;
import com.tpbancodedados.vision.EquipamentoVision;

import java.util.Scanner;

public class Menu {
    public static void exibir() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Administrar Funcionários");
            System.out.println("2 - Administrar Animais");
            System.out.println("3 - Administrar Equipamento");
            System.out.println("4 - Administrar Plantação");
            System.out.println("5 - Administrar Produto");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Administrando Funcionários...");
                    FuncionarioVision.exibir();
                    break;
                case 2:
                    System.out.println("Administrando Animais...");
                    AnimalVision.exibir();
                    break;
                case 3:
                    System.out.println("Administrando Equipamento...");
                    EquipamentoVision.exibir();
                    break;
                case 4:
                    System.out.println("Administrando Plantação...");
                    break;
                case 5:
                    System.out.println("Administrando Produto...");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
