/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

/**
 *
 * @author rafael
 */
public class Funcionarios {
    private int id;
    private String nome;
    private String cpf;
    private String senha;
    private String papel;

    public Funcionarios(String nome, String cpf, String senha, String papel) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.papel = papel;
    }

    public Funcionarios() {
    }

    public Funcionarios(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public Funcionarios(int id, String nome, String cpf, String senha, String papel) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.papel = papel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
}
