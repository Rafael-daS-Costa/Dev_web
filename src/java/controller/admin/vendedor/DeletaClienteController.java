package controller;


import entidade.Clientes;
import java.io.IOException;
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
@WebServlet(name = "DeletarClientes", urlPatterns = {"/DeletarClientes"})
public class DeletaClienteController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/acoesVendedor/deletaCliente.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        //Parâmetros do request
        int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
        
        if (id_cliente == 0) {
            request.setAttribute("msgError", "Preencha todos os campos, por favor.");
            rd = request.getRequestDispatcher("/views/acoesVendedor/deletaCliente.jsp");
            rd.forward(request, response);
        } else {
            ClientesDAO clienteDAO = new ClientesDAO();
            Clientes cliente = clienteDAO.get(id_cliente);
            
            try {
                if (cliente.getId() != 0) {
                    clienteDAO.delete(id_cliente);
                    
                    rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                    request.setAttribute("msgOperacaoRealizada", "Cliente deletado com sucesso");
                    request.setAttribute("link", "/aplicacaoMVC/home");
                    rd.forward(request, response);
                } else {
                    throw new Exception("Não há esse cliente para ser deletado");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                rd = request.getRequestDispatcher("/views/acoesVendedor/deletaCliente.jsp");
                request.setAttribute("msgErro", "Não é possível deletar este cliente, cheque se ele existe, por favor.");
                rd.forward(request, response);
            }
        }

    }
}
