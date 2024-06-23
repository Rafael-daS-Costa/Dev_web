<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Funcionarios"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Comentário</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Funcionarios funcionarios = (Funcionarios) request.getAttribute("funcionarios");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Funcionarios</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Funcionarios</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Funcionarios</h1>");
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="/aplicacaoMVC/admin/FuncionariosController" method="POST">
                        <input type="hidden" name="id" value="<%=funcionarios.getId()%>" class="form-control">
                        <div class="mb-3">
                            <label for="descricao" class="form-label" >Descrição</label>
                            <input type="text" name="descricao" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=funcionarios.getNome()%>" class="form-control">
                        </div>
                        <div>
                            <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                            <a href="/aplicacaoMVC/admin/FuncionariosController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>