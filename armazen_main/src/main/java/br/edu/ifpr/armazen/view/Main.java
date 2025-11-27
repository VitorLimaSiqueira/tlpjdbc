package br.edu.ifpr.armazen.view;

import br.edu.ifpr.armazen.controller.ProdutoController;
import br.edu.ifpr.armazen.controller.MovimentacaoController;
import br.edu.ifpr.armazen.model.Produto;

public class Main {
    public static void main(String[] args) {
        ProdutoController produtoController = new ProdutoController();
        MovimentacaoController movimentacaoController = new MovimentacaoController();

        // Cadastrar Produto
        Produto produto = new Produto();
        produto.setnome("Produto A");
        produto.setDescricao("Descrição do Produto A");
        produto.setPreco(50.00);
        produto.setQuantidade(100);

        produtoController.cadastrarProduto(produto);  // Cadastra o produto no banco

        // Atualizar estoque (Entrada de 50 unidades)
        produtoController.atualizarEstoque(1, 50);  // Produto ID 1 recebe 50 unidades a mais

        // Registrar uma movimentação de saída
        movimentacaoController.registrarMovimentacao(1, 30, "saida");  // Produto ID 1 tem uma saída de 30 unidades

        // Listar todos os produtos cadastrados
        produtoController.listarProdutos();

        // Listar todas as movimentações do Produto com ID 1
        movimentacaoController.listarMovimentacoes(1);

        // Listar movimentações de um produto entre um período específico
        String dataInicio = "2025-01-01 00:00:00";
        String dataFim = "2025-12-31 23:59:59";
        movimentacaoController.listarMovimentacoesPorPeriodo(1, dataInicio, dataFim);  // Filtra as movimentações no ano de 2025

        // Buscar um produto específico pelo ID
        produtoController.buscarProdutoPorId(1);  // Buscar o Produto ID 1
    }
}
