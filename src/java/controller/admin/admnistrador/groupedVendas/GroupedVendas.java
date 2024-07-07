package controller.admin.admnistrador.groupedVendas;

import entidade.Vendas;

public class GroupedVendas {

    private final int id_produto;
    private float total = 0;
    
    
    public GroupedVendas(Vendas venda) {
        this.id_produto = venda.getId_produto();
        this.total += venda.getValor_venda();
    }
    
    /**
     * Adiciona uma venda, somando seu valor ao atributo "total" do objeto GroupedVendas 
     *
     * @param venda Uma venda qualquer
     * @return true se adição tiver sucesso, false se venda.getId() for diferente de id_produto
     */
    public boolean addVenda(Vendas venda) {
        if (this.id_produto != venda.getId_produto())
            return false;
        this.total += venda.getValor_venda();
        return true;
    }

    public int getKey() {
        return id_produto;
    }
    
    public float getTotal() {
        return total;
    }
}
