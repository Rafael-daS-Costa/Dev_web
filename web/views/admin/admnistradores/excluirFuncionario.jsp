<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Excluir funcionário</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="col-sm-6 offset-3 mt-5">

                <h3>Excluir funcionário</h3>

                <%
                    String msg = (String) request.getAttribute("msg");
                    if ((msg != null) && (!msg.isEmpty())) {%>
                <div class="alert alert-danger" role="alert">
                    <%= msg%>
                </div>
                <% }%>
                <form action="/aplicacaoMVC/ExcluirFuncionarioController?acao=excluir" method="POST">
                    <div class="mb-3">
                        <label for="" class="form-label">Id</label>
                        <input
                            type="text"
                            class="form-control"
                            name="id"
                            id="id"
                            aria-describedby="helpId"
                            placeholder=""
                            required="true"
                            maxlength="50"
                            minlength="1"
                        />
                        <small id="helpId" class="form-text text-muted">Max. 50 caracteres, mín. 1</small>
                    </div>
                    <button
                        type="submit"
                        class="btn btn-primary"
                    >
                        Enviar
                    </button>
                    
                </form>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>