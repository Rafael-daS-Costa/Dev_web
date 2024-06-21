package controller;

import entidade.Vendas;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ClientesDAO;
import model.VendasDAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rafael
 */
@WebServlet(name = "CadastrarVenda", urlPatterns = {"/CadastrarVenda"})
public class CadastraVendaController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/acoesVendedor/cadastraVenda.jsp");
        rd.forward(request, response);

    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        //Parâmetros do request
        int quantidade_venda = Integer.parseInt(request.getParameter("quantidade_venda"));
        String data_venda = request.getParameter("data_venda");
        float valor_venda = Float.parseFloat(request.getParameter("valor_venda"));
        int id_produto_venda = Integer.parseInt(request.getParameter("id_produto"));
        int id_cliente_venda = Integer.parseInt(request.getParameter("id_cliente"));
        int id_funcionario_venda = Integer.parseInt(request.getParameter("id_funcionario"));
        
        
        if (data_venda.isEmpty() ||
                quantidade_venda == 0 ||
                valor_venda == 0.0f ||
                id_produto_venda == 0 ||
                id_cliente_venda == 0 ||
                id_funcionario_venda == 0) {
            request.setAttribute("msgError", "Preencha todos os campos, por favor.");
            rd = request.getRequestDispatcher("/views/acoesVendedor/cadastraVenda.jsp");
            rd.forward(request, response);
        } else {
            Vendas venda = new Vendas(quantidade_venda, data_venda, valor_venda, id_cliente_venda,
            id_produto_venda, id_funcionario_venda);
            
            ClientesDAO clienteDAO = new ClientesDAO();
            if (clienteDAO.get(id_cliente_venda) == null) {
                request.setAttribute("msgError", "É preciso cadastrar um cliente para vender produtos a ele.");
                rd = request.getRequestDispatcher("/views/acoesVendedor/cadastraCliente.jsp");
                rd.forward(request, response);
            } else {
                try {
                    VendasDAO vendaDAO = new VendasDAO();
                    vendaDAO.insert(venda);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha no Cadastro");
                } finally {
                    rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                    request.setAttribute("msgOperacaoRealizada", "Cadastro de venda efetuado com sucesso");
                    request.setAttribute("link", "/aplicacaoMVC/home");
                    rd.forward(request, response);
                }
            }
        }

    }
}
