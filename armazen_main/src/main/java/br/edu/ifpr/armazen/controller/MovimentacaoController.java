package br.edu.ifpr.armazen.controller;

import br.edu.ifpr.armazen.model.Movimentacao;
import br.edu.ifpr.armazen.model.dao.MovimentacaoDAO;

import java.text.SimpleDateFormat;
import java.util.List;

public class MovimentacaoController {
    private MovimentacaoDAO dao;

    public MovimentacaoController() {
        this.dao = new MovimentacaoDAO();
    }

    public void registrarMovimentacao(int produtoId, int quantidade, String tipo) {
        if (quantidade <= 0) {
            System.out.println("A quantidade deve ser maior que zero.");
            return;
        }

        if (!tipo.equals("entrada") && !tipo.equals("saida")) {
            System.out.println("Tipo de movimentação inválido. Deve ser 'entrada' ou 'saida'.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataAtual = sdf.format(new java.util.Date());

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setProdutoId(produtoId);
        movimentacao.setQuantidade(quantidade);
        movimentacao.setTipo(tipo);
        movimentacao.setDataMovimentacao(dataAtual);

        dao.registrarMovimentacao(movimentacao);
        System.out.println("Movimentação registrada com sucesso.");
    }

    public void listarMovimentacoes(int produtoId) {
        List<Movimentacao> movimentacoes = dao.listarMovimentacoes(produtoId);
        if (movimentacoes.isEmpty()) {
            System.out.println("Não há movimentações para o produto ID " + produtoId);
            return;
        }

        System.out.println("Movimentações de Produto ID " + produtoId + ":");
        for (Movimentacao m : movimentacoes) {
            System.out.println("ID: " + m.getId() + " - Tipo: " + m.getTipo() + " - Quantidade: " + m.getQuantidade() + " - Data: " + m.getDataMovimentacao());
        }
    }

    public void listarMovimentacoesPorPeriodo(int produtoId, String dataInicio, String dataFim) {
        List<Movimentacao> movimentacoes = dao.listarMovimentacoesPorPeriodo(produtoId, dataInicio, dataFim);
        if (movimentacoes.isEmpty()) {
            System.out.println("Não há movimentações para o produto ID " + produtoId + " no período informado.");
            return;
        }

        System.out.println("Movimentações de Produto ID " + produtoId + " entre " + dataInicio + " e " + dataFim + ":");
        for (Movimentacao m : movimentacoes) {
            System.out.println("ID: " + m.getId() + " - Tipo: " + m.getTipo() + " - Quantidade: " + m.getQuantidade() + " - Data: " + m.getDataMovimentacao());
        }
    }
}
