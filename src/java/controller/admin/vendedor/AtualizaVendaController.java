package controller;

import entidade.Vendas;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.VendasDAO;

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
        int id_venda = Integer.parseInt(request.getParameter("id_venda"));
        int quantidade_venda = Integer.parseInt(request.getParameter("quantidade_venda"));
        String data_venda = request.getParameter("data_venda");
        float valor_venda = Float.parseFloat(request.getParameter("valor_venda"));
        int id_produto = Integer.parseInt(request.getParameter("id_produto"));
        int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
        int id_funcionario = Integer.parseInt(request.getParameter("id_funcionario"));
        
        if (id_venda == 0 ||
                quantidade_venda == 0 ||
                data_venda.isEmpty() ||
                valor_venda == 0.0f ||
                id_produto == 0 ||
                id_cliente == 0 ||
                id_funcionario == 0) {
            request.setAttribute("msgError", "Preencha todos os campos, por favor.");
            rd = request.getRequestDispatcher("/views/acoesVendedor/atualizaVenda.jsp");
            rd.forward(request, response);
        } else {
            Vendas vendaObtida = new Vendas(id_venda, quantidade_venda, data_venda, valor_venda, id_cliente, id_produto, id_funcionario);
            VendasDAO vendaDAO = new VendasDAO();
            Vendas venda = vendaDAO.get(id_venda);
            
            rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
            request.setAttribute("msgOperacaoRealizada", "Venda atualizada com sucesso");
            request.setAttribute("link", "/aplicacaoMVC/home");
            
             try {
                if (venda.getId() != 0) {
                    vendaDAO.update(vendaObtida);
                } else {
                    throw new Exception("Não há essa venda para ser atualizada");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                request.setAttribute("msgErro", "Não é possível atualizar esta venda, cheque se ela existe, por favor.");
                rd = request.getRequestDispatcher("/views/acoesVendedor/atualizaVenda.jsp");
            } finally {
                rd.forward(request, response);
             }
        }

    }
}
