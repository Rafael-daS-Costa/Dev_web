package controller;

import entidade.Clientes;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ClientesDAO;

/**
 *
 * @author rafael
 */
@WebServlet(name = "AtualizarVenda", urlPatterns = {"/AtualizarVenda"})
public class AtualizaVendaController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/acoesVendedor/atualizaVenda.jsp");
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        //Parâmetros do request
        int id_cliente = Integer.parseInt(request.getParameter("id"));
        String nome_user = request.getParameter("nome");
        String cpf_user = request.getParameter("cpf");
        String endereco_user = request.getParameter("endereco");
        String bairro_user = request.getParameter("bairro");
        String cidade_user = request.getParameter("cidade");
        String uf_user = request.getParameter("uf");
        String cep_user = request.getParameter("cep");
        String telefone_user = request.getParameter("telefone");
        String email_user = request.getParameter("email");
        
        if (id_cliente == 0 ||
                nome_user.isEmpty() ||
                cpf_user.isEmpty() ||
                endereco_user.isEmpty() ||
                bairro_user.isEmpty() ||
                cidade_user.isEmpty() ||
                uf_user.isEmpty() ||
                cep_user.isEmpty() ||
                telefone_user.isEmpty() ||
                email_user.isEmpty()) {
            request.setAttribute("msgError", "Preencha todos os campos, por favor.");
            rd = request.getRequestDispatcher("/views/acoesVendedor/atualizaCliente.jsp");
            rd.forward(request, response);
        } else {
            Clientes clienteObtido = new Clientes(id_cliente, nome_user, cpf_user, endereco_user, bairro_user,
            cidade_user, uf_user, cep_user, telefone_user, email_user);
            ClientesDAO clienteDAO = new ClientesDAO();
            Clientes cliente = clienteDAO.get(id_cliente);
            
            rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
            request.setAttribute("msgOperacaoRealizada", "Cliente atualizado com sucesso");
            request.setAttribute("link", "/aplicacaoMVC/home");
            
             try {
                if (cliente.getId() != 0) {
                    clienteDAO.update(clienteObtido);
                } else {
                    throw new Exception("Não há esse cliente para ser atualizado");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                request.setAttribute("msgErro", "Não é possível atualizar este cliente, cheque se ele existe, por favor.");
                rd = request.getRequestDispatcher("/views/acoesVendedor/atualizaCliente.jsp");
            } finally {
                rd.forward(request, response);
             }
        }

    }
}
