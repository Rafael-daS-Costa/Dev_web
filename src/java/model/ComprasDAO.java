package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
;
import entidade.Compras;

public class ComprasDAO implements Dao<Compras> {
    @Override
    public Compras get(int id) {
        Conexao conexao = new Conexao();
        Compras categoria = new Compras();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM compras WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    categoria.setId(Integer.parseInt(resultado.getString("id")));
                    categoria.setQuantidade_compra(Integer.parseInt(resultado.getString("quantidade_compra")));
                    categoria.setData_compra(resultado.getString("data_compra"));
                    categoria.setValor_compra(Integer.parseInt(resultado.getString("valor_compra")));
                    categoria.setId_fornecedor(Integer.parseInt(resultado.getString("id_fornecedor")));
                    categoria.setId_produto(Integer.parseInt(resultado.getString("id_produto")));
                    categoria.setId_funcionario(Integer.parseInt(resultado.getString("id_funcionario")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get compras) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return categoria;
    }

    @Override
    public void insert(Compras t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO compras (quantidade_compra, data_compra, valor_compra, id_fornecedor, id_produto, id_funcionario) VALUES (?, ?, ?, ?, ?, ?)");
            sql.setInt(1, t.getQuantidade_compra());
            sql.setString(2, t.getData_compra());
            sql.setInt(3, t.getValor_compra());
            sql.setInt(4, t.getId_fornecedor());
            sql.setInt(5, t.getId_produto());
            sql.setInt(6, t.getId_funcionario());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de insert (compras) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

        @Override
    public void update(Compras t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE compras SET quantidade_compra = ?, data_compra = ?, valor_compra = ?, id_fornecedor = ?, id_produto = ?, id_funcionario = ? WHERE id = ? ");
            sql.setInt(1, t.getQuantidade_compra());
            sql.setString(2, t.getData_compra());
            sql.setInt(3, t.getValor_compra());
            sql.setInt(4, t.getId_fornecedor());
            sql.setInt(5, t.getId_produto());
            sql.setInt(6, t.getId_funcionario());
            sql.setInt(7, t.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de update (alterar compras) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM compras WHERE id = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de delete (excluir compras) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Compras> getAll() {

        ArrayList<Compras> meusCompras = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM compras";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Compras Compras = new Compras(
                            resultado.getInt("id"),
                            resultado.getInt("quantidade_compra"),
                            resultado.getString("data_compra"),
                            resultado.getInt("valor_compra"),
                            resultado.getInt("id_fornecedor"),
                            resultado.getInt("id_produto"),
                            resultado.getInt("id_funcionario")
                    );
                    meusCompras.add(Compras);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - compras) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusCompras;
    }
}
