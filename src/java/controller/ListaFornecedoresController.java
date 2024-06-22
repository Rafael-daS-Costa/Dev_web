package controller;

import entidade.Fornecedores;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FornecedoresDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rafael
 */
@WebServlet(name = "ListaFornecedores", urlPatterns = {"/ListaFornecedores"})
public class ListaFornecedoresController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                   FornecedoresDAO categoriasDAO = new FornecedoresDAO();
            try {
                ArrayList<Fornecedores> listaFornecedores = categoriasDAO.getAll();
                request.setAttribute("listaFornecedores", listaFornecedores);
                RequestDispatcher rd = request.getRequestDispatcher("/views/acoesComprador/listaFornecedores.jsp");
                rd.forward(request, response);
                
                
            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar categorias (listarFornecedores) ");
            }
    }
    
}
