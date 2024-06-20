package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidade.Categorias;

public class CategoriasDAO implements Dao<Categorias> {
    @Override
    public Categorias get(int id) {
        Conexao conexao = new Conexao();
        Categorias categoria = new Categorias();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Categorias WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    categoria.setId(Integer.parseInt(resultado.getString("id")));
                    categoria.setNome_categoria(resultado.getString("nome_categoria"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get categorias) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return categoria;
    }

    @Override
    public void insert(Categorias t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Categorias (nome_categoria) VALUES (?)");
            sql.setString(1, t.getNome_categoria());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de insert (categorias) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Categorias t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Categorias SET nome_categoria = ?  WHERE ID = ? ");
            sql.setString(1, t.getNome_categoria());
            sql.setInt(2, t.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de update (alterar categoria) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Categorias WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de delete (excluir categoria) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Categorias> getAll() {

        ArrayList<Categorias> meusCategorias = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM Categorias";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Categorias Categorias = new Categorias(
                            resultado.getInt("id"),
                            resultado.getString("nome_categoria")
                    );
                    meusCategorias.add(Categorias);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - categorias) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusCategorias;
    }
}
