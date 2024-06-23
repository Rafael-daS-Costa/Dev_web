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

@WebServlet(name = "AdmnistradoresController", urlPatterns = {"/admin/admnistrador/AdmnistradoresController"})
public class AdmnistradoresController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parametro ação indicando o que fazer
        String acao = (String) request.getParameter("acao");
        Funcionarios admnistrador = new Funcionarios();
        FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
        RequestDispatcher rd;
        int id;
        switch (acao) {
            case "ListarAdmnistrador":
                List<Funcionarios> listaAdmnistradores = funcionariosDAO.getAll().stream().filter(f -> f.getPapel().equals("0")).collect(Collectors.toList());
                ArrayList<Funcionarios> arrayListAdmnistrador = new ArrayList<Funcionarios>(listaAdmnistradores);
                request.setAttribute("listaAdmnistrador", arrayListAdmnistrador);

                rd = request.getRequestDispatcher("/views/admin/admnistradores/listaAdmnistradores.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
                id = Integer.parseInt(request.getParameter("id"));
                admnistrador = funcionariosDAO.get(id);

                request.setAttribute("admnistrador", admnistrador);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/admnistradores/formAdmnistradores.jsp");
                rd.forward(request, response);
                break;

            case "Excluir":

                // get parametro ação indicando sobre qual funcionarios será a ação
                id = Integer.parseInt(request.getParameter("id"));
                admnistrador = funcionariosDAO.get(id);

                request.setAttribute("admnistrador", admnistrador);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/admnistradores/formAdmnistradores.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("admnistrador", admnistrador);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/admnistradores/formAdmnistradores.jsp");
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
            Funcionarios admnistrador = new Funcionarios();
            switch (btEnviar) {
                case "Alterar":
                    admnistrador = funcionariosDAO.get(id);
                case "Excluir":
                    try {
                    admnistrador = funcionariosDAO.get(id);


                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de usuario");
                }
                break;
            }

            request.setAttribute("admnistrador", admnistrador);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/admin/admnistradores/formAdmnistradores.jsp");
            rd.forward(request, response);

        } else {
            
             Funcionarios admnistradores = new Funcionarios(id, nome, cpf, senha, papel);
             funcionariosDAO = new FuncionariosDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        funcionariosDAO.insert(admnistradores);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        funcionariosDAO.update(admnistradores);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        funcionariosDAO.delete(id);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                request.setAttribute("link", "/aplicacaoMVC/admin/admnistrador/AdmnistradoresController?acao=ListarAdmnistrador");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de usuario");
            }
        }
    }

}
