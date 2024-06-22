<%@page import="entidade.Vendas"%>
<%@page import="entidade.Clientes"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Login</title>
         <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="mt-5">
                <h3>Lista de Vendas: </h3>
                <%
                    ArrayList<Vendas> listaVendas = (ArrayList<Vendas>) request.getAttribute("listaVendas");
                    for (Vendas item : listaVendas){%>

                            <div class="card mb-2 col-sm-6 rounded">
                                <div class="card-body">
                                    <strong>Id:</strong> <%= item.getId()%><br>
                                    <strong>Valor da venda em reais:</strong><%= item.getValor_venda() %><br>
                                    <strong>CPF:</strong> <%= item.getId_produto() %><br>
                                    <strong>Quantidade da venda:</strong> <%= item.getQuantidade_venda() %><br>
                                    <strong>Cliente:</strong> <%= item.getId_cliente() %><br>
                                    <strong>Vendedor:</strong> <%= item.getId_funcionario() %><br>
                                    <strong>Data da venda:</strong> <%= item.getData_venda() %>
                                </div>
                            </div>

                <%  }%>
            </div>
        </div>
       <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>