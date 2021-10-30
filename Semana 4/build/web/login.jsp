<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

	<h1>Faça login no sistema:</h1>
	<form action="login">
		Login: <input type="text" name="login"/>
		Senha: <input type="password" name="senha" />
		<input type="submit" value="Fazer login" />
		<br>
		${erro }
	</form>
	
	<br>
	<h3>Cadastrar-se</h3>
	<form action="cadastro.jsp">
		<input type="submit" value="Novo usuário"/>
	</form>
</body>
</html>