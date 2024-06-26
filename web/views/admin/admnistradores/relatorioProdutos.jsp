<%@page import="entidade.Produtos"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Relatório</title>
         <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">
                <h3>Relatório de Produtos: </h3>
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">Id</th>
                                    <th scope="col">Nome</th>
                                    <th scope="col">Descrição</th>
                                    <th scope="col">Preço</th>
                                    <th scope="col">Quantidade</th>
                                    <th scope="col">Liberado p/ Venda</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    ArrayList<Produtos> listaProdutos = (ArrayList<Produtos>) request.getAttribute("listaProdutos");
    
                                        for (Produtos item : listaProdutos) {
                                        out.println("<tr>");
                                        out.println("<th>" + item.getId() + "</th>");
                                        out.println("<td>" + item.getNome_produto() + "</td>");
                                        out.println("<td>" + item.getDescricao() + "</td>");
                                        out.println("<td>" + item.getPreco_venda() + "</td>");
                                        out.println("<td>" + item.getQuantidade_disponivel() + "</td>");
                                        out.println("<td>" + item.getLiberado_venda() + "</td>");
                                        %>
                                
                                <%   out.println("</tr>");
                                    }
                                %>
    
                            </tbody>
                        </table>
            </div>
        </div>
       <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
