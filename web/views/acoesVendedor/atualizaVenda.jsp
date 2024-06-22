
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vendas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <form action="/aplicacaoMVC/AtualizarVenda?acao=atualizacao" method="POST">
            <div class="mb-3">
                <label for="" class="form-label">Id Venda:</label>
                <input
                type="text"
                class="form-control"
                name="id_venda"
                id="id_venda"
                aria-describedby="helpId"
                placeholder=""
                required="true"
                maxlength="5"
                minlength="1"
                />
                <small id="helpId" class="form-text text-muted">Max 5 caracteres, mínimo 1</small>
            </div>
            <div class="mb-3">
                <label for="" class="form-label">Quantidade Venda:</label>
                <input
                type="text"
                class="form-control"
                name="quantidade_venda"
                id="quantidade_venda"
                aria-describedby="helpId"
                placeholder=""
                required="true"
                maxlength="5"
                minlength="1"
                />
                <small id="helpId" class="form-text text-muted">Max 5 caracteres, mínimo 1</small>
            </div>
            <div class="mb-3">
                <label for="" class="form-label">Data Venda:</label>
                <input
                type="text"
                class="form-control"
                name="data_venda"
                id="data_venda"
                aria-describedby="helpId"
                placeholder="aaaa-MM-dd"
                required="true"
                maxlength="10"
                minlength="10"
                />
                <small id="helpId" class="form-text text-muted">10 caracteres</small>
            </div>
            <div class="mb-3">
                <label for="" class="form-label">Valor Venda:</label>
                <input
                type="text"
                class="form-control"
                name="valor_venda"
                id="valor_venda"
                aria-describedby="helpId"
                placeholder=""
                required="true"
                maxlength="50"
                minlength="3"
                />
                <small id="helpId" class="form-text text-muted">Max 50 caracteres, min 3</small>
            </div>
            <div class="mb-3">
                <label for="" class="form-label">ID Produto:</label>
                <input
                type="text"
                class="form-control"
                name="id_produto"
                id="id_produto"
                aria-describedby="helpId"
                placeholder=""
                required="true"
                maxlength="50"
                minlength="1"
                />
                <small id="helpId" class="form-text text-muted">Max 50 caracteres, min 1</small>
            </div>
            <div class="mb-3">
                <label for="" class="form-label">ID do cliente:</label>
                <input
                type="text"
                class="form-control"
                name="id_cliente"
                id="id_cliente"
                aria-describedby="helpId"
                placeholder=""
                required="true"
                maxlength="50"
                minlength="1"
                />
                <small id="helpId" class="form-text text-muted">Max 50 caracteres, min 1</small>
            </div>
            <div class="mb-3">
                <label for="" class="form-label">ID do funcionário: </label>
                <input
                type="text"
                class="form-control"
                name="id_funcionario"
                id="id_funcionario"
                aria-describedby="helpId"
                placeholder=""
                required="true"
                maxlength="50"
                minlength="1"
                />
                <small id="helpId" class="form-text text-muted">Max 50 caracteres, min 1</small>
            </div>
            <button
                type="submit"
                class="btn btn-primary"
            >
                Enviar
            </button>
        </form>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>
</body>