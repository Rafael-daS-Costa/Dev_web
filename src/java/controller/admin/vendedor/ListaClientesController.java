package controller;

import entidade.Clientes;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ClientesDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rafael
 */
@WebServlet(name = "ListaClientes", urlPatterns = {"/ListaClientes"})
public class ListaClientesController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                   ClientesDAO clienteDAO = new ClientesDAO();
            try {
                ArrayList<Clientes> listaClientes = clienteDAO.ListaDeClientes();
                request.setAttribute("listaClientes", listaClientes);
                RequestDispatcher rd = request.getRequestDispatcher("/views/acoesVendedor/listaClientes.jsp");
                rd.forward(request, response);
                
                
            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar produtos (listarClientes) ");
            }
    }
    
}
