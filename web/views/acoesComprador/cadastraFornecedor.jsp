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
            <div class="col-sm-6 offset-3 mt-5">

                <h3>Login</h3>

                <%
                    String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) {%>
                <div class="alert alert-danger" role="alert">
                    <%= msgError%>
                </div>
                <% }%>
                <form action="/aplicacaoMVC/CadastraFornecedorController?acao=login" method="POST">
                    <div class="mb-3">
                        <label for="" class="form-label">Razão-Social</label>
                        <input
                            type="text"
                            class="form-control"
                            name="razao_social"
                            id="razao_social"
                            aria-describedby="helpId"
                            placeholder=""
                            required="true"
                            maxlength="50"
                            minlength="3"
                        />
                        <small id="helpId" class="form-text text-muted">Max. 50 caracteres, mín. 3</small>
                    </div>
                    <div class="mb-3">
                        <label for="" class="form-label">CNPJ</label>
                        <input
                            type="text"
                            class="form-control"
                            name="cnpj"
                            id="cnpj"
                            aria-describedby="helpId"
                            placeholder=""
                            required="true"
                            maxlength="18"
                            minlength="3"
                        />
                        <small id="helpId" class="form-text text-muted">Max 18 caracteresm, min 3</small>
                    </div>
                    <div class="mb-3">
                        <label for="" class="form-label">Endereço</label>
                        <input
                            type="text"
                            class="form-control"
                            name="endereco"
                            id="endereco"
                            aria-describedby="helpId"
                            placeholder=""
                            required="true"
                            maxlength="50"
                            minlength="3"
                        />
                        <small id="helpId" class="form-text text-muted">Max 50 caracteres, min 3</small>
                    </div>
                    <div class="mb-3">
                        <label for="" class="form-label">Bairro</label>
                        <input
                            type="text"
                            class="form-control"
                            name="bairro"
                            id="bairro"
                            aria-describedby="helpId"
                            placeholder=""
                            required="true"
                            maxlength="50"
                            minlength="3"
                        />
                        <small id="helpId" class="form-text text-muted">Max 50 caracteres, min 3</small>
                    </div>
                    <div class="mb-3">
                        <label for="" class="form-label">Cidade</label>
                        <input
                            type="text"
                            class="form-control"
                            name="cidade"
                            id="cidade"
                            aria-describedby="helpId"
                            placeholder=""
                            required="true"
                            maxlength="50"
                            minlength="3"
                        />
                        <small id="helpId" class="form-text text-muted">Max 50 caracteres, min 3</small>
                    </div>
                    <div class="mb-3">
                        <label for="" class="form-label">UF</label>
                        <input
                            type="text"
                            class="form-control"
                            name="uf"
                            id="uf"
                            aria-describedby="helpId"
                            placeholder=""
                            required="true"
                            maxlength="2"
                            minlength="2"
                        />
                        <small id="helpId" class="form-text text-muted">2 caracteres</small>
                    </div>
                    <div class="mb-3">
                        <label for="" class="form-label">CEP</label>
                        <input
                            type="text"
                            class="form-control"
                            name="cep"
                            id="cep"
                            aria-describedby="helpId"
                            placeholder=""
                            required="true"
                            maxlength="9"
                            minlength="3"
                        />
                        <small id="helpId" class="form-text text-muted">Max 9 caracteres, min 3</small>
                    </div>
                    <div class="mb-3">
                        <label for="" class="form-label">Telefone</label>
                        <input
                            type="text"
                            class="form-control"
                            name="telefone"
                            id="telefone"
                            aria-describedby="helpId"
                            placeholder=""
                            required="true"
                            maxlength="20"
                        />
                        <small id="helpId" class="form-text text-muted">Max. 20 caracteres</small>
                    </div>
                    <div class="mb-3">
                        <label for="" class="form-label">Email</label>
                        <input
                            type="text"
                            class="form-control"
                            name="email"
                            id="email"
                            aria-describedby="helpId"
                            placeholder=""
                            required="true"
                            maxlength="50"
                        />
                        <small id="helpId" class="form-text text-muted">Max. 50 caracteres</small>
                    </div>
                    <button
                        type="submit"
                        class="btn btn-primary"
                    >
                        Enviar
                    </button>
                    
                </form>
                <form action="/aplicacaoMVC/AutenticaController?acao=login" method="POST">
                    <div class="mb-3">
                        <label for="cpf" class="form-label">CPF</label>
                        <input type="text" name="cpf" value="249.252.810-38" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="senha" class="form-label">Senha</label>
                        <input type="password" name="senha" value="123" class="form-control">
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <input type="submit" value="Enviar" class="btn btn-primary">  
                        </div>
                        <div class="col-sm-6">                                
                            <h6>Não possui acesso <a href="/aplicacaoMVC/RegistrarController">Registre-se aqui</a></h6>
                        </div>
                </form>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>