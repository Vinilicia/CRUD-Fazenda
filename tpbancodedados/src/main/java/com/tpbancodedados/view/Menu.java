package com.tpbancodedados.view;

import com.tpbancodedados.view.FuncionarioView;
import com.tpbancodedados.view.AnimalView;
import com.tpbancodedados.view.EquipamentoView;
import com.tpbancodedados.view.PlantacaoView;
import com.tpbancodedados.view.ProdutoView;

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
                    FuncionarioView.exibir();
                    break;
                case 2:
                    System.out.println("Administrando Animais...");
                    AnimalView.exibir();
                    break;
                case 3:
                    System.out.println("Administrando Equipamento...");
                    EquipamentoView.exibir();
                    break;
                case 4:
                    System.out.println("Administrando Plantação...");
                    PlantacaoView.exibir();
                    break;
                case 5:
                    System.out.println("Administrando Produto...");
                    ProdutoView.exibir();
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
