/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidade.Fornecedores;
import model.FornecedoresDao;

/**
 *
 * @author roberto
 */
public interface ValidaIdFornecedor {
    default Boolean idFornecedorEhValido(int id) {
        if (id < 0) {
            return false;
        }
        Fornecedores fornecedor = new FornecedoresDao().get(id);
        // Fornecedor nõa presente no banco. Objeto construído por construtor sem parâmetros
        if (fornecedor.getId() == 0  && fornecedor.getRazao_social().isEmpty() && fornecedor.getCnpj().isEmpty() && fornecedor.getEndereco().isEmpty() && fornecedor.getBairro().isEmpty() && fornecedor.getCidade().isEmpty() && fornecedor.getUf().isEmpty() &&
        fornecedor.getCep().isEmpty() && fornecedor.getTelefone().isEmpty() && fornecedor.getEmail().isEmpty()) {
            return false;
        }
        return true;
    }
}
