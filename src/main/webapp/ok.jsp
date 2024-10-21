<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri= "/struts-tags" prefix="s" %>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Resumen de compra</title>
<link rel="stylesheet" href="CSS/estilos.css">
</head>
<body>
<h1 class="ok">DOCUMENTO DE PAGO</h1>
<label class="nom">NOMBRE:</label><br/>
<label class="TresSeguidos"><s:property  value="nombre"/></label>
<br/><label class="nom">APELLIDOS:</label><br/>
<label class="TresSeguidos"><s:property value="apellidos"/></label>
<br/><label class="nom">GÉNERO:</label><br/>

<%if(request.getParameter("genero").equals("1")){%>
	<label class="TresSeguidos">Masculino</label>
	<% }else if(request.getParameter("genero").equals("2")){%>
		<label class="TresSeguidos">Femenino</label>
	<%} %>
<br/><label class="nom">TIPO:</label><br/>
 <label class="TresSeguidos"> <%String h = "";
  String s = "";
  Boolean hardw = Boolean.valueOf(request.getParameter("hard"));
  Boolean softw = Boolean.valueOf(request.getParameter("soft"));

if(hardw.booleanValue() == true){
	h = "Hardware";
}

if(softw.booleanValue() == true){
	s = "Software";
}
out.print(h +" " + s);
%></label>
<br/><label class="nom">PRODUCTO:</label><br/>
  
 <label class="TresSeguidos"> <%
  switch(request.getParameter("producto")){
  case "01":
	  out.print("MONITOR");
	  
	  break;
  case "02":
	  out.print("LECTOR DVD");   
	  break;
  case "03":
	  out.print("IMPRESORA");	   
	  break;
  case "04":
	  out.print("RATON");
	  break;
  case "05":
	  out.print("TECLADO");
	  break;
  case "06":
	  out.print("DISCO DURO");
	  break;
  }%></label>


<br/><label class="nom">CANTIDAD:</label><br/>
<label class="TresSeguidos"><s:property  value="cantidad"/></label>
<br/><label class="nom">TOTAL:</label><br/>

<label class="TresSeguidos"><%String preciounidad = request.getParameter("precio");
String cantidad = request.getParameter("cantidad"); 
double precios = Double.parseDouble(preciounidad);
int cantidades = Integer.parseInt(cantidad);
double total = precios * cantidades;
				out.print(String.valueOf(total));
				%></label>				
<br/><label class="nom">OBSERVACIONES:</label><br/>
<label class="TresSeguidos"><s:property value="observaciones"/></label>
<a href="<s:url action='cargarClientes'/>" class="botn">Ir a Página Vacía</a>
</body>
</html>