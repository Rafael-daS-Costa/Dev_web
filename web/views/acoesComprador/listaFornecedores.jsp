<%@page import="entidade.Fornecedores"%>
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
                <h3>Lista de Fornecedores: </h3>
                <%
                    ArrayList<Fornecedores> listaFornecedores = (ArrayList<Fornecedores>) request.getAttribute("listaFornecedores");
                    for (Fornecedores item : listaFornecedores){%>

                            <div class="card mb-2 col-sm-6 rounded">
                                <div class="card-body">
                                    <strong>Id:</strong> <%= item.getId()%><br>
                                    <strong>Razão Social:</strong> <%= item.getRazao_social()%><br>
                                    <strong>CNPJ:</strong> <%= item.getCnpj()%><br>
                                    <strong>Endereço:</strong> <%= item.getEndereco()%><br>
                                    <strong>Bairro:</strong> <%= item.getBairro()%><br>
                                    <strong>Cidade:</strong> <%= item.getCidade()%><br>
                                    <strong>UF:</strong> <%= item.getUf()%><br>
                                    <strong>CEP:</strong> <%= item.getCep()%><br>
                                    <strong>Telefone:</strong> <%= item.getTelefone()%><br>
                                    <strong>Email:</strong> <%= item.getEmail()%><br>
                                </div>
                            </div>

                <%  }%>
            </div>
        </div>
       <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>