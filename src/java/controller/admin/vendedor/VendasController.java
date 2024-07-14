import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import entidade.Vendas;
import entidade.Funcionarios;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProdutosDao;
import entidade.Produtos;
import model.VendasDAO;

@WebServlet(name = "VendasController", urlPatterns = {"/admin/vendedor/VendasController"})
public class VendasController extends HttpServlet {
    VendasDAO vendasDAO = new VendasDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parametro ação indicando o que fazer
        String acao = (String) request.getParameter("acao");
        Vendas venda = new Vendas();
        RequestDispatcher rd;
        Funcionarios vendedor = (Funcionarios) request.getSession().getAttribute("funcionario");
        int id;
        switch (acao) {
            case "ListarVenda":
                List<Vendas> listVendas = vendasDAO.getAll().stream().filter(v -> v.getId_funcionario() == vendedor.getId()).collect(Collectors.toList());
                ArrayList<Vendas> arrayListVendas = new ArrayList<>(listVendas);
                request.setAttribute("listaVendas", arrayListVendas);

                rd = request.getRequestDispatcher("/views/admin/vendedores/listaVendas.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
                id = Integer.parseInt(request.getParameter("id"));
                venda = vendasDAO.get(id);

                request.setAttribute("venda", venda);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/vendedores/formVendas.jsp");
                rd.forward(request, response);
                break;

            case "Excluir":

                // get parametro ação indicando sobre qual funcionarios será a ação
                id = Integer.parseInt(request.getParameter("id"));
                venda = vendasDAO.get(id);

                request.setAttribute("venda", venda);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/vendedores/formVendas.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("venda", venda);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/vendedores/formVendas.jsp");
                rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String quantidade_venda = request.getParameter("quantidade_venda");
        String data_venda = request.getParameter("data_venda");
        String valor_venda = request.getParameter("valor_venda");
        String id_cliente = request.getParameter("id_cliente");
        String id_produto = request.getParameter("id_produto");
        String id_funcionario = request.getParameter("id_funcionario");
        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (quantidade_venda.isEmpty() || data_venda.isEmpty() || valor_venda.isEmpty() || id_cliente.isEmpty() || id_produto.isEmpty()
        || id_funcionario.isEmpty()) {
            Vendas venda = new Vendas();
            switch (btEnviar) {
                case "Alterar":
                    venda = vendasDAO.get(id);
                case "Excluir":
                    try {
                    venda = vendasDAO.get(id);


                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de usuario");
                }
                break;
            }

            request.setAttribute("venda", venda);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/admin/vendedores/formVendas.jsp");
            rd.forward(request, response);

        } else {
            
             Vendas venda = new Vendas(id, Integer.parseInt(quantidade_venda), data_venda, Float.parseFloat(valor_venda), Integer.parseInt(id_cliente), Integer.parseInt(id_produto), Integer.parseInt(id_funcionario));
             vendasDAO = new VendasDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        String msgOperacaoRealizada = "Inclusão realizada com sucesso";
                        ProdutosDao produtosDao = new ProdutosDao();
                        Produtos produto = produtosDao.get(venda.getId_produto());

                        if (produto.getLiberado_venda().equals("N")) {
                            msgOperacaoRealizada = "Falha na inclusão. Produto não está liberado para venda.";
                        }
                        else if (produto.getQuantidade_disponivel() <= 0) {
                            msgOperacaoRealizada = "Falha na inclusão. A quantidade do produto no estoque é 0.";
                        }
                        else {
                            vendasDAO.insert(venda);
                        }
                        request.setAttribute("msgOperacaoRealizada",msgOperacaoRealizada);
                        break;
                    case "Alterar":
                        vendasDAO.update(venda);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        vendasDAO.delete(id);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }
                
                String acao = request.getParameter("btEnviar");
                request.setAttribute("acao", "acao");
                request.setAttribute("link", "/aplicacaoMVC/admin/vendedor/VendasController?acao=ListarVenda");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de venda");
            } catch (Exception ex) {
                Logger.getLogger(VendasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}