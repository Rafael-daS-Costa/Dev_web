<%@page import="entidade.Categorias"%>
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
                <h3>Lista de Categorias: </h3>
                <%
                    ArrayList<Categorias> listaCategorias = (ArrayList<Categorias>) request.getAttribute("listaCategorias");
                    for (Categorias item : listaCategorias){%>

                            <div class="card mb-2 col-sm-6 rounded">
                                <div class="card-body">
                                    <strong>Id:</strong> <%= item.getId()%><br>
                                    <%= item.getNome_categoria() %><br>
                                    <strong>Nome:</strong> <%= item.getNome_categoria()%><br>
                                </div>
                            </div>

                <%  }%>
            </div>
        </div>
       <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>