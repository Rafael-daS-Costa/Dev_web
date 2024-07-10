package controller.admin.admnistrador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.admin.admnistrador.groupedVendas.GroupedVendas;
import entidade.Produtos;
import entidade.Vendas;
import model.ProdutosDao;
import model.VendasDAO;


@WebServlet(name = "RelatorioVendas", urlPatterns = {"/RelatorioVendas"})
public class RelatorioVendas extends HttpServlet {
    
    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                   ProdutosDao produtoDAO = new ProdutosDao();
            try {
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
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/admnistradores/relatorioVendas.jsp");           
                rd.forward(request, response);
                
            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar vendas");
            }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        
    }

}
