package com.tpbancodedados.view;

import com.tpbancodedados.view.AgronomoView;
import com.tpbancodedados.view.CaseiroView;
import com.tpbancodedados.view.VeterinarioView;

import java.util.Scanner;

public class FuncionarioView {

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

            if(scanner.hasNextInt()){
                opcao = scanner.nextInt();
            }
            else{
                opcao = -1;
            }
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Administrar Agrônomos...");
                    AgronomoView.exibir();
                    break;
                case 2:
                    System.out.println("Administrar Caseiros...");
                    CaseiroView.exibir();
                    break;
                case 3:
                    System.out.println("Administrar Veterinários...");
                    VeterinarioView.exibir();
                    break;
                case 4:
                    System.out.println("Buscando Funcionários...");
                    buscarFuncionario();
                    opcao = 4;
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

    private static void buscarFuncionario(){
        Scanner scanner = new Scanner(System.in);
        int opcao;
        String string;
        int number;

         do{
            System.out.println("1 - Buscar por Nome");
            System.out.println("2 - Buscar por CPF");
            System.out.println("3 - Buscar por Salário igual a");
            System.out.println("4 - Buscar por Salário menor ou igual a");
            System.out.println("5 - Buscar por Salário maior ou igual a");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            if(scanner.hasNextInt()){
                opcao = scanner.nextInt();
            }
            else{
                opcao = -1;
            }
            scanner.nextLine();
            switch (opcao){
                case 1:
                    System.out.print("Digite o Nome: ");
                    string = scanner.nextLine();
                    System.out.println(string);
                    break;
                case 0:
                    System.out.print("Voltando...");
                    break;
            }
        } while (opcao != 0);
    }
}
