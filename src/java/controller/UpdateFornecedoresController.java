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
import java.util.Map;

@WebServlet(name = "UpdateFornecedoresController", urlPatterns = {"/UpdateFornecedoresController"})
public class UpdateFornecedoresController extends HttpServlet {
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/acoesComprador/updateFornecedores.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        // pegando os parâmetros do request
        Map<String, String[]> parametersMap = request.getParameterMap();
        //String id_fornecedor = request.getParameter("id_fornecedor");
        String nome = request.getParameter("nome");

        if (parametersMap.values().stream().anyMatch(parameterArray -> parameterArray[0].isEmpty())) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "id da fornecedor está vazio");
            rd = request.getRequestDispatcher("/views/acoesComprador/updateFornecedores.jsp");
            rd.forward(request, response);

        } else {
            Fornecedores fornecedor = new Fornecedores(Integer.parseInt(parametersMap.get("id")[0]), 
                    parametersMap.get("razao_social")[0], parametersMap.get("cnpj")[0], 
                    parametersMap.get("endereco")[0], parametersMap.get("bairro")[0], 
                    parametersMap.get("cidade")[0], parametersMap.get("uf")[0], 
                    parametersMap.get("cep")[0], parametersMap.get("telefone")[0], 
                    parametersMap.get("email")[0]);
            FornecedoresDao fornecedorsDao = new FornecedoresDao();
            fornecedorsDao.update(fornecedor);
        }
    }
}
