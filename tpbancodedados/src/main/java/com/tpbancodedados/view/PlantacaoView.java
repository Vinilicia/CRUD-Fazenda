package com.tpbancodedados.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.tpbancodedados.controller.PlantacaoController;
import com.tpbancodedados.model.Plantacao;

public class PlantacaoView {
    private static PlantacaoController plantacaoController = new PlantacaoController();
    private static Scanner scanner = new Scanner(System.in);

    public static void exibir() {
        int opcao;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE PLANTAÇÕES =====");
            System.out.println("1 - Cadastrar Plantação");
            System.out.println("2 - Listar Plantações");
            System.out.println("3 - Editar Plantação");
            System.out.println("4 - Deletar Plantação");
            System.out.println("5 - Filtrar Plantações por Data");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
            } else {
                opcao = -1;
            }
            scanner.nextLine();

            List<Plantacao> plantacoes = plantacaoController.listarPlantacoes();

            switch (opcao) {
                case 1:
                    System.err.println("PASSSOU CASE 1");
                    Plantacao plantacao = cadastrarPlantacao();
                    if (plantacaoController.inserirPlantacao(plantacao)) {
                        System.out.println("Plantaçao cadastrada com sucesso!");
                    } else {
                        System.out.println("Falha ao cadastrar plantação.");
                    }
                    break;
                case 2:
                    exibirPlantacoes(plantacoes);
                    break;
                case 3:
                    exibirPlantacoes(plantacoes);
                    editarPlantacao();
                    break;
                case 4:
                    exibirPlantacoes(plantacoes);
                    deletarPlantacao();
                    break;
                case 5:
                    filtrarPlantacoesPorData();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static Plantacao cadastrarPlantacao() {
        Plantacao plantacao = new Plantacao();

        System.out.print("Cultura: ");
        plantacao.setCultura(scanner.nextLine());

        System.out.print("Data do Plantio (yyyy-mm-dd): ");
        plantacao.setDataPlantio(RecebedorInput.receberInputValidado(LocalDate.class));

        System.out.print("ID do Agrônomo: ");
        plantacao.setIdAgronomo(RecebedorInput.receberInputValidado(Integer.class));

        return plantacao;
    }

    private static void editarPlantacao() {
        System.out.print("Digite o ID da Plantação a ser editada: ");
        int idPlantacao = RecebedorInput.receberInputValidado(Integer.class);

        Plantacao plantacao = new Plantacao();
        plantacao.setIdPlantacao(idPlantacao);

        System.out.print("Cultura: ");
        plantacao.setCultura(scanner.nextLine());

        System.out.print("Data do Plantio (yyyy-mm-dd): ");
        plantacao.setDataPlantio(RecebedorInput.receberInputValidado(LocalDate.class));

        System.out.print("ID do Agrônomo: ");
        plantacao.setIdAgronomo(RecebedorInput.receberInputValidado(Integer.class));

        if (plantacaoController.atualizarPlantacao(plantacao)) {
            System.out.println("Plantaçao atualizada com sucesso!");
        } else {
            System.out.println("Falha ao atualizar plantação.");
        }
    }

    private static void deletarPlantacao() {
        System.out.print("Digite o ID da Plantação a ser deletada: ");
        int idPlantacao = RecebedorInput.receberInputValidado(Integer.class);
        
        if (plantacaoController.deletarPlantacao(idPlantacao)) {
            System.out.println("Plantaçao deletada com sucesso!");
        } else {
            System.out.println("Falha ao deletar plantação.");
        }
    }

    private static void filtrarPlantacoesPorData() {
        System.out.print("Digite a data de referência (yyyy-mm-dd): ");
        LocalDate dataReferencia = RecebedorInput.receberInputValidado(LocalDate.class);  // Usando RecebedorInput para validar LocalDate
        System.out.print("Filtrar antes da data? (s/n): ");
        boolean antes = scanner.nextLine().equalsIgnoreCase("s");

        List<Plantacao> plantacoesFiltradas = plantacaoController.filtrarPorData(dataReferencia, antes);
        
        if (plantacoesFiltradas == null || plantacoesFiltradas.isEmpty()) {
            System.out.println("Nenhuma plantação encontrada.");
        } else {
            exibirPlantacoes(plantacoesFiltradas);
        }
    }

    private static void exibirPlantacoes(List<Plantacao> plantacoes) {
        if (plantacoes.isEmpty()) {
            System.out.println("Nenhuma plantação encontrada.");
            return;
        }
        for (Plantacao plantacao : plantacoes) {
            System.out.println(plantacao);
        }
    }
}
