package br.edu.ifpr.armazen.view;

import br.edu.ifpr.armazen.controller.ProdutoController;

import java.util.Scanner;

import br.edu.ifpr.armazen.controller.MovimentacaoController;
import br.edu.ifpr.armazen.model.Produto;

public class Main {
    private static Scanner LER = new Scanner(System.in);

    public static void main(String[] args) {

        boolean onoff = true;

        while (onoff) {
            menu();

            int escolha = LER.nextInt();
            LER.nextLine();

            escolhas(escolha, onoff);
        }
    }

    private static void escolhas(int escolha, boolean onoff) {
        ProdutoController produtoController = new ProdutoController();
        MovimentacaoController movimentacaoController = new MovimentacaoController();

        switch (escolha) {
                case 1:
                    // Adicionar Produto
                    System.out.println("--- Adicionando um produto ---");
                    System.out.println(
                            "Para adicionar um produto informe \n - nome \n - descrição \n - preço \n - quantidade");
                    Produto produto = new Produto();
                    
                    produto.setnome(LER.nextLine());
                   // LER.nextLine();
                    produto.setDescricao(LER.nextLine());
                    //LER.nextLine();
                    produto.setPreco(LER.nextDouble());
                    LER.nextLine();
                    produto.setQuantidade(LER.nextInt());
                    LER.nextLine();
                    produtoController.cadastrarProduto(produto);
                    break;
                case 2:
                    // Registrar Movimentação
                    System.out.println("--- Registrando uma movimentação");
                    System.out.println("Para registrar uma movimentação digite \n - id do produto \n - quantidade a ser alterada \n - tipo de movimentação (saida ou entrada)");
                    movimentacaoController.registrarMovimentacao(LER.nextInt(), LER.nextInt(), LER.next());
                    break;
                case 3:
                    // Listar Produtos
                    System.out.println("--- Produtos ---");
                    produtoController.listarProdutos();
                    break;
                case 4:
                    // Listar Movimentações
                    System.out.println("--- Movimentações ---");
                    System.out.println("Para listar as movimentações digite o ID do produto");
                    movimentacaoController.listarMovimentacoes(LER.nextInt());
                    break;
                case 5:
                    // listar por periodo
                    System.out.println("--- Listar movimentações em um periodo ---");
                    System.out.println("Para listar em um periodo informe \n - id do pruduto \n - data inicial \n - data final \n (para as datas use o padrão AAAA-MM-DD HH:MM:SS");
                    movimentacaoController.listarMovimentacoesPorPeriodo(LER.nextInt(), LER.nextLine(), LER.nextLine());
                    break;
                case 6:
                    // Busca por ID
                    System.out.println("--- Buscar produto por ID ---");
                    System.out.println("Digite o id do produto:");
                    produtoController.buscarProdutoPorId(LER.nextInt());
                    break;
                case 0:
                    System.out.println("TCHAU...");
                    onoff = false;
                    break;
            }
    }

    public static void menu() {
        System.out.println("--- Menu ---");
        System.out.println("1 - Adicionar produto");
        System.out.println("2 - Registrar movimentação");
        System.out.println("3 - Listar produtos");
        System.out.println("4 - Listar Movimentação");
        System.out.println("5 - Listar por periodo");
        System.out.println("6 - Buscar produto por id");
    }
}
