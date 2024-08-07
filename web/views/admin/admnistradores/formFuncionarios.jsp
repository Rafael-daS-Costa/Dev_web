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
        <title>Formulário de Admnistrador</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Funcionarios admnistrador = (Funcionarios) request.getAttribute("admnistrador");
                        String escolha = (String) request.getAttribute("escolha");
                        String acao = (String) request.getAttribute("acao");
                        String papelFuncionario = (String) request.getAttribute("papelFuncionario");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir " + escolha.substring(0,1).toUpperCase() + escolha.substring(1) + "</h1>");
                                break;
                            case "Alterar":
                            out.println("<h1>Alterar " + escolha.substring(0,1).toUpperCase() + escolha.substring(1) + "</h1>");
                                break;
                            case "Excluir":
                            out.println("<h1>Excluir " + escolha.substring(0,1).toUpperCase() + escolha.substring(1) + "</h1>");
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="/aplicacaoMVC/admin/admnistrador/AdmnistradoresController" method="POST">
                        <input type="hidden" name="id" value="<%=admnistrador.getId()%>" class="form-control">
                        <div class="mb-3">
                            <label for="nome" class="form-label" >Nome</label>
                            <input type="text" name="nome" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%= acao.equals("Incluir") ? "" : admnistrador.getNome()%>" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="cpf" class="form-label" >CPF</label>
                            <input type="text" name="cpf" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%= acao.equals("Incluir") ? "" : admnistrador.getCpf()%>" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="senha" class="form-label" >Senha</label>
                            <input type="text" name="senha" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%= acao.equals("Incluir") ? "" : admnistrador.getSenha()%>" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="papel" class="form-label" >Papel</label>
                            <input type="text" name="papel" placeholder="0" <%= acao.equals("Alterar") ? "" : "readonly"%> value="<%= (String) request.getAttribute("papelFuncionario")%>" class="form-control">
                        </div>
                        <div>
                            <input type="submit" name="btEnviar" value="<%=acao%> <%=escolha%>" class="btn btn-primary">
                            <a href="/aplicacaoMVC/admin/admnistrador/AdmnistradoresController?acao=ListarAdmnistrador&escolha=<%=request.getAttribute("escolha")%>" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>