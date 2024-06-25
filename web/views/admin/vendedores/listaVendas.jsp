<%@page import="entidade.Vendas"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista Vendas</title>
         <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">

    </head>
    <body>
        <div class="container">
             <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">

                <h1>Área Restrita</h1>
                <h2>Lista de Vendas</h2>

                <a href="/aplicacaoMVC/admin/vendedor/VendasController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Quantidade de Vendas</th>
                                <th scope="col">Data da Venda</th>
                                <th scope="col">Valor da Venda</th>
                                <th scope="col">Id do Cliente</th>
                                <th scope="col">Id do Produto</th>
                                <th scope="col">Id do Funcionario</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Vendas> listaVendas = (ArrayList<Vendas>) request.getAttribute("listaVendas");

                                for (Vendas venda : listaVendas) {
                                    out.println("<tr>");
                                    out.println("<th>" + venda.getId() + "</th>");
                                    out.println("<td>" + venda.getQuantidade_venda() + "</td>");
                                    out.println("<td>" + venda.getData_venda() + "</td>");
                                    out.println("<td>" + venda.getValor_venda() + "</td>");
                                    out.println("<td>" + venda.getId_cliente() + "</td>");
                                    out.println("<th>" + venda.getId_produto() + "</th>");
                                    out.println("<td>" + venda.getId_funcionario() + "</td>");
                                    %>
                            <td>
                            <a href="/aplicacaoMVC/admin/vendedor/VendasController?acao=Alterar&id=<%=venda.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="/aplicacaoMVC/admin/vendedor/VendasController?acao=Excluir&id=<%=venda.getId()%>" class="btn btn-danger">Excluir</a></td>
                            
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

