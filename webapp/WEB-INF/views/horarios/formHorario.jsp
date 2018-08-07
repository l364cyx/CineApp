<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- Tag Library para acceder a recursos estáticos -->
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Tag Library para formularios -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%--  Añadimos Tags JSTL Loops y Condicionales--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">    
		<meta name="author" content="">
		<title>Creacion de Horarios</title>

	<!-- Variable al modelo con la URL relativa a resources -->
	<spring:url value="/resources" var="urlPublic" />
	
	<spring:url value="/horarios/save" var="urlForm" />

		<link href="${urlPublic }/bootstrap/css/bootstrap.min.css" rel="stylesheet">    
		<link href="${urlPublic }/bootstrap/css/theme.css" rel="stylesheet">
		<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	</head>

	<body>

		<!-- Fixed navbar -->
		 <jsp:include page="../includes/menu.jsp"></jsp:include>

		<div class="container theme-showcase" role="main">

			<h3 class="blog-title"><span class="label label-success">Datos del Horario</span></h3>  

		${horario }
	<!-- Mensaje Flash -->
	      <c:if test="${mensaje!=null }">      	
	      	<div class='alert alert-success' role="alert">${mensaje}</div>      	
	      </c:if>
      
		<%--Control de Errores en Data Binding --%>
			<spring:hasBindErrors name="horario">
				<div class='alert alert-danger' role='alert'>
					Por favor corrija los siguientes errores:
					<ul>
						<c:forEach var="error" items="${errors.allErrors}">
							<li><spring:message message="${error}" /></li>
						</c:forEach>
					</ul>
				</div>
			</spring:hasBindErrors>


			<form:form modelAttribute="horario" action="${urlForm }" method="post">
				<div class="row">         
					<div class="col-sm-3">
						<div class="form-group">
							<label for="idPelicula" class="control-label">Pelicula</label>              
							
							<!-- Form:select con objetos compuestos  -->
							<form:select id="idPelicula" path="pelicula.id" class="form-control" items="${ listaPeliculas}" itemValue="id" itemLabel="titulo"/>
							
<%-- 							 <form:select id="idPelicula" path="titulo" class="form-control" items="${ listaPeliculas}"/> --%>
							
<!-- 								<option value="1">Titulo Pelicula 1</option> -->
<!-- 								<option value="2">Titulo Pelicula 2</option> -->
<!-- 								<option value="3">Titulo Pelicula 3</option>                 -->
<!-- 							</select>              -->
						</div> 
					</div>
				</div>
				<div class="row"> 
					<div class="col-sm-3">
						<div class="form-group">
							<label for="fecha">Fecha</label>             
							<form:input type="text" class="form-control" path="fecha" id="fecha" required="required"/>
						</div>  
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label for="hora">Hora</label>
							<form:input type="text" class="form-control" path="hora" id="hora" placeholder="Formato: HH:mm Ejemplo 21:30" required="required"/>
						</div>  
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label for="sala" class="control-label">Sala</label>              
							<form:select id="sala" path="sala" class="form-control">
								<form:option value="Premium">Sala Premium</form:option>
								<form:option value="Sala 1">Sala 1</form:option>
								<form:option value="Sala 2">Sala 2</form:option>
								<form:option value="Sala 3">Sala 3</form:option>                
							</form:select>             
						</div> 
					</div>

					<div class="col-sm-3">
						<div class="form-group">
							<label for="precio">Precio</label>
							<form:input type="text" class="form-control" path="precio" id="precio" required="required"/>
						</div>  
					</div>

				</div>

				<button type="submit" class="btn btn-danger" >Guardar</button>
			</form:form> 

			<hr class="featurette-divider">

			<!-- FOOTER -->
			<jsp:include page="../includes/footer.jsp"></jsp:include>

		</div> <!-- /container -->

		<!-- Bootstrap core JavaScript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
		<script src="${urlPublic }/bootstrap/js/bootstrap.min.js"></script> 
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
      $(function () {
            $("#fecha").datepicker({dateFormat: 'dd/mm/yy'});
        }
      );
    </script>
	</body>
</html>
