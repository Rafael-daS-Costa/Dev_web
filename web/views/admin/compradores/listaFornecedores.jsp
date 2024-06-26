<%@page import="entidade.Fornecedores"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista Fornecedores</title>
         <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">

    </head>
    <body>
        <div class="container">
             <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">

                <h1>Área Restrita</h1>
                <%
                String escolha = request.getParameter("escolha"); 
                out.println("<h2>Lista de " + escolha.substring(0,1).toUpperCase() + escolha.substring(1) + "</h2>"); %>

                <a href="/aplicacaoMVC/admin/comprador/CompradoresController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Razão social</th>
                                <th scope="col">CNPJ</th>
                                <th scope="col">Cidade</th>
                                <th scope="col">Bairro</th>
                                <th scope="col">Uf</th>
                                <th scope="col">Email</th>
                                <th scope="col">Telefone</th>
                                <th scope="col">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Fornecedores> listaFornecedores = (ArrayList<Fornecedores>) request.getAttribute("listaFornecedor");

                                for (Fornecedores fornecedor : listaFornecedores) {
                                    out.println("<tr>");
                                    out.println("<th>" + fornecedor.getId() + "</th>");
                                    out.println("<td>" + fornecedor.getRazao_social() + "</td>");
                                    out.println("<td>" + fornecedor.getCnpj() + "</td>");
                                    out.println("<td>" + fornecedor.getCidade() + "</td>");
                                    out.println("<td>" + fornecedor.getBairro() + "</td>");
                                    out.println("<td>" + fornecedor.getUf() + "</td>");
                                    out.println("<td>" + fornecedor.getEmail() + "</td>");
                                    out.println("<td>" + fornecedor.getTelefone() + "</td>");
                                    %>
                            <td>
                            <a href="/aplicacaoMVC/admin/comprador/CompradoresController?acao=Alterar&id=<%=fornecedor.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="/aplicacaoMVC/admin/comprador/CompradoresController?acao=Excluir&id=<%=fornecedor.getId()%>" class="btn btn-danger">Excluir</a></td>
                            
                            <%   out.println("</tr>");
                                }
                            %>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>

    </body>
</html>

