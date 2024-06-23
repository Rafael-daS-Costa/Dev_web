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
        <title>Lista Admnistradores</title>
         <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">

    </head>
    <body>
        <div class="container">
             <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">

                <h1>Área Restrita</h1>
                <h2>Lista de Admnistradores</h2>

                <a href="/aplicacaoMVC/admin/admnistrador/AdmnistradoresController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Senha</th>
                                <th scope="col">Papel</th>
                                <th scope="col">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Funcionarios> listaAdmnistradores = (ArrayList<Funcionarios>) request.getAttribute("listaAdmnistrador");

                                for (Funcionarios admnistrador : listaAdmnistradores) {
                                    out.println("<tr>");
                                    out.println("<th>" + admnistrador.getId() + "</th>");
                                    out.println("<td>" + admnistrador.getNome() + "</td>");
                                    out.println("<td>" + admnistrador.getCpf() + "</td>");
                                    out.println("<td>" + admnistrador.getSenha() + "</td>");
                                    out.println("<td>" + admnistrador.getPapel() + "</td>");
                                    %>
                            <td>
                            <a href="/aplicacaoMVC/admin/admnistrador/AdmnistradoresController?acao=Alterar&id=<%=admnistrador.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="/aplicacaoMVC/admin/admnistrador/AdmnistradoresController?acao=Excluir&id=<%=admnistrador.getId()%>" class="btn btn-danger">Excluir</a></td>
                            
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

