<%@page import="entidade.Funcionarios"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista de funcionários</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="mt-5">
                <h1>Funcionários</h1>

                <%
                    ArrayList<Funcionarios> listaFuncionarios = (ArrayList<Funcionarios>) request.getAttribute("listaFuncionarios");
                    for (Funcionarios item : listaFuncionarios){%>

                        <div class="card mb-2 col-sm-6">
                            <div class="card-body">
                                <strong>Id:</strong> <%= item.getId()%><br>
                                <strong>Nome:</strong> <%= item.getNome()%><br>
                                <strong>CPF</strong> <%= item.getCpf()%>
                                <strong>Senha</strong> <%= item.getSenha()%>
                                <strong>Papel</strong> <%= item.getPapel()%>
                            </div>
                        </div>

                <%  }%>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

