<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style>
        <%@include file="/WEB-INF/jsp/css/login-style.css" %>
    </style>
</head>
<body>

<div class="container">
    <header class="header">
        <h1 id="title" class="text-center">Login</h1>
        <h3 style="color: blanchedalmond" class="text-center">
            ${message}
        </h3>
    </header>
    <form method="post" action="<%=request.getContextPath()%>/login/doLogin">

        <div class="form-group">
            <label id="username-label" for="username">Username</label>
            <input
                    type="text"
                    name="username"
                    id="username"
                    class="form-control"
                    placeholder="Digite o seu username"
                    required
            />
        </div>
        <div class="form-group">
            <label id="password-label" for="senha">Senha</label>
            <input
                    type="password"
                    name="senha"
                    id="senha"
                    class="form-control"
                    placeholder="Digite a sua senha"
                    required
            />
        </div>
        <div class="form-group">
            <input id="submit" class="submit-button" type="submit" value="Entrar">
        </div>
        <a href="<%=request.getContextPath()%>/usuario/create">
            <h3 class="text-center">Registrar</h3>
        </a>
    </form>
</div>
</body>
</html>