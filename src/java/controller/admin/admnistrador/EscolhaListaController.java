package controller.admin.admnistrador;

import entidade.Funcionarios;
import entidade.Produtos;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.admin.admnistrador.groupedVendas.GroupedVendas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import model.FuncionariosDAO;
import model.ProdutosDao;
import model.VendasDAO;
import entidade.Vendas;

@WebServlet(name = "EscolhaListaController", urlPatterns = {"/admin/admnistrador/EscolhaListaController"})
public class EscolhaListaController extends HttpServlet {
    RequestDispatcher rd;
    FuncionariosDAO funcionariosDAO = new FuncionariosDAO();

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String escolha  = request.getParameter("escolha");
        String proximaPagina = "/views/admin/admnistradores/listaAdmnistradores.jsp";

        String papelFuncionario = null;
        request.setAttribute("escolha", escolha);
        switch(escolha) {
            case "admnistradores":
                papelFuncionario = "0";
                break;
            case "vendedores":
                papelFuncionario = "1";
                break;
            case "relatorioProdutos":
                proximaPagina = "/views/admin/admnistradores/relatorioProdutos.jsp";
                request.setAttribute("listaProdutos", new ProdutosDao().getAll());
                break;
            case "relatorioVendas":
                proximaPagina = "/views/admin/admnistradores/relatorioVendas.jsp";
                ArrayList<Vendas> vendasList = new VendasDAO().getAll();
                ArrayList<GroupedVendas> groupedVendasList = new ArrayList<>();
                groupedVendasList.add(new GroupedVendas(vendasList.get(0)));
                for (Vendas venda : vendasList.subList(1, vendasList.size())) {
                    boolean foundProductId = false;
                    for (GroupedVendas groupedVenda: groupedVendasList) {
                        if (groupedVenda.getKey() == venda.getId_produto()) {
                            groupedVenda.addVenda(venda);
                            foundProductId = true;
                            break;
                        }
                    }
                    if (!foundProductId)
                        groupedVendasList.add(new GroupedVendas(venda));
                }
                groupedVendasList.sort(Comparator.comparing(GroupedVendas::getKey));
                vendasList.sort(Comparator.comparing(Vendas::getId_produto));
                request.setAttribute("listaVendas", vendasList);
                request.setAttribute("listaVendasAgrupadas", groupedVendasList);
            default:
                papelFuncionario = "2";
                break;
        }
        rd = request.getRequestDispatcher(proximaPagina);
        if (papelFuncionario != null) request.setAttribute("listaAdmnistrador", getFuncionarios(papelFuncionario));
        rd.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post");
    }
    

    private ArrayList<Funcionarios> getFuncionarios(String papel) {
        List<Funcionarios> listaAdmnistradores = this.funcionariosDAO.getAll().stream().filter(f -> f.getPapel().equals(papel)).collect(Collectors.toList());  // Filtra apenas admnistradores
        return new ArrayList<Funcionarios>(listaAdmnistradores);
    }
}
