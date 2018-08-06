<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- Tag Library para acceder a recursos estáticos -->
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:url value="/" var="urlRoot" />


<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${urlRoot }">My CineSite</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="${urlRoot }peliculas/index">Peliculas</a></li>
				<li><a href="${urlRoot }banners/index">Banners</a></li>
				<li><a href="#">Acerca</a></li>
				<li><a href="#">Login</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>