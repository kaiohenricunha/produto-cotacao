<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style>
        <%@include file="/WEB-INF/jsp/css/edit-style.css" %>
    </style>
</head>


<div class="container">
    <header class="header">
        <h1 id="title" class="text-center">Editar Usuário</h1>
        <h3 style="color: blanchedalmond" class="text-center">
            ${message}
        </h3>
    </header>
    <form method="POST" action="<%=request.getContextPath()%>/usuario/edit">
        <div class="form-group">
            <input type="hidden" name="id" value="${usuario.id}">

            <label id="name-label" for="nome">Nome</label>
            <input
                    type="text"
                    name="nome"
                    id="nome"
                    class="form-control"
                    value="${usuario.nome}"
            />
        </div>
        <div class="form-group">
            <label id="email-label" for="email">Email</label>
            <input
                    type="text"
                    name="email"
                    id="email"
                    class="form-control"
                    value="${usuario.email}"
            />
        </div>
        <div class="form-group">
            <label id="username-label" for="username">Username</label>
            <input
                    type="text"
                    name="username"
                    id="username"
                    class="form-control"
                    value="${usuario.username}">
        </div>
        <div class="form-group">
            <label id="password-label" for="senha">Senha</label>
            <input
                    type="password"
                    name="senha"
                    id="senha"
                    class="form-control"
                    value="${usuario.senha}"
            />
        </div>
        <div class="form-group">
            <input id="submit" class="submit-button" type="submit" value="Salvar">
        </div>
        <a href="<%=request.getContextPath()%>/usuario/list">
            <h3 class="text-center">Listar Usuários Cadastrados</h3>
        </a>
    </form>
</div>
</body>
</html>