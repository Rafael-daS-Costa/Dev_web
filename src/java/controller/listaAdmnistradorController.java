package controller;

import model.FuncionariosDAO;
import entidade.Funcionarios;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "listaAdmnistrador", urlPatterns = {"/listaAdmnistrador"})
public class listaAdmnistradorController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                   FuncionariosDAO funcionarioDAO = new FuncionariosDAO();
            try {
                ArrayList<Funcionarios> listaAdmnistradores = funcionarioDAO.getAll();
                request.setAttribute("listaAdmnistrador", listaAdmnistradores);
                RequestDispatcher rd = request.getRequestDispatcher("/views/acoesAdmnistrador/listaAdmnistrador.jsp");
                rd.forward(request, response);
                
                
            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar usuarios (ListarAdmnistradors) ");
            }
    }
}
