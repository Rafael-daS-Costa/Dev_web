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

@WebServlet(name = "CadastrarClientes", urlPatterns = {"/CadastrarClientes"})
public class CadastraClienteController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/acoesVendedor/cadastraCliente.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        //Parâmetros do request
        String nome_user = request.getParameter("nome");
        String cpf_user = request.getParameter("cpf");
        String endereco_user = request.getParameter("endereco");
        String bairro_user = request.getParameter("bairro");
        String cidade_user = request.getParameter("cidade");
        String uf_user = request.getParameter("uf");
        String cep_user = request.getParameter("cep");
        String telefone_user = request.getParameter("telefone");
        String email_user = request.getParameter("email");
        
        if (nome_user.isEmpty() ||
                cpf_user.isEmpty() ||
                endereco_user.isEmpty() ||
                bairro_user.isEmpty() ||
                cidade_user.isEmpty() ||
                uf_user.isEmpty() ||
                cep_user.isEmpty() ||
                telefone_user.isEmpty() ||
                email_user.isEmpty()) {
            request.setAttribute("msgError", "Preencha todos os campos, por favor.");
            rd = request.getRequestDispatcher("/views/acoesVendedor/cadastraCliente.jsp");
            rd.forward(request, response);
        } else {
            Clientes cliente = new Clientes(nome_user, cpf_user, endereco_user, bairro_user,
            cidade_user, uf_user, cep_user, telefone_user, email_user);
            ClientesDAO clienteDAO = new ClientesDAO();
            
            rd = request.getRequestDispatcher("/views/acoesVendedor/cadastraCliente.jsp");
            rd.forward(request, response);
            
            try {
                clienteDAO.insert(cliente);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha no Cadastro");
            }
        }

    }
    
}
