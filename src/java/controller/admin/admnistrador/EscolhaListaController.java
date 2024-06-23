package controller.admin.admnistrador;

import entidade.Funcionarios;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.FuncionariosDAO;

@WebServlet(name = "EscolhaListaController", urlPatterns = {"/admin/admnistrador/EscolhaListaController"})
public class EscolhaListaController extends HttpServlet {
    RequestDispatcher rd;
    FuncionariosDAO funcionariosDAO = new FuncionariosDAO();

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String escolha  = request.getParameter("escolha");
        String proximaPagina = "/views/admin/admnistradores/listaAdmnistradores.jsp";


        request.setAttribute("listaAdmnistrador", getAdmnistradores());
        if (escolha.equals("vendedores")) {
            proximaPagina = "/views/admin/admnistradores/listaVendedores.jsp";
        }
        rd = request.getRequestDispatcher(proximaPagina);
        rd.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post");
    }
    

    private ArrayList<Funcionarios> getAdmnistradores() {
        List<Funcionarios> listaAdmnistradores = this.funcionariosDAO.getAll().stream().filter(f -> f.getPapel().equals("0")).collect(Collectors.toList());  // Filtra apenas admnistradores
        return new ArrayList<Funcionarios>(listaAdmnistradores);
    }
}
