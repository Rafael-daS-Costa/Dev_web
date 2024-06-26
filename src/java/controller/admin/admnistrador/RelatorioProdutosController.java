/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this lwicense
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin.admnistrador;

import entidade.Produtos;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProdutosDao;


/**
 *
 * @author roberto
 */
@WebServlet(name = "RelatorioProdutos", urlPatterns = {"/RelatorioProdutos"})
public class RelatorioProdutosController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                   ProdutosDao produtoDAO = new ProdutosDao();
            try {
                ArrayList<Produtos> listaProdutos = produtoDAO.getAll();
                request.setAttribute("listaProdutos", listaProdutos);
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/admnistradores/relatorioProdutos.jsp");           
                rd.forward(request, response);
                
            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar produtos (mostrarProdutos) ");
            }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        
    }
    
}