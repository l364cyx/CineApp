<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%--  Añadimos JSTL Loops y Condicionales--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--  Añadimos JSTL para dar formato--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- Tag Library para acceder a recursos estáticos -->
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido a CineSite</title>

<!-- CSS de Bootstrap vía CDN-->
<!-- 
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
-->
<!-- Variable al modelo con la URL relativa a resources -->
<spring:url value="/resources" var="urlPublic" />

<!-- CSS de Bootstrap Local-->	
<link rel="stylesheet" href="${urlPublic}/bootstrap/css/bootstrap.min.css">	

</head>
<body>

	<%--   
		<h1>Lista de Películas:</h1>
		<ol>
			<c:forEach items="${ peliculas}" var="pelicula">
				<li>${pelicula }</li>
			</c:forEach>
		</ol>
	--%>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Tabla de Películas</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Titulo</th>
						<th>Duracion</th>
						<th>Clasificacion</th>
						<th>Genero</th>
						<th>Imagen</th>
						<th>Fecha Estreno</th>
						<th>Estatus</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ peliculas }" var="pelicula">
						<tr>
							<td>${pelicula.id }</td>
							<td>${pelicula.titulo }</td>
							<td>${pelicula.duracion }min.</td>
							<td>${pelicula.clasificacion }</td>
							<td>${pelicula.genero }</td>
							<td><img src="${urlPublic}/images/${pelicula.imagen}"
								width="80" height="100"></td>
							<td><fmt:formatDate value="${pelicula.fechaEstreno }"
									pattern="dd/MM/yyyy" /></td>
							<td>
							
								<c:choose>
									<c:when test="${pelicula.estatus=='Activa'}">
										<span class="label label-success">ACTIVA</span>
									</c:when>
									<c:otherwise>
										<span class="label label-danger">INACTIVA</span>
									</c:otherwise>

								</c:choose>
								
								</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="panel-footer">Panel footer</div>
	</div>

</body>
</html>