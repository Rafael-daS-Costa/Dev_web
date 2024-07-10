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
        <title>Login</title>
         <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="mt-5">
                <h3>Lista de Produtos: </h3>
                <%
                    ArrayList<Produtos> listaProdutos = (ArrayList<Produtos>) request.getAttribute("listaProdutos");
                    for (Produtos item : listaProdutos) 
                        if (item.getQuantidade_disponivel() != 0 && item.getLiberado_venda().charAt(0) == 'S') {{%>

                            <div class="card mb-2 col-sm-6 rounded">
                                <div class="card-body">
                                    <strong>Número:</strong> <%= item.getId()%><br>
                                    <%= item.getNome_produto() %><br>
                                    <strong>Descrição:</strong> <%= item.getDescricao() %><br>
                                    <strong>Preço:</strong> <%= item.getPreco_venda() %>
                                    <!-- <strong>Quantidade disponível:</strong> <%= item.getQuantidade_disponivel() %> -->
                                    <!--<strong>Liberação para venda:</strong> <%= item.getLiberado_venda() %> -->
                                </div>
                            </div>
                    <%  }  %>

                <%  }%>
            </div>
        </div>
       <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
