package com.tpbancodedados.view;

import java.util.Scanner;

public class CaseiroView {

    public static void exibir() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        String string;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE CASEIROS =====");
            System.out.println("1 - Cadastrar Caseiro");
            System.out.println("2 - Buscar Caseiros");
            System.out.println("3 - Editar Caseiro");
            System.out.println("4 - Escolher Equipamento");
            System.out.println("5 - Buscar Equipamentos por Caseiro");
            System.out.println("6 - Deletar Caseiro");
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
                    System.out.println("Cadastrar Caseiros...");
                    // Chame aqui o método que cadastra o Caseiro
                    break;
                case 2:
                    System.out.println("Buscando Caseiros...");
                    buscarCaseiros();
                    break;
                case 3:
                    System.out.println("Editando Caseiro...");
                    // Chame aqui o método para deletar o Caseiro
                    break;
                case 4:
                    System.out.println("Escolhendo Equipamento...");
                    // Chame aqui o método para deletar o Caseiro
                    break;
                case 5:
                    System.out.print("Digite o ID do Caseiro: ");
                    string = scanner.nextLine();
                    break;
                case 6:
                    System.out.println("Deletando Caseiro...");
                    // Chame aqui o método para deletar o Caseiro
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

    private static void buscarCaseiros(){
        Scanner scanner = new Scanner(System.in);
        int opcao;
        String string;

         do{
            System.out.println("\n===== MENU DE BUSCA DE CASEIROS =====");
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
                    break;
                case 2:
                    System.out.print("Digite o CPF: ");
                    string = scanner.nextLine();
                    break;
                case 3:
                    System.out.print("Buscar Salários iguais a: ");
                    string = scanner.nextLine();
                    break;
                case 4:
                    System.out.print("Buscar Salários com o valor de no máximo: ");
                    string = scanner.nextLine();
                    break;
                case 5:
                    System.out.print("Buscar Salários com o valor de no mínimo: ");
                    string = scanner.nextLine();
                    break;
                case 0:
                    System.out.print("Voltando...");
                    break;
            }
        } while (opcao != 0);
    }
}
