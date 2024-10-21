<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
    // Redireccionar directamente a la acción cargarClientes para mostrar la tabla actualizada
    response.sendRedirect("cargarClientes.action");
%>
</body>
</html>