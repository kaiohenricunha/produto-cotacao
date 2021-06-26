<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style>
        <%@include file="/WEB-INF/jsp/css/list-style.css" %>
    </style>
</head>
<body>
<center>
    <h1>
        Usuários Cadastrados
    </h1>

    <h3 style="color: blanchedalmond">
        <c:out value="Usuário autenticado com sucesso"/>
    </h3>

    <table width="100%" border="4px">
        <tr>
            <th>
                Editar
            </th>
            <th>
                Deletar
            </th>
            <th>
                Id
            </th>
            <th>
                Nome
            </th>
            <th>
                Email
            </th>
            <th>
                Username
            </th>
        </tr>
        <c:forEach var="usuario" items="${usuarioLista}">
            <tr>
                <td>
                    <c:if test="${usuarioSession != null && usuarioSession.getLoggedUser() != null && usuarioSession.getLoggedUser().id == usuario.id}">
                        <a class="blue-link"
                           href="<%=request.getContextPath()%>/usuario/edit?id=${usuario.id}">Editar</a>
                    </c:if>
                </td>
                <td>
                    <c:if test="${usuarioSession != null && usuarioSession.getLoggedUser() != null && usuarioSession.getLoggedUser().id == usuario.id}">
                        <a class="blue-link"
                           href="<%=request.getContextPath()%>/usuario/delete?id=${user.id}">Deletar</a>
                    </c:if>
                </td>
                <td>
                    <c:out value="${usuario.id}"/>
                </td>
                <td>
                    <c:out value="${usuario.nome}"/>
                </td>
                <td>
                    <c:out value="${usuario.email}"/>
                </td>
                <td>
                    <c:out value="${usuario.username}"/>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br><br>
    <a href="<%=request.getContextPath()%>/usuario/create">Adicionar Usuário</a>
    <br><br>
    <a href="<%=request.getContextPath()%>/login/logout">Sair</a>

</center>
</body>
</html>