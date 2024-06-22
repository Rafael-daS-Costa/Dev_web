<%-- 
    Document   : deletaCliente
    Created on : 21 de jun. de 2024, 16:34:16
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
            <div class="mt-5">

                <%  String msgOperacaoRealizada = (String) request.getAttribute("msgErro");
                    if ((msgOperacaoRealizada != null) && (!msgOperacaoRealizada.isEmpty())) {%>

                    <h2><%= msgOperacaoRealizada%></h2>
                    
                <% }%>
            </div>
            <form action="/aplicacaoMVC/DeletarClientes?acao=cadastro" method="POST">
                <div class="mb-3">
                    <label for="" class="form-label">Id Cliente:</label>
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
        
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>
    </body>
</html>
