<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Olá ${usuario.nome }</h1>
	<h2>Tópicos do fórum</h2>
	<table id="tabela">
		<tr>
			<th>Usuario</th>
			<th>Tópico</th>
		</tr>
		<c:forEach var="topico" items="${topicos }" varStatus="id">
			<tr bgcolor="#${id.count % 2 == 0 ? 'daa520' : 'add8e6' }" id="${id.count }">
				<td>${topico.login }</td>
				<td>${topico.titulo }</td>
				<td>
					<form action="exibir">
						<input type="hidden" name="topicoID" value="${topico.id }" />
						<input type="submit" value="Ver tópico" />
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br><br>
	<form action="ranking">
		<input type="submit" value="Ranking"/>
	</form>
	<form action="insere-topico.jsp">
		<input type="submit" value="Inserir tópico" />
	</form>
	

</body>
</html>