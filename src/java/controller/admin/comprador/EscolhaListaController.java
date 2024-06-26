/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin.comprador;

import entidade.Compras;
import entidade.Fornecedores;
import entidade.Funcionarios;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ComprasDAO;
import model.FornecedoresDao;

/**
 *
 * @author rafael
 */
@WebServlet(name = "EscolhaListaControllerComprador", urlPatterns = {"/admin/comprador/EscolhaListaController"})
public class EscolhaListaController extends HttpServlet {
    RequestDispatcher rd;
    FornecedoresDao fornecedoresDAO = new FornecedoresDao();
    ComprasDAO comprasDAO = new ComprasDAO();

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String escolha  = request.getParameter("escolha");
        String proximaPagina = "";

        request.setAttribute("escolha", escolha);
        switch(escolha) {
            case "fornecedores":
                proximaPagina = "/views/admin/compradores/listaFornecedores.jsp";
                request.setAttribute("listaFornecedor", getFornecedores());
                break;
            case "compras":
                proximaPagina = "/views/admin/compradores/listaCompras.jsp";
                request.setAttribute("listaCompras", getCompras());
                break;
        }
        rd = request.getRequestDispatcher(proximaPagina);
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post");
    }
    
    private ArrayList<Fornecedores> getFornecedores() {
        List<Fornecedores> listaFornecedores = this.fornecedoresDAO.getAll();
        return new ArrayList<Fornecedores>(listaFornecedores);
    }
    
    private ArrayList<Compras> getCompras() {
        List<Compras> listaCompras = this.comprasDAO.getAll();
        return new ArrayList<Compras>(listaCompras);
    }
}
