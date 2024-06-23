<%@page import="entidade.Funcionarios"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista Funcionarios</title>
         <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">

    </head>
    <body>
        <div class="container">
             <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">

                <h1>Área Restrita</h1>
                <h2>Lista de Funcionarios</h2>

                <a href="/aplicacaoMVC/admin/AdmnistradoresController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Descrição</th>
                                <th scope="col">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Funcionarios> listaFuncionarios = (ArrayList<Funcionarios>) request.getAttribute("listaFuncionario");

                                for (Funcionarios funcionario : listaFuncionarios) {
                                    out.println("<tr>");
                                    out.println("<th>" + funcionario.getId() + "</th>");
                                    out.println("<td>" + funcionario.getNome() + "</td>");
                                    %>
                            <td>
                            <a href="/aplicacaoMVC/admin/AdmnistradoresController?acao=Alterar&id=<%=funcionario.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="/aplicacaoMVC/admin/AdmnistradoresController?acao=Excluir&id=<%=funcionario.getId()%>" class="btn btn-danger">Excluir</a></td>
                            
                            <%   out.println("</tr>");
                                }
                            %>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>

    </body>
</html>

