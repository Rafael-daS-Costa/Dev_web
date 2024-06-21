package controller;

import entidade.Fornecedores;
import model.FornecedoresDao;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastraFornecedorController", urlPatterns = {"/CadastraFornecedorController"})
public class CadastraFornecedorController extends HttpServlet {
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/acoesComprador/cadastraFornecedor.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        // pegando os parâmetros do request
        String razao_social = request.getParameter("razao_social");
        String cnpj = request.getParameter("cnpj");
        String endereco = request.getParameter("endereco");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
        String cep = request.getParameter("cep");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");

        if (razao_social.isEmpty() || cnpj.isEmpty() || endereco.isEmpty() || bairro.isEmpty() || cidade.isEmpty() || uf.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "Algum dos campos está vazio");
            rd = request.getRequestDispatcher("/views/acoesComprador/cadastraFornecedor.jsp");
            rd.forward(request, response);

        } else {
            Fornecedores fornecedor = new Fornecedores(razao_social, cnpj, endereco, bairro, cidade, uf, cep,telefone, email);
            FornecedoresDao fornecedoresDao = new FornecedoresDao();
            fornecedoresDao.insert(fornecedor);
        }
    }
}
