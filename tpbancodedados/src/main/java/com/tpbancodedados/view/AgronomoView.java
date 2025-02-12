package com.tpbancodedados.view;

import java.util.Scanner;
import java.util.List;

import com.tpbancodedados.model.Agronomo;

import com.tpbancodedados.controller.AgronomoController;

import com.tpbancodedados.view.RecebedorInput;

public class AgronomoView {
    private static AgronomoController agronomoController = new AgronomoController();

    private static Agronomo agronomo = new Agronomo();    

    private static Scanner scanner = new Scanner(System.in);

    public static void exibir() {
        int opcao;
        int idAgronomo;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE AGRÔNOMOS =====");
            System.out.println("1 - Cadastrar Agrônomo");
            System.out.println("2 - Buscar Agrônomos");
            System.out.println("3 - Editar Agrônomo");
            System.out.println("4 - Deletar Agrônomo");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            List<Agronomo> agronomos = agronomoController.listarAgronomos();
            
            if(scanner.hasNextInt()){
                opcao = scanner.nextInt();
            }
            else{
                opcao = -1;
            }
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Cadastrar Agrônomo...");
                    agronomo = cadastrarAgronomo();
                    if(agronomoController.inserirAgronomo(agronomo)){
                        System.out.println("Agrônomo cadastrado com Sucesso");
                    }
                    else{
                        System.out.println("Falha ao cadastrar Agrônomo");
                    }
                    break;
                case 2:
                    System.out.println("Buscando Agrônomos...");
                    exibirAgronomos(agronomos);
                    buscarAgronomos();
                    break;
                case 3:
                    System.out.println("Editando Agrônomo...");
                    exibirAgronomos(agronomos);
                    agronomo = editarAgronomo();
                    if(agronomoController.atualizarAgronomo(agronomo)){
                        System.out.println("Agronomo editado com Sucesso");
                    }
                    else{
                        System.out.println("Falha ao editar Agronomo");
                    }
                    break;
                case 4:
                    System.out.println("Deletando Agrônomo...");
                    exibirAgronomos(agronomos);
                    System.out.print("Digite o ID do Agrônomo");
                    idAgronomo = RecebedorInput.receberInputValidado(Integer.class);
                    if(agronomoController.deletarAgronomo(idAgronomo)){
                        System.out.println("Agrônomo deletado com Sucesso");
                    }
                    else{
                        System.out.println("Falha ao deletar o Agrônomo");
                    }
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
        int opcao;
        double decimal;
        String string;
        List<Agronomo> agronomos;

         do{
            System.out.println("\n===== MENU DE BUSCA DE AGRÔNOMOS =====");
            System.out.println("1 - Buscar por Salário menor ou igual a");
            System.out.println("2 - Buscar por Salário maior ou igual a");
            System.out.println("3 - Buscar por área de especialização");
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
                    System.out.print("Buscar Salários com o valor de no máximo");
                    decimal = RecebedorInput.receberInputValidado(Double.class);
                    agronomos = agronomoController.filtrarPorSalario(decimal, false);
                    exibirAgronomos(agronomos);
                    break;
                case 2:
                    System.out.print("Buscar Salários com o valor de no mínimo: ");
                    decimal = RecebedorInput.receberInputValidado(Double.class);
                    agronomos = agronomoController.filtrarPorSalario(decimal, true);
                    exibirAgronomos(agronomos);
                    break;
                case 3:
                    System.out.print("Digite a área de especialização: ");
                    string = scanner.nextLine();
                    agronomos = agronomoController.buscAgronomosPorAreaEspecializacao(string);
                    exibirAgronomos(agronomos);
                    break;
                case 0:
                    System.out.print("Voltando...");
                    break;
            }
        } while (opcao != 0);
    }

    private static Agronomo cadastrarAgronomo(){
        String string;
        double decimal;

        System.out.print("Nome: ");
        string = scanner.nextLine();
        agronomo.setNome(string);
        System.out.print("CPF: ");
        string = scanner.nextLine();
        agronomo.setCpf(string);        
        System.out.print("Salário");
        decimal = RecebedorInput.receberInputValidado(Double.class);
        agronomo.setSalario(decimal);
        System.out.print("Área de especialização: ");
        string = scanner.nextLine();
        agronomo.setAreaEspecializacao(string);

        return agronomo;
    }

    private static Agronomo editarAgronomo(){
        String string;
        int number;
        double decimal; 

        System.out.print("ID");
        number = RecebedorInput.receberInputValidado(Integer.class);
        agronomo.setId(number);
        System.out.print("Nome: ");
        string = scanner.nextLine();
        agronomo.setNome(string);
        System.out.print("CPF: ");
        string = scanner.nextLine();
        agronomo.setCpf(string);
        System.out.print("Salário");
        decimal = RecebedorInput.receberInputValidado(Double.class);
        agronomo.setSalario(decimal);
        System.out.print("Área de Especialização: ");
        string = scanner.nextLine();
        agronomo.setAreaEspecializacao(string);

        return agronomo;
    }

    public static void exibirAgronomos(List<Agronomo> agronomos){
        if (agronomos.isEmpty()){
            System.out.println("Nenhum funcionário encontrado.");
            return;
        }
        for (Agronomo agronomo : agronomos){
            System.out.println("ID: " + agronomo.getId() + ", Nome: " + agronomo.getNome() +
                    ", CPF: " + agronomo.getCpf() + ", Salário: " + agronomo.getSalario() + ", Área de Especialização: " + agronomo.getAreaEspecializacao());
        }
    }
}
