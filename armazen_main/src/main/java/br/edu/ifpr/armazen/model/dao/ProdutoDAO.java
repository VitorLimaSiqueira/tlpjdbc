package br.edu.ifpr.armazen.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifpr.armazen.model.Produto;

public class ProdutoDAO {

    public void adicionarProduto(Produto produto) {
        String sql = "INSERT INTO Produto (nome, descricao, quantidade, preco) VALUES (?, ?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, produto.getnome());
            stmt.setString(2, produto.getDescricao());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setDouble(4, produto.getPreco());

            stmt.executeUpdate();
            System.out.println("Produto adicionado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM Produto";
        Connection con = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setnome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setPreco(rs.getFloat("preco"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public Produto buscarProdutoPorId(int id) {
        Produto produto = null;
        String sql = "SELECT * FROM Produto WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto();
                    produto.setId(rs.getInt("id"));
                    produto.setnome(rs.getString("nome"));
                    produto.setDescricao(rs.getString("descricao"));
                    produto.setQuantidade(rs.getInt("quantidade"));
                    produto.setPreco(rs.getFloat("preco"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produto;
    }

    public void atualizarEstoque(int produtoId, int quantidade) {
        String sql = "UPDATE Produto SET quantidade = quantidade + ? WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);
            stmt.setInt(2, produtoId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Estoque atualizado com sucesso!");
            } else {
                System.out.println("Produto não encontrado para atualizar o estoque.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE Produto SET nome = ?, descricao = ?, quantidade = ?, preco = ? WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, produto.getnome());
            stmt.setString(2, produto.getDescricao());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setDouble(4, produto.getPreco());
            stmt.setInt(5, produto.getId());

            stmt.executeUpdate();
            System.out.println("Produto atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirProduto(int id) {
        String sql = "DELETE FROM Produto WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
            System.out.println("Produto excluído com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
