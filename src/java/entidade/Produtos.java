/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

/**
 *
 * @author rafael
 */
public class Produtos {
    private int id;
    private String nome_produto;
    private String descricao;
    private double preco_compra;
    private double preco_venda;
    private int quantidade_disponivel;
    private String liberado_venda;
    private int id_categoria;

    public Produtos() {
    }

    public Produtos(int id, String nome_produto, String descricao, double preco_compra, double preco_venda, int quantidade_disponivel, String liberado_venda, int id_categoria) {
        this.id = id;
        this.nome_produto = nome_produto;
        this.descricao = descricao;
        this.preco_compra = preco_compra;
        this.preco_venda = preco_venda;
        this.quantidade_disponivel = quantidade_disponivel;
        this.liberado_venda = liberado_venda;
        this.id_categoria = id_categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco_compra() {
        return preco_compra;
    }

    public void setPreco_compra(double preco_compra) {
        this.preco_compra = preco_compra;
    }

    public double getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(double preco_venda) {
        this.preco_venda = preco_venda;
    }

    public int getQuantidade_disponivel() {
        return quantidade_disponivel;
    }

    public void setQuantidade_disponivel(int quantidade_disponivel) {
        this.quantidade_disponivel = quantidade_disponivel;
    }

    public String getLiberado_venda() {
        return liberado_venda;
    }

    public void setLiberado_venda(String liberado_venda) {
        this.liberado_venda = liberado_venda;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
    
    
    
}
