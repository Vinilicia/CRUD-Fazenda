package com.tpbancodedados.view;

import java.util.Scanner;
import java.util.List;

import com.tpbancodedados.model.Caseiro;
import com.tpbancodedados.model.Equipamento;
import com.tpbancodedados.controller.CaseiroController;
import com.tpbancodedados.controller.EquipamentoController;

import com.tpbancodedados.view.RecebedorInput;
import com.tpbancodedados.view.EquipamentoView;

public class CaseiroView {
    private static CaseiroController caseiroController = new CaseiroController();
    private static EquipamentoController equipamentoController = new EquipamentoController();

    private static Caseiro caseiro = new Caseiro();

    private static Scanner scanner = new Scanner(System.in);

    public static void exibir() {
        int opcao;
        int idCaseiro;
        int idEquipamento;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE CASEIROS =====");
            System.out.println("1 - Cadastrar Caseiro");
            System.out.println("2 - Buscar Caseiros");
            System.out.println("3 - Editar Caseiro");
            System.out.println("4 - Escolher Equipamento");
            System.out.println("5 - Buscar Equipamentos por Caseiro");
            System.out.println("6 - Deletar Equipamentos do Caseiro");
            System.out.println("7 - Deletar Caseiro");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            List<Caseiro> caseiros = caseiroController.listarCaseiros();
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
                    System.out.println("Cadastrar Caseiros...");
                    caseiro = cadastrarCaseiro();
                    if(caseiroController.inserirCaseiro(caseiro)){
                        System.out.println("Caseiro cadastrado com Sucesso");
                    }
                    else{
                        System.out.println("Falha ao cadastrar Caseiro");
                    }
                    break;
                case 2:
                    System.out.println("Buscando Caseiros...");
                    buscarCaseiros();
                    break;
                case 3:
                    System.out.println("Editando Caseiro...");
                    exibirCaseiros(caseiros);
                    caseiro = editarCaseiro();
                    break;
                case 4:
                    exibirCaseiros(caseiros);
                    System.out.print("Digite o ID do Caseiro");
                    idCaseiro = RecebedorInput.receberInputValidado(Integer.class);
                    System.out.println("Escolhendo Equipamento...");
                    EquipamentoView.exibirEquipamentos(equipamentos);
                    System.out.print("Digite o ID do equipamento");
                    idEquipamento = RecebedorInput.receberInputValidado(Integer.class);
                    if(caseiroController.associarEquipamento(idCaseiro, idEquipamento)){
                        System.out.println("Caseiro e Equipamento Associado com Sucesso");
                    }
                    else{
                        System.out.println("Falha ao associar Caseiro e Equipamento");
                    }
                    break;
                case 5:               
                    exibirCaseiros(caseiros);     
                    System.out.print("Digite o ID do Caseiro");
                    idCaseiro = RecebedorInput.receberInputValidado(Integer.class);
                    equipamentos = caseiroController.listarEquipamentosDoCaseiro(idCaseiro);
                    EquipamentoView.exibirEquipamentos(equipamentos);
                    break;
                case 6:
                    exibirCaseiros(caseiros);
                    System.out.print("Digite o ID do Caseiro");
                    idCaseiro = RecebedorInput.receberInputValidado(Integer.class);
                    System.out.println("Escolhendo Equipamento...");
                    EquipamentoView.exibirEquipamentos(equipamentos);
                    System.out.print("Digite o ID do equipamento");
                    idEquipamento = RecebedorInput.receberInputValidado(Integer.class);
                    if(caseiroController.deletarCaseiroEquipamento(idCaseiro, idEquipamento)){
                        System.out.println("Caseiro e Equipamento deletado com Sucesso");
                    }
                    else{
                        System.out.println("Falha ao deletar Caseiro e Equipamento");
                    }
                    break;
                case 7:
                    System.out.println("Deletando Caseiro...");
                    exibirCaseiros(caseiros);
                    System.out.print("Digite o ID do Caseiro");
                    idCaseiro = RecebedorInput.receberInputValidado(Integer.class);
                    if(caseiroController.deletarCaseiro(idCaseiro)){
                        System.out.println("Caseiro deletado com Sucesso");
                    }
                    else{
                        System.out.println("Falha ao deletar o Caseiro");
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

    private static Caseiro cadastrarCaseiro(){
        String string;
        double decimal;

        System.out.print("Nome: ");
        string = scanner.nextLine();
        caseiro.setNome(string);
        System.out.print("CPF: ");
        string = scanner.nextLine();
        caseiro.setCpf(string);        
        System.out.print("Salário");
        decimal = RecebedorInput.receberInputValidado(Double.class);
        caseiro.setSalario(decimal);

        return caseiro;
    }

    private static void buscarCaseiros(){
        int opcao;
        List<Caseiro> caseiros;
        double decimal;

         do{
            System.out.println("\n===== MENU DE BUSCA DE CASEIROS =====");
            System.out.println("1 - Buscar por Salário menor ou igual a");
            System.out.println("2 - Buscar por Salário maior ou igual a");
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
                    caseiros = caseiroController.filtrarPorSalario(decimal, false);
                    exibirCaseiros(caseiros);
                    break;
                case 2:
                    System.out.print("Buscar Salários com o valor de no mínimo");
                    decimal = RecebedorInput.receberInputValidado(Double.class);
                    caseiros = caseiroController.filtrarPorSalario(decimal, true);
                    exibirCaseiros(caseiros);
                    break;
                case 0:
                    System.out.print("Voltando...");
                    break;
            }
        } while (opcao != 0);
    }

    private static Caseiro editarCaseiro(){
        String string;
        int number;
        double decimal; 

        System.out.print("ID");
        number = RecebedorInput.receberInputValidado(Integer.class);
        caseiro.setId(number);
        System.out.print("Nome: ");
        string = scanner.nextLine();
        caseiro.setNome(string);
        System.out.print("CPF: ");
        string = scanner.nextLine();
        caseiro.setCpf(string);
        System.out.print("Salário");
        decimal = RecebedorInput.receberInputValidado(Double.class);
        caseiro.setSalario(decimal);

        return caseiro;
    } 

    public static void exibirCaseiros(List<Caseiro> caseiros){
        if (caseiros.isEmpty()){
            System.out.println("Nenhum funcionário encontrado.");
            return;
        }
        for (Caseiro caseiro : caseiros){
            System.out.println("ID: " + caseiro.getId() + ", Nome: " + caseiro.getNome() +
                    ", CPF: " + caseiro.getCpf() + ", Salário: " + caseiro.getSalario());
        }
    }
}
