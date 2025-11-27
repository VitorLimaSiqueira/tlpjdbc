package br.edu.ifpr.armazen.model.dao;

import br.edu.ifpr.armazen.model.Movimentacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoDAO {
    
    public List<Movimentacao> listarMovimentacoes(int produtoId) {
        List<Movimentacao> movimentacoes = new ArrayList<>();
        String sql = "SELECT * FROM Movimentacao WHERE produto_id = ? ORDER BY data_movimentacao DESC";
        Connection con = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, produtoId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Movimentacao movimentacao = new Movimentacao();
                    movimentacao.setId(rs.getInt("id"));
                    movimentacao.setProdutoId(rs.getInt("produto_id"));
                    movimentacao.setDataMovimentacao(rs.getString("data_movimentacao"));
                    movimentacao.setQuantidade(rs.getInt("quantidade"));
                    movimentacao.setTipo(rs.getString("tipo"));

                    movimentacoes.add(movimentacao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movimentacoes;
    }

    public List<Movimentacao> listarMovimentacoesPorPeriodo(int produtoId, String dataInicio, String dataFim) {
        List<Movimentacao> movimentacoes = new ArrayList<>();
        String sql = "SELECT * FROM Movimentacao WHERE produto_id = ? AND data_movimentacao BETWEEN ? AND ? ORDER BY data_movimentacao DESC";
        Connection con = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, produtoId);
            stmt.setString(2, dataInicio);  
            stmt.setString(3, dataFim);  

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Movimentacao movimentacao = new Movimentacao();
                    movimentacao.setId(rs.getInt("id"));
                    movimentacao.setProdutoId(rs.getInt("produto_id"));
                    movimentacao.setDataMovimentacao(rs.getString("data_movimentacao"));  
                    movimentacao.setQuantidade(rs.getInt("quantidade"));
                    movimentacao.setTipo(rs.getString("tipo"));

                    movimentacoes.add(movimentacao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movimentacoes;
    }

    public void registrarMovimentacao(Movimentacao movimentacao) {
        String sqlMovimentacao = "INSERT INTO Movimentacao (produto_id, quantidade, tipo, data_movimentacao) VALUES (?, ?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();
        try (PreparedStatement stmtMov = con.prepareStatement(sqlMovimentacao)) {
            stmtMov.setInt(1, movimentacao.getProdutoId());
            stmtMov.setInt(2, movimentacao.getQuantidade());
            stmtMov.setString(3, movimentacao.getTipo());
            stmtMov.setString(4, movimentacao.getDataMovimentacao());

            stmtMov.executeUpdate();

            if (movimentacao.getTipo().equals("entrada")) {
                atualizarEstoque(movimentacao.getProdutoId(), movimentacao.getQuantidade());
            } else if (movimentacao.getTipo().equals("saida")) {
                atualizarEstoque(movimentacao.getProdutoId(), -movimentacao.getQuantidade());
            }
            System.out.println("Movimentação registrada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarEstoque(int produtoId, int quantidade) {
        String sql = "UPDATE Produto SET quantidade = quantidade + ? WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);
            stmt.setInt(2, produtoId);

            stmt.executeUpdate();
            System.out.println("Estoque atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
