package controller;

import entidade.Categorias;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoriasDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rafael
 */
@WebServlet(name = "ListaCategorias", urlPatterns = {"/ListaCategorias"})
public class ListaCategoriasController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                   CategoriasDAO categoriasDAO = new CategoriasDAO();
            try {
                ArrayList<Categorias> listaCategorias = categoriasDAO.getAll();
                System.out.println(listaCategorias);
                System.out.println("Oi");
                request.setAttribute("listaCategorias", listaCategorias);
                RequestDispatcher rd = request.getRequestDispatcher("/views/acoesComprador/listaCategorias.jsp");
                rd.forward(request, response);
                
                
            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar categorias (listarCategorias) ");
            }
    }
    
}
