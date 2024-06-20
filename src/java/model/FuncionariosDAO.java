/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entidade.Funcionarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class FuncionariosDAO implements Dao<Funcionarios>{
    
    @Override
    public Funcionarios get(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement("SELECT * FROM funcionarios WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            Funcionarios funcionarios = new Funcionarios();

            if (resultado != null) {
                while (resultado.next()) {
                    funcionarios.setId(Integer.parseInt(resultado.getString("id")));
                    funcionarios.setNome(resultado.getString("nome"));
                    funcionarios.setCpf(resultado.getString("cpf"));
                    funcionarios.setSenha(resultado.getString("senha"));
                    funcionarios.setPapel(resultado.getString("papel"));
                }
            }
            return funcionarios;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get funcionarios) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void insert(Funcionarios t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement("INSERT INTO funcionarios (nome, cpf, senha, papel) VALUES (?,?,?,?)");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getCpf());
            sql.setString(3, t.getSenha());
            sql.setString(4, t.getPapel());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de insert (funcionarios) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Funcionarios t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement("UPDATE funcionarios SET nome = ?, "
                            + "cpf = ?, senha = ?, papel = ?  WHERE id = ? ");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getCpf());
            sql.setString(3, t.getSenha());
            sql.setString(4, t.getPapel());

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar funcionarios) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Funcionarios> getAll() {

        ArrayList<Funcionarios> meusFuncionarios = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM funcionarios";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Funcionarios funcionarios = new Funcionarios(
                            resultado.getInt("id"), 
                            resultado.getString("nome"),
                            resultado.getString("cpf"),
                            resultado.getString("senha"),
                            resultado.getString("papel"));
                    meusFuncionarios.add(funcionarios);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (GetAll) incorreta" + e.getMessage());
        } finally {
            conexao.closeConexao();
        }
        return meusFuncionarios;
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement("DELETE FROM funcionarios WHERE id = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir funcionario) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public Funcionarios Logar(Funcionarios funcionario) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM funcionarios WHERE cpf=? and senha =? LIMIT 1");
            sql.setString(1, funcionario.getCpf());
            sql.setString(2, funcionario.getSenha());
            ResultSet resultado = sql.executeQuery();
            Funcionarios funcionarioObtido = new Funcionarios();
            if (resultado != null) {
                while (resultado.next()) {
                    System.out.println(resultado.getString("id"));
                    funcionarioObtido.setId(Integer.parseInt(resultado.getString("id")));
                    funcionarioObtido.setNome(resultado.getString("nome"));
                    funcionarioObtido.setCpf(resultado.getString("cpf"));
                    funcionarioObtido.setPapel(resultado.getString("papel"));
                    funcionarioObtido.setSenha(resultado.getString("senha"));
                }
            }
            return funcionarioObtido;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}
