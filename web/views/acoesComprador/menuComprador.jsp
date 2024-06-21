<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Menu do Comprador</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="col-sm-6 offset-3 mt-5">

                <h3>Menu do comprador</h3>
                <p>Selecione a operação que deseja efetuar.</p>
                <%
                    String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) {%>
                <div class="alert alert-danger" role="alert">
                    <%= msgError%>
                </div>
                <% }%>
                <a
                    name="cadastro_categoria"
                    id="cadastro_categoria"
                    class="btn btn-primary"
                    href="/cadastraCategoria.jsp"
                    role="button"
                    >Cadastro de categoria</a
                >
                <a
                    name="excluir_categoria"
                    id="excluir_categoria"
                    class="btn btn-primary"
                    href="cadastraFornecedor.jsp"
                    role="button"
                    >Excluir categoria</a
                >
                <a
                    name="cadastro_fornecedor"
                    id="cadastro_fornecedor"
                    class="btn btn-primary"
                    href="cadastraFornecedor.jsp"
                    role="button"
                    >Cadastro de fornecedor</a
                >
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>