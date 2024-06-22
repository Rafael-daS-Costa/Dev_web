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
                <h3>Lista de Clientes: </h3>
                <%
                    ArrayList<Clientes> listaClientes = (ArrayList<Clientes>) request.getAttribute("listaClientes");
                    for (Clientes item : listaClientes){%>

                            <div class="card mb-2 col-sm-6 rounded">
                                <div class="card-body">
                                    <strong>Id:</strong> <%= item.getId()%><br>
                                    <%= item.getNome() %><br>
                                    <strong>CPF:</strong> <%= item.getCpf()%><br>
                                    <strong>Bairro:</strong> <%= item.getBairro() %><br>
                                    <strong>Cidade:</strong> <%= item.getCidade() %><br>
                                    <strong>Endereço:</strong> <%= item.getEndereco() %><br>
                                    <strong>Uf:</strong> <%= item.getUf() %>
                                    <strong>Telefone:</strong> <%= item.getTelefone()%>
                                    <strong>Email:</strong> <%= item.getEmail() %>
                                </div>
                            </div>

                <%  }%>
            </div>
        </div>
       <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>