package com.tpbancodedados.view;

import java.util.List;
import java.util.Scanner;

import com.tpbancodedados.controller.ProdutoController;
import com.tpbancodedados.model.Produto;

public class ProdutoView {
    private static ProdutoController produtoController = new ProdutoController();
    private static Scanner scanner = new Scanner(System.in);

    public static void exibir() {
        int opcao;

        do {
            System.out.println("\n===== MENU DE ADMINISTRAÇÃO DE PRODUTOS =====");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Editar Produto");
            System.out.println("4 - Deletar Produto");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
            } else {
                opcao = -1;
            }
            scanner.nextLine();

            List<Produto> produtos = produtoController.listarProdutos();

            switch (opcao) {
                case 1:
                    Produto produto = cadastrarProduto();
                    if (produtoController.inserirProduto(produto)) {
                        System.out.println("Produto cadastrado com sucesso!");
                    } else {
                        System.out.println("Falha ao cadastrar produto.");
                    }
                    break;
                case 2:
                    exibirProdutos(produtos);
                    break;
                case 3:
                    exibirProdutos(produtos);
                    editarProduto();
                    break;
                case 4:
                    exibirProdutos(produtos);
                    deletarProduto();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static Produto cadastrarProduto() {
        Produto produto = new Produto();

        System.out.print("Tipo: ");
        produto.setTipo(scanner.nextLine());
        System.out.print("Quantidade: ");
        produto.setQuantidade(RecebedorInput.receberInputValidado(Double.class));
        System.out.print("Unidade: ");
        produto.setUnidade(scanner.nextLine());
        System.out.print("ID da Plantação: ");
        produto.setIdPlantacao(RecebedorInput.receberInputValidado(Integer.class));
        
        return produto;
    }

    private static void editarProduto() {
        System.out.print("Digite o ID do Produto a ser editado: ");
        int idProduto = RecebedorInput.receberInputValidado(Integer.class);

        Produto produto = new Produto();
        produto.setIdProduto(idProduto);

        System.out.print("Tipo: ");
        produto.setTipo(scanner.nextLine());
        System.out.print("Quantidade: ");
        produto.setQuantidade(RecebedorInput.receberInputValidado(Double.class));
        System.out.print("Unidade: ");
        produto.setUnidade(scanner.nextLine());
        System.out.print("ID da Plantação: ");
        produto.setIdPlantacao(RecebedorInput.receberInputValidado(Integer.class));

        if (produtoController.atualizarProduto(produto)) {
            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Falha ao atualizar produto.");
        }
    }

    private static void deletarProduto() {
        System.out.print("Digite o ID do Produto a ser deletado: ");
        int idProduto = RecebedorInput.receberInputValidado(Integer.class);
        
        if (produtoController.deletarProduto(idProduto)) {
            System.out.println("Produto deletado com sucesso!");
        } else {
            System.out.println("Falha ao deletar produto.");
        }
    }

    private static void exibirProdutos(List<Produto> produtos) {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
            return;
        }
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }
}
