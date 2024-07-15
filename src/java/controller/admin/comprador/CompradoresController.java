/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin.comprador;

import entidade.Compras;
import entidade.Fornecedores;
import interfaces.ValidaDados;

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
@WebServlet(name = "CompradoresController", urlPatterns = {"/admin/comprador/CompradoresController"})
public class CompradoresController extends HttpServlet implements ValidaDados {
    FornecedoresDao fornecedoresDao = new FornecedoresDao();
    ComprasDAO comprasDao = new ComprasDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parametro ação indicando o que fazer
        String acao = (String) request.getParameter("acao");

        Fornecedores fornecedor = new Fornecedores();
        
        Compras compra = new Compras();
        RequestDispatcher rd;
        int id;
        switch (acao) {
            case "Alterar":
                id = Integer.parseInt(request.getParameter("id"));
                fornecedor = fornecedoresDao.get(id);
                compra = comprasDao.get(id);

                request.setAttribute("fornecedor", fornecedor);
                request.setAttribute("acao", acao);
                request.setAttribute("msgError", "");

                rd = request.getRequestDispatcher("/views/admin/compradores/formCompradores.jsp");
                rd.forward(request, response);
                break;

            case "Excluir":

                // get parametro ação indicando sobre qual funcionarios será a ação
                id = Integer.parseInt(request.getParameter("id"));
                fornecedor = fornecedoresDao.get(id);

                request.setAttribute("fornecedor", fornecedor);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/compradores/formCompradores.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("fornecedor", fornecedor);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);
                rd = request.getRequestDispatcher("/views/admin/compradores/formCompradores.jsp");
                rd.forward(request, response);
        }

    }
    
    @Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String razao_social = request.getParameter("razao_social");
        String cnpj = request.getParameter("cnpj");
        String endereco = request.getParameter("endereco");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
        String cep = request.getParameter("cep");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String btEnviar = request.getParameter("btEnviar");
        
        RequestDispatcher rd;

       if (razao_social.isEmpty() || cnpj.isEmpty() || endereco.isEmpty() || bairro.isEmpty() || cidade.isEmpty() || uf.isEmpty() ||
               cep.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            Fornecedores fornecedor = new Fornecedores();
            switch (btEnviar) {
                case "Alterar":
                    fornecedor = fornecedoresDao.get(id);
                case "Excluir":
                    try {
                    fornecedor = fornecedoresDao.get(id);


                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de usuario");
                }
                break;
            }

            request.setAttribute("fornecedor", fornecedor);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/admin/compradores/formCompradores.jsp");
            rd.forward(request, response);

        } else {
            Fornecedores fornecedor = new Fornecedores(id, razao_social, cnpj, endereco, bairro, cidade, uf, cep, telefone, email);
            
            try {
                request.setAttribute("fornecedor", fornecedor);
                request.setAttribute("acao", btEnviar);
                request.setAttribute("listaFornecedor", getFornecedores());
                request.setAttribute("link", "/aplicacaoMVC/admin/comprador/EscolhaListaController?escolha=fornecedores");
                switch (btEnviar) {
                    case "Incluir":
                        // Valida UF
                        if (!ufEhValida(uf)) {
                            request.setAttribute("msgError", "UF não existe");

                            rd = request.getRequestDispatcher("/views/admin/compradores/formCompradores.jsp");
                            rd.forward(request, response);
                            break;
                        }
                        fornecedoresDao.insert(fornecedor);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        if (!ufEhValida(uf)) {
                            request.setAttribute("msgError", "UF não existe");

                            rd = request.getRequestDispatcher("/views/admin/compradores/formCompradores.jsp");
                            rd.forward(request, response);
                            break;
                        }
                        fornecedoresDao.update(fornecedor);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        fornecedoresDao.delete(id);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }
                
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de usuario");
            }
       }
    }
    
    private ArrayList<Fornecedores> getFornecedores() {
        List<Fornecedores> listaFornecedores = this.fornecedoresDao.getAll();
        return new ArrayList<Fornecedores>(listaFornecedores);
    }
}
