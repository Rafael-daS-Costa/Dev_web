package controller.admin.admnistrador;

import entidade.Funcionarios;
import interfaces.ValidaDados;
import java.io.IOException;
import java.util.ArrayList;
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
public class AdmnistradoresController extends HttpServlet implements ValidaDados {

    FuncionariosDAO funcionariosDAO = new FuncionariosDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parametro ação indicando o que fazer
        String acao = (String) request.getParameter("acao");
        String escolha = (String) request.getParameter("escolha");
        String papelChar;
        if (request.getParameter("papel") == null) {
            papelChar = getPapelChar(escolha);
        }
        else {
            papelChar = request.getParameter("papel");
        }
        Funcionarios admnistrador = new Funcionarios();
        RequestDispatcher rd;
        int id;

        if (acao.equals("ListarAdmnistrador")) {
            List<Funcionarios> listaAdmnistradores = funcionariosDAO.getAll().stream().filter(f -> f.getPapel().equals(papelChar)).collect(Collectors.toList());
            ArrayList<Funcionarios> arrayListAdmnistrador = new ArrayList<>(listaAdmnistradores);
            request.setAttribute("listaAdmnistrador", arrayListAdmnistrador);
            rd = request.getRequestDispatcher("/views/admin/admnistradores/listaAdmnistradores.jsp");
        }
        else {
            request.setAttribute("admnistrador", admnistrador);
            if (acao.equals("Alterar") || acao.equals("Excluir")) {
                id = Integer.parseInt(request.getParameter("id"));
                admnistrador = funcionariosDAO.get(id);
            }
            request.setAttribute("admnistrador", admnistrador);
            request.setAttribute("msgError", "");
            request.setAttribute("acao", acao);
            request.setAttribute("papelFuncionario", getPapelChar(escolha));

            rd = request.getRequestDispatcher("/views/admin/admnistradores/formFuncionarios.jsp");
        }

        request.setAttribute("escolha", escolha);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        String papel = request.getParameter("papel");
        String btEnviar = request.getParameter("btEnviar").split(" ")[0];
        String escolha = request.getParameter("btEnviar").split(" ")[1];
        RequestDispatcher rd;
        
        request.setAttribute("acao", btEnviar);
        request.setAttribute("escolha", escolha);
        request.setAttribute("papelFuncionario", getPapelChar(escolha));

        if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty() || papel.isEmpty()) {
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
            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/admin/admnistradores/formFuncionarios.jsp");
            rd.forward(request, response);

        } else {
            
             Funcionarios admnistradores = new Funcionarios(id, nome, cpf, senha, papel);
             funcionariosDAO = new FuncionariosDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        // Tem número no nome
                        if (!nomeEhValido(nome)) {
                            request.setAttribute("msgError", "Foram inseridos caracteres inválidos no nome");
                            request.setAttribute("admnistrador", new Funcionarios());
                            rd = request.getRequestDispatcher("/views/admin/admnistradores/formFuncionarios.jsp");
                            rd.forward(request, response);
                            break;
                        }
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
                
                request.setAttribute("listaAdmnistrador", getFuncionarios(papel));
                escolha = request.getParameter("btEnviar").split(" ")[1];
                request.setAttribute("escolha", escolha);
                request.setAttribute("acao", "ListarAdmnistrador");
                request.setAttribute("papelFuncionario", getPapelChar(escolha));
                request.setAttribute("link", "/aplicacaoMVC/admin/admnistrador/AdmnistradoresController?acao=ListarAdmnistrador&escolha=" + escolha);
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de usuario");
            }
        }
    }

    private String getPapelChar(String escolha) {
        switch (escolha) {
            case "admnistradores":
                return "0";
            case "vendedores":
                return "1";
            default:
                return "2";
        }
    }

    private ArrayList<Funcionarios> getFuncionarios(String papel) {
        List<Funcionarios> listaAdmnistradores = this.funcionariosDAO.getAll().stream().filter(f -> f.getPapel().equals(papel)).collect(Collectors.toList());  // Filtra apenas admnistradores
        return new ArrayList<>(listaAdmnistradores);
    }

}
