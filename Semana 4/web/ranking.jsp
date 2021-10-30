<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ranking</title>
</head>
<body>

	<h1>Ranking de usuários</h1>
	<table id="2">
		<tr>
			<th>Colocação</th>
			<th>Nome</th>
			<th>Login</th>
			<th>Pontos</th>
		</tr>
		<c:forEach var="usuario" items="${usuarios }" varStatus="id">
			<tr bgcolor="#${id.count % 2 == 0 ? 'daa520' : 'add8e6' }">
				<td><c:out value="${id.count }" /></td>
				<td>${usuario.nome }</td>
				<td>${usuario.login }</td>
				<td>${usuario.pontos }</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<form action="topicos">
		<input type="submit" value="Retornar"/>
	</form>

</body>
</html>