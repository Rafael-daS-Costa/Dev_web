package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidade.Fornecedores;
import entidade.Funcionarios;

public class FornecedoresDao implements Dao<Fornecedores> {
    @Override
    public Fornecedores get(int id) {
        Conexao conexao = new Conexao();
        Fornecedores categoria = new Fornecedores();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM fornecedores WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    categoria.setId(Integer.parseInt(resultado.getString("id")));
                    categoria.setRazao_social(resultado.getString("razao_social"));
                    categoria.setCnpj(resultado.getString("cnpj"));
                    categoria.setEndereco(resultado.getString("endereco"));
                    categoria.setBairro(resultado.getString("bairro"));
                    categoria.setCidade(resultado.getString("cidade"));
                    categoria.setUf(resultado.getString("uf"));
                    categoria.setCep(resultado.getString("cep"));
                    categoria.setTelefone(resultado.getString("telefone"));
                    categoria.setEmail(resultado.getString("email"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get fornecedores) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return categoria;
    }

    @Override
    public void insert(Fornecedores t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO fornecedores (razao_social, cnpj, endereco, bairro, cidade, uf, cep, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            sql.setString(1, t.getRazao_social());
            sql.setString(2, t.getCnpj());
            sql.setString(3, t.getEndereco());
            sql.setString(4, t.getBairro());
            sql.setString(5, t.getCidade());
            sql.setString(6, t.getUf());
            sql.setString(7, t.getCep());
            sql.setString(8, t.getTelefone());
            sql.setString(9, t.getEmail());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de insert (fornecedores) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Fornecedores t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE fornecedores SET razao_social = ?, cnpj = ?, endereco = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, telefone = ?, email = ?  WHERE ID = ? ");
            sql.setString(1, t.getRazao_social());
            sql.setString(2, t.getCnpj());
            sql.setString(3, t.getEndereco());
            sql.setString(4, t.getBairro());
            sql.setString(5, t.getCidade());
            sql.setString(6, t.getUf());
            sql.setString(7, t.getCep());
            sql.setString(8, t.getTelefone());
            sql.setString(9, t.getEmail());
            sql.setInt(10, t.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de update (alterar fonrnecedores) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM fornecedores WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de delete (excluir fonrnecedores) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Fornecedores> getAll() {

        ArrayList<Fornecedores> meusFornecedores = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM fornecedores";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Fornecedores Fornecedores = new Fornecedores(
                            resultado.getInt("id"),
                            resultado.getString("razao_social"),
                            resultado.getString("cnpj"),
                            resultado.getString("endereco"),
                            resultado.getString("bairro"),
                            resultado.getString("cidade"),
                            resultado.getString("uf"),
                            resultado.getString("cep"),
                            resultado.getString("telefone"),
                            resultado.getString("email")
                    );
                    meusFornecedores.add(Fornecedores);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - fornecedores) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusFornecedores;
    }
}
