package com.tpbancodedados.view;

import java.util.List;
import java.util.Scanner;

import com.tpbancodedados.model.Funcionario; 

import com.tpbancodedados.controller.FuncionarioController;

import com.tpbancodedados.view.AgronomoView;
import com.tpbancodedados.view.CaseiroView;
import com.tpbancodedados.view.VeterinarioView;
import com.tpbancodedados.view.RecebedorInput;

public class FuncionarioView {
    private static FuncionarioController funcionarioController = new FuncionarioController();
    private static Scanner scanner = new Scanner(System.in);

    public static void exibir() {
        int opcao;
        int idFuncionario;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE FUNCIONÁRIOS =====");
            System.out.println("1 - Administrar Agrônomo");
            System.out.println("2 - Administrar Caseiro");
            System.out.println("3 - Administrar Veterinário");
            System.out.println("4 - Listar Funcionários");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            if(scanner.hasNextInt()){
                opcao = scanner.nextInt();
            }
            else{
                opcao = -1;
            }
            scanner.nextLine();

            List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();

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
                    System.out.println("Lista de Funcionários");
                    exibirFuncionarios(funcionarios);
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

    private static void exibirFuncionarios(List<Funcionario> funcionarios){
        if (funcionarios.isEmpty()){
            System.out.println("Nenhum funcionário encontrado.");
            return;
        }
        for (Funcionario funcionario : funcionarios){
            System.out.println("ID: " + funcionario.getId() + ", Nome: " + funcionario.getNome() +
                    ", CPF: " + funcionario.getCpf() + ", Salário: " + funcionario.getSalario());
        }
    }
}
