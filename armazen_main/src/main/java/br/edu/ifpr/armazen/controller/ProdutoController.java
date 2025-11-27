package br.edu.ifpr.armazen.controller;

import br.edu.ifpr.armazen.model.Produto;
import br.edu.ifpr.armazen.model.dao.ProdutoDAO;

import java.util.List;

public class ProdutoController {
    private ProdutoDAO dao;

    public ProdutoController() {
        this.dao = new ProdutoDAO();
    }

    public void cadastrarProduto(Produto produto) {
        if (produto.getnome() == null || produto.getnome().isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
            return;
        }
        if (produto.getDescricao() == null || produto.getDescricao().isEmpty()) {
            System.out.println("Descrição não pode ser vazia.");
            return;
        }
        if (produto.getPreco() <= 0) {
            System.out.println("Preço deve ser maior que zero.");
            return;
        }
        if (produto.getQuantidade() < 0) {
            System.out.println("Quantidade não pode ser negativa.");
            return;
        }

        dao.adicionarProduto(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    public void atualizarEstoque(int produtoId, int quantidade) {
        if (quantidade == 0) {
            System.out.println("Quantidade não pode ser zero.");
            return;
        }

        Produto produto = dao.buscarProdutoPorId(produtoId);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        dao.atualizarEstoque(produtoId, quantidade);
        System.out.println("Estoque atualizado com sucesso!");
    }

    public void listarProdutos() {
        List<Produto> produtos = dao.listarProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Não há produtos cadastrados.");
            return;
        }

        System.out.println("Lista de Produtos:");
        for (Produto p : produtos) {
            System.out.println(p.getId() + " - " + p.getnome() + " - Quantidade: " + p.getQuantidade() + " - Preço: " + p.getPreco());
        }
    }

    public Produto buscarProdutoPorId(int produtoId) {
        Produto produto = dao.buscarProdutoPorId(produtoId);
        if (produto != null) {
            System.out.println("Produto encontrado: " + produto.getnome() + " - Quantidade: " + produto.getQuantidade() + " - Preço: " + produto.getPreco());
        } else {
            System.out.println("Produto não encontrado.");
        }
        return produto;
    }
}
