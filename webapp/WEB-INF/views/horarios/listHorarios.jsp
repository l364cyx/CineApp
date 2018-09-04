<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Listado de Horarios</title>
	<spring:url value="/resources" var="urlPublic" />
	<spring:url value="/horarios" var="urlHorarios" />
			
	<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">	
	<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
	
	</head>

<body>

	<jsp:include page="../includes/menu.jsp"></jsp:include>
	
	<div class="container theme-showcase" role="main">

		<h3>Listado de Horarios</h3>
      
        <c:if test="${msg !=null }">        
        		<div class='alert alert-success' role='alert'>${msg}</div>
        </c:if>	
      
        <a href="${urlHorarios}/create" class="btn btn-success" role="button" title="Nuevo horario" >Nuevo</a><br><br>        
      
        <div class="table-responsive">	
	        <table class="table table-hover table-striped table-bordered">
	          <tr>
	              <th>Pelicula</th>
	              <th>Fecha</th>
	              <th>Hora</th>
	              <th>Sala</th>
	              <th>Precio</th>
	              <th>Opciones</th>
	          </tr>
	
				<c:forEach var="horario" items="${horarios.content}">
					<tr>
						<td>${horario.pelicula.titulo}</td>
						<td><fmt:formatDate pattern="dd-MM-yyyy" value="${horario.fecha}" /></td>
						<td>${horario.hora}</td>
						<td>${horario.sala} </td>
						<td>$${horario.precio}</td>					
						<td>
							<a href="${urlHorarios}/edit/${horario.id}" class="btn btn-success btn-sm" role="button" title="Edit"><span class="glyphicon glyphicon-pencil"></span></a>							
							<a href="${urlHorarios}/delete/${horario.id}" onclick='return confirm("¿Estas seguro?")' class="btn btn-danger btn-sm" role="button" title="Eliminar"><span class="glyphicon glyphicon-trash"></span></a>
						</td>
					</tr>
				</c:forEach>
	
			</table>
		</div>
		<nav aria-label="">
		  <ul class="pager">
		    <li><a href="${urlHorarios}/indexPaginate?page=${horarios.number - 1 }">Anterior</a></li>
		    <li><a href="${urlHorarios}/indexPaginate?page=${horarios.number + 1 }">Siguiente</a></li>
		  </ul>
		</nav>

      <hr class="featurette-divider">

      <jsp:include page="../includes/footer.jsp"></jsp:include>		

	</div> <!-- /container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>