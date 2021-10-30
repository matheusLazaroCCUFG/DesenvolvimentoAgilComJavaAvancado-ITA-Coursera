<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mostrar tópico</title>
</head>
<body>

	<h2>Tópico:</h2>
	Título: ${topico.titulo }<br>
	Autor:  ${topicoUsuario.nome }<br>
	Conteúdo:<br>
	${topico.conteudo }
	<br>
        <br>
	
	<h3>Comentários:</h3>
		<c:forEach var="list" items="${lista }" varStatus="id">
		<p style="background-color:#${id.count % 2 == 0 ? 'daa520' : 'add8e6' }">
			Autor: ${list.key}<br>
			Comentario:<br>
			${list.value }<br><br>
		</p>
		</c:forEach>
	
	<h4>Adicionar Comentário:</h4>
	<form action="adicionarComentario">
		<br><textarea rows="8" cols="50" name="comentario" placeholder="Digite o conteúdo..."></textarea>
		<input type="hidden" name="login" value="${usuario.login }"/>
		<input type="hidden" name="id_topico" value="${topico.id }"/>
		<br><input type="submit" value="adicionar"/>
	</form>
	
	<form action="topicos"><input type="submit" value="Retornar aos tópicos"/></form>

</body>
</html>