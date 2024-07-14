<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Seleção de operação</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div
            class="row justify-content-center align-items-center g-2" style="width: 25%; margin: auto;"
        >
            <h3 style="text-align: center; margin-bottom: 50px;">Operações de Admnistrador</h3>
            <a href="/aplicacaoMVC/admin/admnistrador/EscolhaListaController?escolha=admnistradores" class="btn btn-primary" style="margin-bottom: 5px;">Admnistradores</a>
            <a href="/aplicacaoMVC/admin/admnistrador/EscolhaListaController?escolha=vendedores" class="btn btn-primary" style="margin-bottom: 5px;">Vendedores</a>
            <a href="/aplicacaoMVC/admin/admnistrador/EscolhaListaController?escolha=compradores" class="btn btn-primary" style="margin-bottom: 5px;">Compradores</a>
            <a href="/aplicacaoMVC/admin/admnistrador/EscolhaListaController?escolha=relatorioProdutos" class="btn btn-primary" style="margin-bottom: 5px;">Relatório de Produtos</a>
            <a href="/aplicacaoMVC/admin/admnistrador/EscolhaListaController?escolha=relatorioVendas" class="btn btn-primary" style="margin-bottom: 5px;">Relatório de Vendas</a>
        </div>
        
        
        
        
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>