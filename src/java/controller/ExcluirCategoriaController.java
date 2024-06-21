package controller;

import entidade.Categorias;
import model.CategoriasDAO;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ExcluirCategoriaController", urlPatterns = {"/ExcluirCategoriaController"})
public class ExcluirCategoriaController extends HttpServlet {
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/acoesComprador/excluirCategoria.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        // pegando os parâmetros do request
        String id_categoria = request.getParameter("id_categoria");

        if (id_categoria.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "id da categoria está vazio");
            rd = request.getRequestDispatcher("/views/acoesComprador/excluirCategoria.jsp");
            rd.forward(request, response);

        } else {
            CategoriasDAO categoriasDAO = new CategoriasDAO();
            categoriasDAO.delete(Integer.parseInt(id_categoria));
        }
    }
}
