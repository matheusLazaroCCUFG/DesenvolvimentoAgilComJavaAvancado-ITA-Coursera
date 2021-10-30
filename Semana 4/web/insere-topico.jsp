<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

	<h1>Inserir tópico:</h1>
	<form action="adicionarTopico">
		Título: <input type="text" name="titulo" />
		<br><textarea rows="8" cols="50" name="conteudo" placeholder="Digite o conteúdo..."></textarea>
		<input type="hidden" value="${usuario.login }" />
		<br><input type="submit" value="adicionar" name="adicionar"/>
	</form>
</body>
</html>