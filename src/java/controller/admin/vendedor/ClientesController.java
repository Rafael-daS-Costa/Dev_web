package controller.admin.vendedor;

import entidade.Clientes;
import entidade.Clientes;
import model.ClientesDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ClientesController", urlPatterns = {"/admin/vendedor/ClientesController"})
public class ClientesController extends HttpServlet {
    ClientesDAO clientesDAO = new ClientesDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parametro ação indicando o que fazer
        String acao = (String) request.getParameter("acao");
        Clientes cliente = new Clientes();
        RequestDispatcher rd;
        int id;
        switch (acao) {
            case "ListarCliente":
                ArrayList<Clientes> arrayListAdmnistrador = clientesDAO.ListaDeClientes();;
                request.setAttribute("listaClientes", arrayListAdmnistrador);

                rd = request.getRequestDispatcher("/views/admin/vendedores/listaClientes.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
                id = Integer.parseInt(request.getParameter("id"));
                cliente = clientesDAO.get(id);

                request.setAttribute("cliente", cliente);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/vendedores/formClientes.jsp");
                rd.forward(request, response);
                break;

            case "Excluir":

                // get parametro ação indicando sobre qual funcionarios será a ação
                id = Integer.parseInt(request.getParameter("id"));
                cliente = clientesDAO.get(id);

                request.setAttribute("cliente", cliente);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/vendedores/formClientes.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("cliente", cliente);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/vendedores/formClientes.jsp");
                rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String endereco = request.getParameter("endereco");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
        String cep = request.getParameter("cep");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");        
        String btEnviar = request.getParameter("btEnviar").split(",")[0];

        RequestDispatcher rd;

        if (nome.isEmpty() || cpf.isEmpty() || cpf.isEmpty() || endereco.isEmpty() || bairro.isEmpty()
        || cidade.isEmpty() || uf.isEmpty() || cep.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            Clientes cliente = new Clientes();
            switch (btEnviar) {
                case "Alterar":
                    cliente = clientesDAO.get(id);
                case "Excluir":
                    try {
                    cliente = clientesDAO.get(id);


                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de usuario");
                }
                break;
            }

            request.setAttribute("cliente", cliente);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/admin/vendedores/formClientes.jsp");
            rd.forward(request, response);

        } else {
            
             Clientes cliente = new Clientes(id, nome, cpf, endereco, bairro, cidade, uf, cep, telefone, email);
             clientesDAO = new ClientesDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        clientesDAO.insert(cliente);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        clientesDAO.update(cliente);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        clientesDAO.delete(id);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }
                
                String acao = request.getParameter("btEnviar");
                request.setAttribute("acao", "acao");
                request.setAttribute("link", "/aplicacaoMVC/admin/vendedor/ClientesController?acao=ListarCliente");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de cliente");
            } catch (Exception ex) {
                Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
