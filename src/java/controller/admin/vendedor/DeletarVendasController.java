package controller;

import entidade.Vendas;
import java.io.IOException;
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
@WebServlet(name = "DeletarVendas", urlPatterns = {"/DeletarVendas"})
public class DeletarVendasController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/acoesVendedor/deletaVendas.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        //Parâmetros do request
        int id_venda = Integer.parseInt(request.getParameter("id_venda"));
        
        if (id_venda == 0) {
            request.setAttribute("msgError", "Preencha todos os campos, por favor.");
            rd = request.getRequestDispatcher("/views/acoesVendedor/deletaVendas.jsp");
            rd.forward(request, response);
        } else {
            VendasDAO vendaDAO = new VendasDAO();
            Vendas venda = vendaDAO.get(id_venda);
            
            try {
                if (venda.getId() != 0) {
                    vendaDAO.delete(id_venda);
                    
                    rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                    request.setAttribute("msgOperacaoRealizada", "Venda deletada com sucesso");
                    request.setAttribute("link", "/aplicacaoMVC/home");
                    rd.forward(request, response);
                } else {
                    throw new Exception("Não há esse venda para ser deletado");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                rd = request.getRequestDispatcher("/views/acoesVendedor/deletaVenda.jsp");
                request.setAttribute("msgErro", "Não é possível deletar esta venda, cheque se ele existe, por favor.");
                rd.forward(request, response);
            }
        }

    }
}