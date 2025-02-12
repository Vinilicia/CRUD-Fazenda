package com.tpbancodedados.view;

import java.util.List;
import java.util.Scanner;

import com.tpbancodedados.model.Equipamento;
import com.tpbancodedados.model.EstadoEquipamento;

import com.tpbancodedados.controller.EquipamentoController;


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
            System.out.println("4 - Deletar Equipamento");
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
                    if(equipamentoController.adicionarEquipamento(equipamento)){
                        System.out.println("Equipamento cadastrado com Sucesso");
                    }
                    else{
                        System.out.println("Falha ao cadastrar Equipamento");
                    }
                    break;
                case 2:
                    System.out.println("Buscando Equipamentos...");
                    exibirEquipamentos(equipamentos);
                    buscarEquipamentos();
                    break;
                case 3:
                    System.out.println("Editando Equipamentos...");
                    exibirEquipamentos(equipamentos);
                    equipamento = editarEquipamento();
                    if(equipamentoController.atualizarEquipamento(equipamento)){
                        System.out.println("Equipamento atualizado com Sucesso");
                    }
                    else{
                        System.out.println("Falha ao atualizar Equipamento");
                    }
                    break;
                case 4:
                    System.out.println("Deletando Equipamento...");
                    exibirEquipamentos(equipamentos);
                    System.out.println("Digite o ID do Equipamento");
                    idEquipamento = RecebedorInput.receberInputValidado(Integer.class);
                    if(equipamentoController.removerEquipamento(idEquipamento)){
                        System.out.println("Equipamento deletado com Sucesso");
                    }
                    else{
                        System.out.println("Falha ao deletar o Equipamento");
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

    private static Equipamento cadastrarEquipamento(){
        String string;
        int opcao;

        System.out.print("Descição: ");
        string = scanner.nextLine();
        equipamento.setDescricao(string);
        System.out.println("Selecione o Estado");
        do{
            System.out.println("1 - Disponível");
            System.out.println("2 - Quebrado");
            System.out.println("3 - Em manutenção");

            if(scanner.hasNextInt()){
                opcao = scanner.nextInt();
            }
            else{
                opcao = 0;
            }
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    equipamento.setEstado(EstadoEquipamento.DISPONIVEL);
                    break;
                case 2:
                    equipamento.setEstado(EstadoEquipamento.QUEBRADO);
                    break;
                case 3:
                    equipamento.setEstado(EstadoEquipamento.EM_MANUTENCAO);
                    break;
                default:
                    System.out.println("Escolha um estado válido");
                    opcao = 0;
                    break;
            }
        }while(opcao == 0);

        return equipamento;
    }

    private static void buscarEquipamentos(){
        int opcao;
        List<Equipamento> equipamentos;

        System.out.println("Buscar Equipamento pelo Estado");
        do{
            System.out.println("1 - Disponível");
            System.out.println("2 - Quebrado");
            System.out.println("3 - Em manutenção");

            if(scanner.hasNextInt()){
                opcao = scanner.nextInt();
            }
            else{
                opcao = 0;
            }
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    equipamentos = equipamentoController.buscarEquipamentosPorEstado(EstadoEquipamento.DISPONIVEL);
                    exibirEquipamentos(equipamentos);
                    return;
                case 2:
                    equipamentos = equipamentoController.buscarEquipamentosPorEstado(EstadoEquipamento.QUEBRADO);
                    exibirEquipamentos(equipamentos);
                    return;
                case 3:
                    equipamentos = equipamentoController.buscarEquipamentosPorEstado(EstadoEquipamento.EM_MANUTENCAO);
                    exibirEquipamentos(equipamentos);
                    return;
                default:
                    System.out.println("Escolha um estado válido");
                    opcao = 0;
                    break;
            }
        }while(opcao == 0);
    }

    private static Equipamento editarEquipamento(){
        String string;
        int number;
        int opcao;

        System.out.print("ID");
        number = RecebedorInput.receberInputValidado(Integer.class);
        equipamento.setIdEquipamento(number);
        System.out.print("Descrição: ");
        string = scanner.nextLine();
        equipamento.setDescricao(string);
        System.out.println("Selecione o Estado");
        do{
            System.out.println("1 - Disponível");
            System.out.println("2 - Quebrado");
            System.out.println("3 - Em manutenção");

            if(scanner.hasNextInt()){
                opcao = scanner.nextInt();
            }
            else{
                opcao = 0;
            }
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    equipamento.setEstado(EstadoEquipamento.DISPONIVEL);
                    break;
                case 2:
                    equipamento.setEstado(EstadoEquipamento.QUEBRADO);
                    break;
                case 3:
                    equipamento.setEstado(EstadoEquipamento.EM_MANUTENCAO);
                    break;
                default:
                    System.out.println("Escolha um estado válido");
                    opcao = 0;
                    break;
            }
        }while(opcao == 0);

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
