package controller;

import entidade.Fornecedores;
import model.FornecedoresDao;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ExcluirFornecedoresController", urlPatterns = {"/ExcluirFornecedoresController"})
public class ExcluirFornecedoresController extends HttpServlet {
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/acoesComprador/excluirFornecedores.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        // pegando os parâmetros do request
        String id_fornecedor = request.getParameter("id_fornecedor");

        if (id_fornecedor.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "id do fornecedor está vazio");
            rd = request.getRequestDispatcher("/views/acoesComprador/excluirFornecedores.jsp");
            rd.forward(request, response);

        } else {
            FornecedoresDao categoriasDAO = new FornecedoresDao();
            categoriasDAO.delete(Integer.parseInt(id_fornecedor));
        }
    }
}
