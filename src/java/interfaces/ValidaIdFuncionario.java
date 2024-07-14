package interfaces;

import entidade.Funcionarios;
import model.FuncionariosDAO;

public interface ValidaIdFuncionario {
    default Boolean idFuncionarioEhValido(int id) {
        if (id < 0) {
            return false;
        }
        Funcionarios funcionario = new FuncionariosDAO().get(id);
        if (funcionario.getId() == 0 && funcionario.getNome().isEmpty() && funcionario.getCpf().isEmpty() && funcionario.getSenha().isEmpty() && funcionario.getPapel().isEmpty()) {
            return false;
        }
        return true;
    }
}
