package controller;

import entidade.Funcionarios;
import model.FuncionariosDAO;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateFuncionarioController", urlPatterns = {"/UpdateFuncionarioController"})
public class UpdateFuncionarioController extends HttpServlet {
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/acoesComprador/updateFuncionario.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        // pegando os parâmetros do request
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        String papel = request.getParameter("papel");

        if (id.isEmpty() || nome.isEmpty() || cpf.isEmpty() || cpf.isEmpty() || senha.isEmpty() || papel.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "Há categorias vazias");
            rd = request.getRequestDispatcher("/views/acoesComprador/updateFuncionario.jsp");
            rd.forward(request, response);

        } else {
            Funcionarios funcionario = new Funcionarios(Integer.parseInt(id), nome, cpf, senha, papel);
            FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
            funcionariosDAO.update(funcionario);
        }
    }
}
