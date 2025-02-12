package com.tpbancodedados.view;

import java.util.Scanner;
import java.util.List;

import com.tpbancodedados.model.Veterinario;
import com.tpbancodedados.model.Animal;

import com.tpbancodedados.controller.VeterinarioController;
import com.tpbancodedados.controller.AnimalController;

import com.tpbancodedados.view.RecebedorInput;
import com.tpbancodedados.view.AnimalView;

public class VeterinarioView {
    private static VeterinarioController veterinarioController = new VeterinarioController();
    private static AnimalController animalController = new AnimalController();

    private static Veterinario veterinario = new Veterinario();

    private static Scanner scanner = new Scanner(System.in);

    public static void exibir() {
        int opcao;
        int idAnimal;
        int idVeterinario;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE VETERINÁRIOS =====");
            System.out.println("1 - Cadastrar Veterinário");
            System.out.println("2 - Buscar Veterinários");
            System.out.println("3 - Editar Veterinário");
            System.out.println("4 - Escolher Animal");
            System.out.println("5 - Buscar Animais por Vetérinário");
            System.out.println("6 - Deletar Animais do Vetérinário");
            System.out.println("7 - Deletar Veterinário");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            
            List<Veterinario> veterinarios = veterinarioController.listarVeterinarios();
            List<Animal> animais = animalController.listarAnimais();

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
                    veterinario = cadastrarVeterinario();
                    if(veterinarioController.inserirVeterinario(veterinario)){
                        System.out.println("Veterinário cadastrado com Sucesso");
                    }
                    else{
                        System.out.println("Falha ao cadastrar Veterinário");
                    }
                    break;
                case 2:
                    System.out.println("Buscando Veterinários...");
                    exibirVeterinarios(veterinarios);
                    buscarVeterinarios();
                    break;
                case 3:
                    System.out.println("Editando Veterinário...");
                    exibirVeterinarios(veterinarios);
                    veterinario = editarVeterinario();
                    if(veterinarioController.atualizarVeterinario(veterinario)){
                        System.out.println("Veterinário editado com Sucesso");
                    }
                    else{
                        System.out.println("Falha ao editar Veterinário");
                    }
                    break;
                case 4:
                    exibirVeterinarios(veterinarios);
                    System.out.print("Digite o ID do Veterinário");
                    idVeterinario = RecebedorInput.receberInputValidado(Integer.class);
                    System.out.println("Escolhendo Animal...");
                    AnimalView.exibirAnimais(animais);
                    System.out.print("Digite o ID do animal");
                    idAnimal = RecebedorInput.receberInputValidado(Integer.class);
                    if(veterinarioController.associarAnimal(idVeterinario, idAnimal)){
                        System.out.println("Veterinário e Animal Associado com Sucesso");
                    }
                    else{
                        System.out.println("Falha ao associar Veterinário e Animal");
                    }
                    break;
                case 5:
                    exibirVeterinarios(veterinarios);
                    System.out.println("Digite o ID do Veterinário");
                    idVeterinario = RecebedorInput.receberInputValidado(Integer.class);
                    animais = veterinarioController.listarAnimaisPorVeterinario(idVeterinario);
                    AnimalView.exibirAnimais(animais);
                    break;
                case 6:
                    System.out.println("Deletando Animal do Veterinário...");
                    exibirVeterinarios(veterinarios);
                    System.out.println("Digite o ID do Veterinário");
                    idVeterinario = RecebedorInput.receberInputValidado(Integer.class);
                    System.out.println("Escolhendo Animal...");
                    AnimalView.exibirAnimais(animais);
                    System.out.print("Digite o ID do animal");
                    idAnimal = RecebedorInput.receberInputValidado(Integer.class);
                    if(veterinarioController.deletarVeterinarioAnimal(idVeterinario, idAnimal)){
                        System.out.println("Animal deletado com Sucesso do Veterinário");
                    }
                    else{
                        System.out.println("Falha ao deletar Animal do Veterinário");
                    }
                    break;
                case 7:
                    System.out.println("Deletando Veterinário...");
                    exibirVeterinarios(veterinarios);
                    System.out.println("Digite o ID do Veterinário");
                    idVeterinario = RecebedorInput.receberInputValidado(Integer.class);
                    if(veterinarioController.deletarVeterinario(idVeterinario)){
                        System.out.println("Veterinário deletado com Sucesso");
                    }
                    else{
                        System.out.println("Falha ao deletar o Veterinário");
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

    private static Veterinario cadastrarVeterinario(){
        String string;
        double decimal;

        System.out.print("Nome: ");
        string = scanner.nextLine();
        veterinario.setNome(string);
        System.out.print("CPF: ");
        string = scanner.nextLine();
        veterinario.setCpf(string);
        System.out.print("Salário");
        decimal = RecebedorInput.receberInputValidado(Double.class);
        veterinario.setSalario(decimal);
        System.out.print("Registro CRMV: ");
        string = scanner.nextLine();
        veterinario.setRegistroCrmv(string);

        return veterinario;
    }

    private static void buscarVeterinarios(){
        int opcao;
        String string;
        double decimal;
        List<Veterinario> veterinarios;
        Veterinario veterinario;

         do{
            System.out.println("\n===== MENU DE BUSCA DE VETERINÁRIOS =====");
            System.out.println("1 - Buscar por Salário menor ou igual a");
            System.out.println("2 - Buscar por Salário maior ou igual a");
            System.out.println("3 - Buscar por Registro CRMV");
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
                    veterinarios = veterinarioController.filtrarPorSalario(decimal, false);
                    exibirVeterinarios(veterinarios);
                    break;
                case 2:
                    System.out.print("Buscar Salários com o valor de no mínimo");
                    decimal = RecebedorInput.receberInputValidado(Double.class);
                    veterinarios = veterinarioController.filtrarPorSalario(decimal, true);
                    exibirVeterinarios(veterinarios);
                    break;
                case 3:
                    System.out.print("Digite o CRMV: ");
                    string = scanner.nextLine();
                    veterinario = veterinarioController.buscarVeterinarioPorCRMV(string);
                    exibirVeterinario(veterinario);
                    break;
                case 0:
                    System.out.print("Voltando...");
                    break;
            }
        } while (opcao != 0);
    }

    private static Veterinario editarVeterinario(){
        String string;
        int number;
        double decimal; 

        System.out.print("ID");
        number = RecebedorInput.receberInputValidado(Integer.class);
        veterinario.setId(number);
        System.out.print("Nome: ");
        string = scanner.nextLine();
        veterinario.setNome(string);
        System.out.print("CPF: ");
        string = scanner.nextLine();
        veterinario.setCpf(string);
        System.out.print("Salário");
        decimal = RecebedorInput.receberInputValidado(Double.class);
        veterinario.setSalario(decimal);
        System.out.print("Registro CRMV: ");
        string = scanner.nextLine();
        veterinario.setRegistroCrmv(string);

        return veterinario;
    }

    public static void exibirVeterinarios(List<Veterinario> veterinarios){
        if (veterinarios.isEmpty()){
            System.out.println("Nenhum funcionário encontrado.");
            return;
        }
        for (Veterinario veterinario : veterinarios){
            System.out.println("ID: " + veterinario.getId() + ", Nome: " + veterinario.getNome() +
                    ", CPF: " + veterinario.getCpf() + ", Salário: " + veterinario.getSalario() + ", Registro CRMV: " + veterinario.getRegistroCrmv());
        }
    }

    private static void exibirVeterinario(Veterinario veterinario){
        if (veterinario == null){
            System.out.println("Nenhum funcionário encontrado.");
            return;
        }
        System.out.println("ID: " + veterinario.getId() + ", Nome: " + veterinario.getNome() +
                    ", CPF: " + veterinario.getCpf() + ", Salário: " + veterinario.getSalario() + ", Registro CRMV: " + veterinario.getRegistroCrmv());
    }
}
