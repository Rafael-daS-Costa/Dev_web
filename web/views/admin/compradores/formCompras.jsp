<%@page import="entidade.Compras"%>
<%@page import="entidade.Fornecedores"%>
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
                        Compras compra = (Compras) request.getAttribute("compra");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir</h1>");
                                break;
                            case "Alterar":
                            out.println("<h1>Alterar</h1>");
                                break;
                            case "Excluir":
                            out.println("<h1>Excluir</h1>");
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="/aplicacaoMVC/admin/comprador/ComprasController" method="POST">
                        <input type="hidden" name="id" value="<%=compra.getId()%>" class="form-control">
                        <div class="mb-3">
                            <label for="quantidade_compra" class="form-label" >Quantidade</label>
                            <input type="text" name="quantidade_compra" maxlength="11" minlength="1" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=compra.getQuantidade_compra() %>" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="data_compra" class="form-label" >Data</label>
                            <input type="text" name="data_compra" placeholder="aaaa-MM-dd" maxlength="10" minlength="10" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=compra.getData_compra() %>" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="valor" class="form-label" >Valor</label>
                            <input type="text" name="valor" maxlength="11" minlength="1" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=compra.getValor_compra() %>" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="fornecedor" class="form-label" >Id_fornecedor</label>
                            <input type="text" name="fornecedor" maxlength="11" minlength="1" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=compra.getId_fornecedor() %>" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="produto" class="form-label" >Id_produto</label>
                            <input type="text" name="produto" maxlength="11" minlength="1" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=compra.getId_produto() %>" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="funcionario" class="form-label" >id_funcionario</label>
                            <input type="text" name="funcionario" maxlength="11" minlength="1" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=compra.getId_funcionario() %>" class="form-control">
                        </div>
                        <div>
                            <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                            <a href="/aplicacaoMVC/admin/comprador/EscolhaListaController?escolha=compras" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>