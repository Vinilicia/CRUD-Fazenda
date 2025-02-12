package com.tpbancodedados.view;

import java.util.List;
import java.util.Scanner;

import com.tpbancodedados.model.Equipamento;
import com.tpbancodedados.model.EstadoEquipamento;

import com.tpbancodedados.controller.EquipamentoController;

import com.tpbancodedados.view.RecebedorInput;

public class EquipamentoView {
    private static EquipamentoController equipamentoController = new EquipamentoController();

    private static Equipamento equipamento = new Equipamento();

    private static Scanner scanner = new Scanner(System.in);

    public static void exibir() {
        int opcao;
        int idEquipamento;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE EQUIPAMENTOS =====");
            System.out.println("1 - Cadastrar Equipamento");
            System.out.println("2 - Buscar Equipamentos");
            System.out.println("3 - Editar Equipamentos");
            System.out.println("4 - Escolher Caseiro");
            System.out.println("5 - Buscar Caseiros por Equipamento");
            System.out.println("6 - Deletar Equipamento");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            List<Equipamento> equipamentos = equipamentoController.listarTodosEquipamentos();

            if(scanner.hasNextInt()){
                opcao = scanner.nextInt();
            }
            else{
                opcao = -1;
            }
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Cadastrando Equipamento...");
                    equipamento = cadastrarEquipamento();
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
                    System.out.println("Digite o ID do Equipamento: ");
                    // Chame aqui o método para deletar o funcionário
                    break;
                case 6:
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

    private static Equipamento cadastrarEquipamento{
        String string;
        int opcao;

        System.out.print("Descição: ");
        string = scanner.nextLine();
        equipamento.setDescricao(string);
        System.out.print("Selecione o Estado");
        do{

        }while(opcao != 0)
        string = scanner.nextLine();

        return equipamento;
    }

    public static void exibirEquipamentos(List<Equipamento> equipamentos){
        if (equipamentos.isEmpty()){
            System.out.println("Nenhum Equipamento encontrado.");
            return;
        }
        for (Equipamento equipamento : equipamentos){
            System.out.println("ID: " + equipamento.getIdEquipamento() + ", Descrição: " + equipamento.getDescricao() +
                    ", Estado: " + equipamento.getEstado());
        }
    }
}
