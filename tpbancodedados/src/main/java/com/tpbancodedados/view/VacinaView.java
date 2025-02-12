package com.tpbancodedados.view;

import java.util.Scanner;
import java.util.List;

import com.tpbancodedados.model.Vacina;

import com.tpbancodedados.controller.VacinaController;

public class VacinaView {
    private static VacinaController vacinaController = new VacinaController();
    
    private static Vacina vacina = new Vacina();

    private static Scanner scanner = new Scanner(System.in);

    public static void exibir() {
        int opcao;
        int idVacina;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE VACINAS =====");
            System.out.println("1 - Cadastrar Vacina");
            System.out.println("2 - Buscar Vacinas");
            System.out.println("3 - Editar Vacinas");
            System.out.println("4 - Deletar Vacina");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            List<Vacina> vacinas = vacinaController.listarTodasVacinas();

            if(scanner.hasNextInt()){
                opcao = scanner.nextInt();
            }
            else{
                opcao = -1;
            }
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Cadastrando Vacina...");
                    vacina = cadastrarVacina();
                    if(vacinaController.criarVacina(vacina)){
                        System.out.println("Vacina cadastrada com Sucesso");
                    }
                    else{
                        System.out.println("Falha ao cadastrar Vacina");
                    }
                    break;
                case 2:
                    System.out.println("Buscando Vacinas...");
                    exibirVacinas(vacinas);
                    break;
                case 3:
                    System.out.println("Editando Vacinas...");
                    exibirVacinas(vacinas);
                    vacina = editarVacina();
                    if(vacinaController.atualizarVacina(vacina)){
                        System.out.println("Vacina atualizada com Sucesso");
                    }
                    else{
                        System.out.println("Falha ao atualizar Vacina");
                    }
                    break;
                case 4:
                    System.out.println("Deletando Vacina...");
                    exibirVacinas(vacinas);
                    System.out.println("Digite o ID da Vacina");
                    idVacina = RecebedorInput.receberInputValidado(Integer.class);
                    if(vacinaController.deletarVacina(idVacina)){
                        System.out.println("Vacina deletada com Sucesso");
                    }
                    else{
                        System.out.println("Falha ao deletar o Vacina");
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

 private static Vacina cadastrarVacina(){
        String string;

        System.out.print("Descição: ");
        string = scanner.nextLine();
        vacina.setDescricao(string);

        return vacina;
    }

    private static Vacina editarVacina(){
        String string;
        int number;

        System.out.print("ID");
        number = RecebedorInput.receberInputValidado(Integer.class);
        vacina.setIdVacina(number);
        System.out.print("Descrição: ");
        string = scanner.nextLine();
        vacina.setDescricao(string);

        return vacina;

    }

    public static void exibirVacinas(List<Vacina> vacinas){
        if (vacinas.isEmpty()){
            System.out.println("Nenhuma Vacina encontrada.");
            return;
        }
        for (Vacina vacina : vacinas){
            System.out.println(vacina);
        }
    }
}
