/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entidade.Produtos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class ProdutosDao implements Dao<Produtos> {
    
    @Override
    public Produtos get(int id) {
        Conexao conexao = new Conexao();
        Produtos produtos = new Produtos();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement("SELECT * FROM produtos WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    produtos.setId(Integer.parseInt(resultado.getString("id")));
                    produtos.setNome_produto(resultado.getString("nome_produto"));
                    produtos.setDescricao(resultado.getString("descricao"));
                    produtos.setPreco_compra(Double
                            .parseDouble(resultado.getString("preco_compra")));
                    produtos.setPreco_venda(Double
                            .parseDouble(resultado.getString("preco_venda")));
                    produtos.setQuantidade_disponivel(Integer
                            .parseInt(resultado.getString("quantidade_disponivel")));
                    produtos.setLiberado_venda(resultado.getString("liberado_venda"));
                    produtos.setId_categoria(Integer.parseInt(resultado.getString("id_categoria")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get produtos) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return produtos;
    }

    @Override
    public void insert(Produtos t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement("INSERT INTO produtos (nome_produto, "
                            + "descricao, preco_compra, preco_venda, "
                            + "quantidade_disponível, liberado_venda, "
                            + "id_categoria) VALUES (?,?,?,?,?,?,?)");
            sql.setString(1, t.getNome_produto());
            sql.setString(2, t.getDescricao());
            sql.setDouble(3, t.getPreco_compra());
            sql.setDouble(4, t.getPreco_venda());
            sql.setInt(5, t.getQuantidade_disponivel());
            sql.setString(6, t.getLiberado_venda());
            sql.setInt(7, t.getId_categoria());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de insert (produtos) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Produtos t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement("UPDATE produtos SET nome_produto = ?, "
                            + "descricao = ?, preco_compra = ?, preco_venda = ?, "
                            + "quantidade_disponível = ?, liberado_venda = ?, "
                            + "id_categoria = ?  WHERE id = ? ");
            
            sql.setString(1, t.getNome_produto());
            sql.setString(2, t.getDescricao());
            sql.setDouble(3, t.getPreco_compra());
            sql.setDouble(4, t.getPreco_venda());
            sql.setInt(5, t.getQuantidade_disponivel());
            sql.setString(6, t.getLiberado_venda());
            sql.setInt(7, t.getId_categoria());
            sql.setInt(8, t.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de update (alterar produtos) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement("DELETE FROM produtos WHERE id = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de delete (excluir produtos) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Produtos> getAll() {

        ArrayList<Produtos> meusProdutos = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM produtos";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Produtos produtos = new Produtos(
                            resultado.getInt("id"),
                            resultado.getString("nome_produto"),
                            resultado.getString("descricao"),
                            resultado.getDouble("preco_compra"),
                            resultado.getDouble("preco_venda"),
                            resultado.getInt("quantidade_disponível"),
                            resultado.getString("liberado_venda"),
                            resultado.getInt("id_categoria"));
                    meusProdutos.add(produtos);
                }
            }
            System.out.println(meusProdutos);
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - produtos) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusProdutos;
    }
}
