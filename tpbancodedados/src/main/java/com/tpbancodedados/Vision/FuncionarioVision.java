package com.tpbancodedados.vision;

import com.tpbancodedados.vision.AgronomoVision;
import com.tpbancodedados.vision.CaseiroVision;
import com.tpbancodedados.vision.VeterinarioVision;

import java.util.Scanner;

public class FuncionarioVision {

    public static void exibir() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE FUNCIONÁRIOS =====");
            System.out.println("1 - Administrar Agrônomo");
            System.out.println("2 - Administrar Caseiro");
            System.out.println("3 - Administrar Veterinário");
            System.out.println("4 - Buscar Funcionários");
            System.out.println("5 - Deletar Funcionário");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Administrar Agrônomos...");
                    AgronomoVision.exibir();
                    break;
                case 2:
                    System.out.println("Administrar Caseiros...");
                    CaseiroVision.exibir();
                    break;
                case 3:
                    System.out.println("Administrar Veterinários...");
                    VeterinarioVision.exibir();
                    break;
                case 4:
                    System.out.println("Buscando Funcionários...");
                    // Chame aqui o método para buscar os funcionários
                    break;
                case 5:
                    System.out.println("Deletando Funcionário...");
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
