<%@page import="entidade.Vendas"%>
<%@page import="controller.admin.admnistrador.groupedVendas.GroupedVendas"%>
<%@page import="entidade.Produtos"%>
<%@page import="model.ProdutosDao"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Relatório de Vendas</title>
         <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">
                <h3>Relatório de Vendas: </h3>
                    <% 
                    ArrayList<Vendas> listaVendas = (ArrayList<Vendas>) request.getAttribute("listaVendas");
                    ArrayList<GroupedVendas> listaVendasAgrupadas = (ArrayList<GroupedVendas>) request.getAttribute("listaVendasAgrupadas");
                    int listVendasRangeStart = 0;
                        for (GroupedVendas gv : listaVendasAgrupadas) {
                            out.println("<div class='table-responsive'>");
                            out.println("<table class='table table-hover' style='margin-bottom: 50px;'>");
                            out.print("<thead>\n<tr>\n");
                            out.println("<th>Id da Venda</th>");
                            out.println("<th>Id do Produto</th>");
                            out.println("<th>Nome do Produto</th>");
                            out.println("<th>Valor</th>");
                            out.println("</tr>\n</thead>\n<tbody>");

                            for (; listVendasRangeStart < listaVendas.size(); listVendasRangeStart++) {
                                Vendas vendaAtual = listaVendas.get(listVendasRangeStart);
                                if (vendaAtual.getId_produto() != gv.getKey()) {
                                    break;
                                }
                                out.println("<tr>");
                                out.println("<td scope='col'>" + vendaAtual.getId() + "</td>");
                                out.println("<td scope='col'>" + vendaAtual.getId_produto() + "</td>");
                                out.println("<td scope='col'>" + new ProdutosDao().get(vendaAtual.getId_produto()).getNome_produto() + "</td>");
                                out.println("<td scope='col'>" + vendaAtual.getValor_venda() + "</td>");
                                out.println("</tr>");
                            }
                            out.println("<tr>");
                            for (int i = 0; i < 3; i++) {
                                out.println("<td scope='col' style='border-top: 1px solid black;'></td>");
                            }
                            out.println("<td scope='col' style='border-top: 1px solid black;'>" + gv.getTotal() + "</td>");
                            out.println("</tr>\n</tbody>\n</table>\n</div>");
                        }
                    %>
            </div>
        </div>
       <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
