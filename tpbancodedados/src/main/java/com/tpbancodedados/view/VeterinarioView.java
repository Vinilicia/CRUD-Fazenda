package com.tpbancodedados.view;

import java.util.Scanner;
import java.util.List;

import com.tpbancodedados.model.Veterinario;

import com.tpbancodedados.controller.VeterinarioController;

public class VeterinarioView {
    private static VeterinarioController veterinarioController = new VeterinarioController();
    private static Veterinario veterinario = new Veterinario();
    private static Scanner scanner = new Scanner(System.in);

    public static void exibir() {
        int opcao;
        String string;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE VETERINÁRIOS =====");
            System.out.println("1 - Cadastrar Veterinário");
            System.out.println("2 - Buscar Veterinários");
            System.out.println("3 - Editar Veterinário");
            System.out.println("4 - Escolher Animal");
            System.out.println("5 - Buscar Animais por Vetérinário");
            System.out.println("6 - Deletar Veterinário");
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
                    System.out.println("Cadastrando Veterinário...");
                    System.out.print("Nome: ");
                    string = scanner.nextLine();
                    veterinario.setNome(string);
                    System.out.print("CPF: ");
                    string = scanner.nextLine();
                    veterinario.setCpf(string);
                    System.out.print("Salário: ");
                    string = scanner.nextLine();
                    veterinario.setSalario(Double.parseDouble(string));
                    System.out.print("Registro CRMV: ");
                    string = scanner.nextLine();
                    veterinario.setRegistroCrmv(string);
                    veterinarioController.inserirVeterinario(veterinario);
                    break;
                case 2:
                    System.out.println("Buscando Veterinários...");
                    // Chame aqui o método para buscar os Veterinários
                    break;
                case 3:
                    System.out.println("Editando Veterinário...");
                    // Chame aqui o método para deletar o Veterinário
                    break;
                case 4:
                    System.out.println("Escolhendo Animal...");
                    // Chame aqui o método para deletar o Veterinário
                    break;
                case 5:
                    System.out.println("Digite o ID do Veterinário: ");
                    // Chame aqui o método para deletar o Veterinário
                    break;
                case 6:
                    System.out.println("Deletando Veterinário...");
                    // Chame aqui o método para deletar o Veterinário
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
