package controller;

import entidade.Funcionarios;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FuncionariosDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rafael
 */

@WebServlet(name = "CadastrarFuncionarios", urlPatterns = {"/CadastrarFuncionarios"})
public class CadastraFuncionarioController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/acoesVendedor/cadastraFuncionario.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        //Parâmetros do request
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        String papel = request.getParameter("papel");
        
        if (nome.isEmpty() ||
                cpf.isEmpty() || senha.isEmpty() || papel.isEmpty()) {
            request.setAttribute("msgError", "Preencha todos os campos, por favor.");
            rd = request.getRequestDispatcher("/views/acoesAdmnistrador/cadastraFuncionario.jsp");
            rd.forward(request, response);
        } else {
            Funcionarios cliente = new Funcionarios(nome, cpf, senha, papel);
            FuncionariosDAO clienteDAO = new FuncionariosDAO();
            
            rd = request.getRequestDispatcher("/views/acoesFuncionario/cadastraFuncionario.jsp");
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
