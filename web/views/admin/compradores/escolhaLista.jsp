<%-- 
    Document   : escolhaLista.jsp
    Created on : 25 de jun. de 2024, 19:35:44
    Author     : rafael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Seleção de operações</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div
            class="row justify-content-center align-items-center g-2"
        >
            <a href="/aplicacaoMVC/admin/comprador/EscolhaListaController?escolha=compras" class="btn btn-primary">Operações de compras</a>
            <a href="/aplicacaoMVC/admin/comprador/EscolhaListaController?escolha=fornecedores" class="btn btn-primary">Operações de fornecedor</a>
        </div>
        
        
        
        
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>