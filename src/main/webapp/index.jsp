<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inserta los datos</title>
<link rel="stylesheet" href="CSS/estilos_form.css">
</head>
<body>
	<H1 class="ok">FORMULARIO DE COMPRA</H1>
	<h2>Solicitud de datos</h2>
	<s:form action="acceder">
		
		<table class="tabla1"></table>
		<fieldset>
<!-- 		<legend>Choose your favorite monster</legend> -->
		<label class="telef">TELÉFONO:</label><br />
		<s:textfield name="telefono" class="telefon" />
		<br />
		<label class="nom">NOMBRE:</label><br />
		<s:textfield name="nombre" class="nomb" />
		<br />
		<label class="apel">APELLIDOS:</label><br />
		<s:textfield name="apellidos" class="apell"/>

		<br />
		<br />
		<div class="div">
		<label class="tipoProductos">GENERO</label>
		<br />
		<s:radio class="comprobante" name="genero"
			list="#{'1':'Masculino', '2':'Femenino'}" />
		</div>
		
		<br />
			<div class="div">
				<label class="tipoProductos">TIPO DE PRODUCTOS A COMPRAR</label> <br />
				<s:checkbox name="hard" label="Hardware" />
				<s:checkbox name="soft" label="Software" />
			</div>
			<br/><label class="TresSeguidos">PRODUCTO</label>&nbsp;&nbsp;&nbsp;<label class="TresSeguidos">CANTIDAD</label>&nbsp;&nbsp;&nbsp;<label class="TresSeguidos"> PRECIO UNIDAD</label>
		<br/><s:select class="elemTres" name="producto" list="#{'01': 'MONITOR', '02':'LECTOR DVD','03':'IMPRESORA','04':'RATON','05':'TECLADO','06':'DISCO DURO'}" headerKey="-1" />
		<s:textfield name = "cantidad" size="10" value="0"/>
		<s:textfield  name = "precio" size="10" value="0"/><br/> 
	<br />
		<label class="tipoProductos">TIPO DE COMPROBANTE DE PAGO</label>
		<br />
		<s:radio class="comprobante" name="comprobante"
			list="#{'1':'Ticket', '2':'Factura'}" />
		<br />

		<br />
		<br />
		<label class="tipoProductos">OBSERVACIONES</label>
		<br />
		<s:textarea class="elemTres" name="observaciones" />

		</fieldset>

		<s:submit class="botn" label="Pagar" value="Pagar"></s:submit>
	</s:form>
	<a href="<s:url action='cargarClientes'/>" class="botn">Consultar
		todos los Clientes</a>
</body>
</html>