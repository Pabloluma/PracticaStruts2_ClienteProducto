<%@page import="org.apache.struts2.components.If"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inserta los datos</title>
<link rel="stylesheet" href="CSS/estilos_form.css">
</head>
<body>
	<!-- 	<H1 class="ok">FORMULARIO DE COMPRA</H1> -->
	<h2>Datos de compra</h2>
	<s:form action="actualiza">

		<table class="tabla1"></table>
		<fieldset>
		<!-- Hay que enviar el idCliente en un campo oculto para que se pueda actualizar el formulario -->
			<s:hidden name="cliente.idCliente" value="%{cliente.idCliente}" />
			<label class="telef">TELÉFONO:</label>
			<s:textfield value="%{cliente.telefono}" name="cliente.telefono"
				class="telefon" />
			<br /> <label class="nom">NOMBRE:</label>
			<s:textfield name="cliente.nombre" value="%{cliente.nombre}"
				class="nomb" />
			<br /> <label class="apel">APELLIDOS:</label>
			<s:textfield name="cliente.apellidos" value="%{cliente.apellidos}"
				class="apell" />


			<br />
			<div class="div">
				<label class="tipoProductos">GENERO</label> <br />
				<s:radio class="comprobante" name="cliente.genero"
					list="#{'1':'Masculino', '2':'Femenino'}" value="%{cliente.genero}" />
			</div>
			<br />

			<div class="div">
				<label class="tipoProductos">TIPO DE PRODUCTOS A COMPRAR</label> <br />
				<s:checkbox name="pedido.hard" value="%{pedido.hardware == 'Si'}"
					label="Hardware" />
				<s:checkbox name="soft" value="%{pedido.software == 'Si'}"
					label="Software" />
			</div>

			<br /> <label class="TresSeguidos">PRODUCTO</label>&nbsp;&nbsp;&nbsp;<label
				class="TresSeguidos">CANTIDAD</label>&nbsp;&nbsp;&nbsp;<label
				class="TresSeguidos"> PRECIO UNIDAD</label> <br />
			<s:select class="elemTres" value="%{pedido.producto}" name="producto"
				list="#{'1': 'MONITOR', '2':'LECTOR DVD','3':'IMPRESORA','4':'RATON','5':'TECLADO','6':'DISCO DURO'}"
				headerKey="-1" />
			<s:textfield name="cantidad" value="%{pedido.cantidad}" size="10" />
			<s:textfield name="precio" value="%{pedido.precio}" size="10" />
			<br /> <br /> <label class="tipoProductos">TIPO DE
				COMPROBANTE DE PAGO</label><br />
			<s:radio class="comprobante" value="%{pedido.comprobante}"
				name="comprobante" list="#{'1':'Ticket', '2':'Factura'}" />
			<br /> <br /> <br /> <label class="tipoProductos">OBSERVACIONES</label><br />
			<s:textarea class="elemTres" value="%{pedido.observaciones}"
				name="observaciones" />
		</fieldset>
		<s:submit class="botn" onclick="alert('Se ha actualizado los campos modificados');" label="Pagar" value="Actualizar Datos"></s:submit>
<%-- 		<s:submit class="botn" label="Pagar" value="Actualizar Datos"></s:submit> --%>
	</s:form>
	<a href="<s:url action='cargarClientes'/>" class="botn">Consultar
		todos los Clientes</a>
</body>
</html>