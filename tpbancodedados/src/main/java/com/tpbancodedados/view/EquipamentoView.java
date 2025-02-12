package com.tpbancodedados.view;

import java.util.List;
import java.util.Scanner;

import com.tpbancodedados.model.Equipamento;

public class EquipamentoView {

    public static void exibir() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

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
