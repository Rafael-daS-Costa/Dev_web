package controller.admin.comprador;

import entidade.Compras;
import interfaces.ValidaIdFuncionario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ComprasDAO;
import model.FornecedoresDao;

/**
 *
 * @author rafael
 */
@WebServlet(name = "ComprasController", urlPatterns = {"/admin/comprador/ComprasController"})
public class ComprasController extends HttpServlet implements ValidaIdFuncionario {
    ComprasDAO comprasDao = new ComprasDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parametro ação indicando o que fazer
        String acao = (String) request.getParameter("acao");
        Compras compra = new Compras();
        RequestDispatcher rd;
        int id;
        switch (acao) {
            case "Alterar":
                id = Integer.parseInt(request.getParameter("id"));
                compra = comprasDao.get(id);

                request.setAttribute("compra", compra);
                request.setAttribute("acao", acao);
                request.setAttribute("msgError", "");

                rd = request.getRequestDispatcher("/views/admin/compradores/formCompras.jsp");
                rd.forward(request, response);
                break;

            case "Excluir":

                // get parametro ação indicando sobre qual funcionarios será a ação
                id = Integer.parseInt(request.getParameter("id"));
                compra = comprasDao.get(id);

                request.setAttribute("compra", compra);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/compradores/formCompras.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("compra", compra);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);
                rd = request.getRequestDispatcher("/views/admin/compradores/formCompras.jsp");
                rd.forward(request, response);
        }

    }
    
    @Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade_compra"));
        String data = request.getParameter("data_compra");
        int valor = Integer.parseInt(request.getParameter("valor"));
        int fornecedor = Integer.parseInt(request.getParameter("fornecedor"));
        int produto = Integer.parseInt(request.getParameter("produto"));
        int funcionario = Integer.parseInt(request.getParameter("funcionario"));
        String btEnviar = request.getParameter("btEnviar");
        
        RequestDispatcher rd;
 
       if (quantidade == 0 || data.isEmpty() || valor == 0 || fornecedor == 0 || produto == 0 || funcionario == 0) {
            Compras compra = new Compras();
            switch (btEnviar) {
                case "Alterar":
                    compra = comprasDao.get(id);
                case "Excluir":
                    try {
                    compra = comprasDao.get(id);


                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de usuario");
                }
                break;
            }

            request.setAttribute("compra", compra);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos. Não deve haver também quantidade e valor menores ou iguais a zero.");

            rd = request.getRequestDispatcher("/views/admin/compradores/formCompras.jsp");
            rd.forward(request, response);

        } else {
           Compras compra = new Compras();
            if (quantidade < 0 || valor < 0) {
                request.setAttribute("compra", compra);
                request.setAttribute("acao", btEnviar);

                request.setAttribute("msgError", "Valor e quantidade não podem ser menores ou iguais a zero.");

                rd = request.getRequestDispatcher("/views/admin/compradores/formCompras.jsp");
                rd.forward(request, response);
            }
            Compras compras = new Compras(id, quantidade, data, valor, fornecedor, produto, funcionario);
            
            try {
                switch (btEnviar) {
                    case "Incluir":
                        comprasDao.insert(compras);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        comprasDao.update(compras);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        comprasDao.delete(id);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }
                
                request.setAttribute("listaCompras", getCompras());
                request.setAttribute("link", "/aplicacaoMVC/admin/comprador/EscolhaListaController?escolha=compras");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de usuario");
            }
       }
    }
    
    private ArrayList<Compras> getCompras() {
        List<Compras> listaCompras = this.comprasDao.getAll();
        return new ArrayList<Compras>(listaCompras);
    }
}
