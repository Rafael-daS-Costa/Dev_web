package controller.admin.vendedor;

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
import javax.servlet.http.HttpSession;

@WebServlet(name = "EscolhaListaController", urlPatterns = {"/admin/admnistrador/EscolhaListaController"})
public class EscolhaListaController extends HttpServlet {
    RequestDispatcher rd;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String escolha  = request.getParameter("escolha");
        String proximaPagina = "/views/admin/vendedores/escolhaOperacaoClientes.jsp";

        request.setAttribute("escolha", escolha);
        if (escolha == "vendas") proximaPagina = "/views/admin/vendedores/escolhaOperacaoVendas.jsp";
        rd = request.getRequestDispatcher(proximaPagina);
        rd.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post");
    }
    
}
