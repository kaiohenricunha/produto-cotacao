<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deletar Usuário</title>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/usuario/edit">
    Nome:
    <c:out value="${usuario.nome}"/>
    <input type="text" name="nome" value="${usuario.nome}">
    <br><br>
    Email:
    <input type="text" name="email" value="${usuario.email}">
    <br><br>
    <input type="submit" value="Salvar"/>
</form>

<br><br>

<form method="post" action="<%=request.getContextPath()%>/person/delete">
    <input type="hidden" name="name" value="${person.nome}">
    <input type="submit" value="Deletar"/>
</form>
</body>
</html>
