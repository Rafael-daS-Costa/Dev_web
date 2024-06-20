package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidade.Clientes;

public class ClientesDAO {
    
    public Clientes get(int id) {
        Conexao conexao = new Conexao();
        Clientes clientes = new Clientes();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM clientes WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    clientes.setId(Integer.parseInt(resultado.getString("id")));
                    clientes.setNome(resultado.getString("nome"));
                    clientes.setCpf(resultado.getString("cpf"));
                    clientes.setEndereco(resultado.getString("endereco"));
                    clientes.setBairro(resultado.getString("bairro"));
                    clientes.setCidade(resultado.getString("cidade"));
                    clientes.setUf(resultado.getString("uf"));
                    clientes.setCep(resultado.getString("cep"));
                    clientes.setTelefone(resultado.getString("telefone"));
                    clientes.setEmail(resultado.getString("email"));

                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get clientes) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return clientes;
    }

    public void insert(Clientes t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO clientes (nome, cpf, endereco, bairro, cidade, uf, cep, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getCpf());
            sql.setString(3, t.getEndereco());
            sql.setString(5, t.getBairro());
            sql.setString(6, t.getCidade());
            sql.setString(7, t.getUf());
            sql.setString(8, t.getCep());
            sql.setString(9, t.getEmail());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de insert (clientes) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void update(Clientes t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE clientes SET nome = ?, cpf = ?, endereco = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, email = ?  WHERE ID = ? ");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getCpf());
            sql.setString(3, t.getEndereco());
            sql.setString(5, t.getBairro());
            sql.setString(6, t.getCidade());
            sql.setString(7, t.getUf());
            sql.setString(8, t.getCep());
            sql.setString(9, t.getEmail());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de update (alterar clientes) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Clientes clientes) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM clientes WHERE ID = ? ");
            sql.setInt(1, clientes.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Clientes> ListaDeClientes() {
        ArrayList<Clientes> meusClientes = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM clientes order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Clientes cliente = new Clientes(resultado.getString("nome"),
                            resultado.getString("cpf"),
                            resultado.getString("endereco"),
                            resultado.getString("bairro"),
                            resultado.getString("cidade"),
                            resultado.getString("uf"),
                            resultado.getString("cep"),
                            resultado.getString("telefone"),
                            resultado.getString("email"));
                    cliente.setId(Integer.parseInt(resultado.getString("id")));
                    meusClientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (clientes) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusClientes;
    }

}
