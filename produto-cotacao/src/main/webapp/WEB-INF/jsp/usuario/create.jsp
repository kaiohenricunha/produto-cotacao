<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style>
        <%@include file="/" %>
    </style>
</head>
<body>
<div class="container">
    <header class="header">
        <h1 id="title" class="text-center">Cadastro</h1>
        <p id="description" class="description text-center">
            Por favor, preencha o formulário abaixo
        </p>
        <h3 style="color: blanchedalmond" class="text-center">
            ${mensagem}
        </h3>
    </header>
    <form id="survey-form" method="post" action="<%=request.getContextPath()%>/usuario/create">
        <div class="form-group">
            <label id="name-label" for="name">Nome</label>
            <input
                    type="text"
                    name="nome"
                    id="name"
                    class="form-control"
                    placeholder="Escreva o seu nome"
                    required
            />
        </div>
        <div class="form-group">
            <label id="email-label" for="email">Email</label>
            <input
                    type="text"
                    name="email"
                    id="email"
                    class="form-control"
                    placeholder="Escreva o seu email"
                    required
            />
        </div>
        <div class="form-group">
            <label id="username-label" for="username">Username</label>
            <input
                    type="text"
                    name="username"
                    id="username"
                    class="form-control"
                    placeholder="Escreva o seu username"
                    required
            />
        </div>
        <div class="form-group">
            <label id="password-label" for="password">Senha</label>
            <input
                    type="password"
                    name="senha"
                    id="password"
                    class="form-control"
                    placeholder="Escreva a sua senha"
                    required
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