package controller.admin.admnistrador;

import entidade.Funcionarios;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.List;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FuncionariosDAO;

@WebServlet(name = "FuncionariosController", urlPatterns = {"/admin/admnistrador/FuncionariosController"})
public class AdmnistradoresController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parametro ação indicando o que fazer
        String acao = (String) request.getParameter("acao");
        Funcionarios funcionarios = new Funcionarios();
        FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
        RequestDispatcher rd;
        int id;
        switch (acao) {
            case "ListarFuncionario":
                List<Funcionarios> listaAdmnistradores = funcionariosDAO.getAll();
                //.stream().filter(f -> f.getPapel() "0").collect(Collectors.toList());
                ArrayList<Funcionarios> arrayListFuncionario = new ArrayList<Funcionarios>(listaAdmnistradores);
                request.setAttribute("listaFuncionario", arrayListFuncionario);

                rd = request.getRequestDispatcher("/views/admin/admnistradores/listaFuncionarios.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
                id = Integer.parseInt(request.getParameter("id"));
                funcionarios = funcionariosDAO.get(id);

                request.setAttribute("funcionario", funcionarios);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/admnistradores/formFuncionarios.jsp");
                rd.forward(request, response);
                break;

            case "Excluir":

                // get parametro ação indicando sobre qual funcionarios será a ação
                id = Integer.parseInt(request.getParameter("id"));
                funcionarios = funcionariosDAO.get(id);

                request.setAttribute("funcionario", funcionarios);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/admnistradores/formFuncionarios.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("funcionario", funcionarios);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/admnistradores/formFuncionarios.jsp");
                rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        String papel = request.getParameter("papel");
        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;
        FuncionariosDAO funcionariosDAO = new FuncionariosDAO();

        if (nome.isEmpty() || cpf.isEmpty() || cpf.isEmpty() || senha.isEmpty() || papel.isEmpty()) {
            Funcionarios funcionario = new Funcionarios();
            switch (btEnviar) {
                case "Alterar":
                    funcionario = funcionariosDAO.get(id);
                case "Excluir":
                    try {
                    funcionario = funcionariosDAO.get(id);


                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de usuario");
                }
                break;
            }

            request.setAttribute("funcionario", funcionario);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/admin/funcionarios/formFuncionarios.jsp");
            rd.forward(request, response);

        } else {
            
             Funcionarios funcionarios = new Funcionarios(id, nome, cpf, senha, papel);
             funcionariosDAO = new FuncionariosDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        funcionariosDAO.insert(funcionarios);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        funcionariosDAO.update(funcionarios);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        funcionariosDAO.delete(id);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                request.setAttribute("link", "/aplicacaoMVC/admin/admnistrador/FuncionariosController?acao=ListarFuncionario");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de usuario");
            }
        }
    }

}
