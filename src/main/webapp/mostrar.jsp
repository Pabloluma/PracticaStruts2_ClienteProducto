<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Datos de Clientes</title>
<link rel="stylesheet" href="CSS/estilos_tabla.css">
</head>
<body>
	<h1>Datos de Clientes</h1>

	<s:if test="clientes != null && !clientes.isEmpty()">
		<table class="table" border="1">
			<tr>
				<th>Teléfono</th>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Género</th>
				<th>Modificar</th>
			</tr>
			<s:iterator value="clientes" var="cliente">
				<tr>
					<!-- <td><s:property value="#cliente.idCliente" /></td>-->
					<td><s:property value="#cliente.telefono" /></td>
					<td><s:property value="#cliente.nombre" /></td>
					<td><s:property value="#cliente.apellidos" /></td>
					<td><s:property value="#cliente.genero" /></td>
					<!-- Se llama al metodo editarCliente que es el que tiene la logica de la pantalla a la que envía el boton -->
					<td id="modificar"><button><a href="<s:url action='editarCliente'><s:param name="idCliente" value="#cliente.idCliente"/></s:url>"class="botn"> <img src="images/pen.png"></a></button>
					</td>
				</tr>
			</s:iterator>
		</table>
	</s:if>

	<s:else>
		<p>No hay clientes registrados.</p>
	</s:else>

	<br />
	<a href="/PracticaStruts_ClienteProducto" class="boton_volver">Volver al formulario</a>
</body>
</html>