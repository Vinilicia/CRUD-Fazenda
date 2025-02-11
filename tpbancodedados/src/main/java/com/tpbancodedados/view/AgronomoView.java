package com.tpbancodedados.view;

import java.util.Scanner;

public class AgronomoView {

    public static void exibir() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        String string;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE AGRÔNOMOS =====");
            System.out.println("1 - Cadastrar Agrônomo");
            System.out.println("2 - Buscar Agrônomos");
            System.out.println("3 - Editar Agrônomo");
            System.out.println("4 - Escolher Plantação");
            System.out.println("5 - Buscar Plantações por Agrônomo");
            System.out.println("6 - Deletar Agrônomo");
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
                    buscarAgronomos();
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
                    System.out.print("Digite o ID do Agrônomo: ");
                    string = scanner.nextLine();
                    break;
                case 6:
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

    private static void buscarAgronomos(){
        Scanner scanner = new Scanner(System.in);
        int opcao;
        String string;

         do{
            System.out.println("\n===== MENU DE BUSCA DE AGRÔNOMOS =====");
            System.out.println("1 - Buscar por Nome");
            System.out.println("2 - Buscar por CPF");
            System.out.println("3 - Buscar por Salário igual a");
            System.out.println("4 - Buscar por Salário menor ou igual a");
            System.out.println("5 - Buscar por Salário maior ou igual a");
            System.out.println("6 - Buscar por área de especialização");
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
                case 6:
                    System.out.print("Digite a área de especialização: ");
                    string = scanner.nextLine();
                    break;
                case 0:
                    System.out.print("Voltando...");
                    break;
            }
        } while (opcao != 0);
    }
}
