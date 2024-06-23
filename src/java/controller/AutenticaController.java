package controller;

import entidade.Fornecedores;
import entidade.Funcionarios;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FuncionariosDAO;
import model.FornecedoresDao;

/**
 *
 * @author Leonardo
 */
@WebServlet(name = "AutenticaController", urlPatterns = {"/AutenticaController"})
public class AutenticaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        // pegando os parâmetros do request
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");
        if (cpf_user.isEmpty() || senha_user.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "Usuário e/ou senha incorreto");
            rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
            rd.forward(request, response);

        } else {
            Funcionarios funcionarioObtido;
            Funcionarios funcionario = new Funcionarios(cpf_user, senha_user);
            FuncionariosDAO funcionarioDAO = new FuncionariosDAO();
            try {
                funcionarioObtido = funcionarioDAO.Logar(funcionario);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para Logar");
            }

            if (funcionarioObtido.getId() != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("funcionario", funcionarioObtido);

                //rd = request.getRequestDispatcher("/admin/dashboard");
                //rd.forward(request, response);

                switch (funcionarioObtido.getPapel()) {
                    case "1":  // Vendedor
                        rd = request.getRequestDispatcher("/views/acoesVendedor/teste.jsp");
                        rd.forward(request, response);
                        break;
                    case "2":  // Comprador
                        ArrayList<Fornecedores> listaFornecedores = new FornecedoresDao().getAll();
                        request.setAttribute("listaFornecedores", listaFornecedores);
                        rd = request.getRequestDispatcher("/views/acoesComprador/listaFornecedores.jsp");
                        rd.forward(request, response);
                        break;
                    default:
                        // Admin
                        rd = request.getRequestDispatcher("/views/admin/admnistradores/escolhaLista.jsp");
                        rd.forward(request, response);
                        break;
                }

            } else {
                request.setAttribute("msgError", "Usuário e/ou senha incorreto");
                rd = request.getRequestDispatcher("/views/autenticacao/formLogin.jsp");
                rd.forward(request, response);

            }
        }
    }

}
