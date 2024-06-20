/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entidade.Vendas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class VendasDAO {
    public Vendas get(int id) {
        Conexao conexao = new Conexao();
        Vendas vendas = new Vendas();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement("SELECT * FROM vendas WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            
            if (resultado != null) {
                while (resultado.next()) {
                    vendas.setId(Integer.parseInt(resultado.getString("id")));
                    vendas.setQuantidade_venda(Integer
                            .parseInt(resultado.getString("quantidade_venda")));
                    vendas.setData_venda(resultado.getString("data_venda"));
                    vendas.setValor_venda(Float
                            .parseFloat(resultado.getString("valor_venda")));
                    vendas.setId_cliente(Integer
                            .parseInt(resultado.getString("id_cliente")));
                    vendas.setId_produto(Integer
                            .parseInt(resultado.getString("id_produto")));
                    vendas.setId_funcionario(Integer
                            .parseInt(resultado.getString("id_funcionario")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get vendas) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return vendas;
    }
    
    public void insert(Vendas t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement("INSERT INTO vendas (quantidade_venda, "
                            + "data_venda, valor_venda, id_cliente, id_produto, "
                            + "id_funcionario) VALUES (?,?,?,?,?,?)");
            sql.setInt(1, t.getQuantidade_venda());
            sql.setString(2, t.getData_venda());
            sql.setFloat(3, t.getValor_venda());
            sql.setInt(4, t.getId_cliente());
            sql.setInt(5, t.getId_produto());
            sql.setInt(6, t.getId_funcionario());
            
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de insert (vendas) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void update(Vendas t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement("UPDATE vendas SET quantidade_venda = ?, "
                            + "data_venda = ?, valor_venda = ?, id_cliente = ?, "
                            + "id_produto = ?, id_funcionario = ?  WHERE id = ? ");
            sql.setInt(1, t.getQuantidade_venda());
            sql.setString(2, t.getData_venda());
            sql.setFloat(3, t.getValor_venda());
            sql.setInt(4, t.getId_cliente());
            sql.setInt(5, t.getId_produto());
            sql.setInt(6, t.getId_funcionario());
            sql.setInt(7, t.getId());
            
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de update (alterar vendas) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Vendas> getAll() {

        ArrayList<Vendas> minhasVendas = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM vendas";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Vendas vendas = new Vendas(
                            resultado.getInt("id"),
                            resultado.getInt("quantidade_venda"),
                            resultado.getString("data_venda"),
                            resultado.getFloat("valor_venda"),
                            resultado.getInt("id_cliente"),
                            resultado.getInt("id_produto"),
                            resultado.getInt("id_funcionario"));
                    minhasVendas.add(vendas);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - vendas) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return minhasVendas;
    }
    
}
