package controller.admin.vendedor;

import entidade.Clientes;
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
import model.ClientesDAO;

@WebServlet(name = "EscolhaVendedorListaController", urlPatterns = {"/admin/vendedor/EscolhaListaController"})
public class EscolhaListaController extends HttpServlet {
    RequestDispatcher rd;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String escolha  = request.getParameter("escolha");
        String proximaPagina = "/views/admin/vendedores/listaClientes.jsp";
        ArrayList<Clientes> listaClientes = new ClientesDAO().ListaDeClientes();
        request.setAttribute("listaClientes", listaClientes);
        request.setAttribute("escolha", escolha);
        if (escolha.equals("vendas")) proximaPagina = "/views/admin/vendedores/listaVendas.jsp";
        rd = request.getRequestDispatcher(proximaPagina);
        rd.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post");
    }
    
}
