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

@WebServlet(name = "CadastraCategoriaController", urlPatterns = {"/CadastraCategoriaController"})
public class CadastraCategoriaController extends HttpServlet {
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/acoesComprador/cadastraCategoria.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        // pegando os parâmetros do request
        String nome_categoria = request.getParameter("nome_categoria");

        if (nome_categoria.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "Nome da categoria está vazio");
            rd = request.getRequestDispatcher("/views/acoesComprador/cadastraCategoria.jsp");
            rd.forward(request, response);

        } else {
            Categorias categoria = new Categorias(nome_categoria);
            CategoriasDAO categoriasDAO = new CategoriasDAO();
            categoriasDAO.insert(categoria);
        }
    }
}
