package controller;

import entidade.Clientes;
import entidade.Vendas;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.VendasDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rafael
 */
@WebServlet(name = "ListaVendas", urlPatterns = {"/ListaVendas"})
public class ListaVendasController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                   VendasDAO vendaDAO = new VendasDAO();
            try {
                ArrayList<Vendas> listaVendas = vendaDAO.getAll();
                request.setAttribute("listaVendas", listaVendas);
                RequestDispatcher rd = request.getRequestDispatcher("/views/acoesVendedor/listaVendas.jsp");
                rd.forward(request, response);
                
                
            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar produtos (listarVendas) ");
            }
    }
    
}
