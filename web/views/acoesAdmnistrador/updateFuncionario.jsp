<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Atualização de funcionário</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="col-sm-6 offset-3 mt-5">

                <h3>Atualizar funcionário</h3>

                <%
                    String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) {%>
                <div class="alert alert-danger" role="alert">
                    <%= msgError%>
                </div>
                <% }%>
                <form action="/aplicacaoMVC/UpdateFuncionarioController?acao=atualiza" method="POST">
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
                    <div class="mb-3">
                        <label for="" class="form-label">Papel</label>
                        <select
                            class="form-select form-select-lg"
                            name="papel"
                            id="papel"
                        >
                            <option value="0">Admnistrador</option>
                            <option value="1">Vendedor</option>
                            <option value="2">Comprador</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="" class="form-label">Nome</label>
                        <input
                            type="text"
                            class="form-control"
                            name="nome"
                            id="nome"
                            aria-describedby="helpId"
                            placeholder=""
                            required="true"
                            maxlength="50"
                            minlength="3"
                        />
                        <small id="helpId" class="form-text text-muted">Max. 50 caracteres, mín. 3</small>
                    </div>
                    <div class="mb-3">
                        <label for="" class="form-label">CPF</label>
                        <input
                            type="text"
                            class="form-control"
                            name="cpf"
                            id="cpf"
                            aria-describedby="helpId"
                            placeholder=""
                            required="true"
                            maxlength="14"
                            minlength="3"
                        />
                        <small id="helpId" class="form-text text-muted">Max 14 caracteresm, min 3</small>
                    </div>
                    <div class="mb-3">
                        <label for="" class="form-label">Senha</label>
                        <input
                            type="text"
                            class="form-control"
                            name="senha"
                            id="senha"
                            aria-describedby="helpId"
                            placeholder=""
                            required="true"
                            maxlength="10"
                            minlength="3"
                        />
                        <small id="helpId" class="form-text text-muted">Max 10 caracteres, min 3</small>
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