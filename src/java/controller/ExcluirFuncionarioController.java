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

@WebServlet(name = "ExcluirFuncionarioController", urlPatterns = {"/ExcluirFuncionarioController"})
public class ExcluirFuncionarioController extends HttpServlet {
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

        if (id.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msg", "Há categorias vazias");
            rd = request.getRequestDispatcher("/views/acoesComprador/excluirFuncionario.jsp");
            rd.forward(request, response);

        } else {
            FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
            funcionariosDAO.delete(Integer.parseInt(id));
        }
    }
}
