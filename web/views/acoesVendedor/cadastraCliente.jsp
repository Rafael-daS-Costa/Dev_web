<%-- 
    Document   : cadastraCliente.jsp
    Created on : 21 de jun. de 2024, 11:01:56
    Author     : rafael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Clientes</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <div
            class="container"
        >
            <form action="/aplicacaoMVC/CadastrarClientes?acao=cadastro" method="POST">
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
                        minlength="14"
                    />
                    <small id="helpId" class="form-text text-muted">14 caracteres</small>
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
        </div>
        
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>
    </body>
</html>
