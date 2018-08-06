<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!-- Tag Library para acceder a recursos estáticos -->
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%--  Añadimos JSTL Loops y Condicionales--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--  Añadimos Tags JSTL para dar formato--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Listado de imagenes del banner</title>
    
     <!-- Variable al modelo con la URL relativa a resources -->
	<spring:url value="/resources" var="urlPublic" />
	
	
	<spring:url value="/banners/create" var="urlCreate" />
    
    <link href="${urlPublic }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${urlPublic }/bootstrap/css/theme.css" rel="stylesheet">
    
  </head>

  <body>

    <!-- Fixed navbar -->
   <jsp:include page="../includes/menu.jsp"></jsp:include>

    <div class="container theme-showcase" role="main">

      <h3>Listado de imagenes del Banner</h3>
      
       	<!-- Mensaje Flash -->
      <c:if test="${mensaje!=null }">      	
      	<div class='alert alert-success' role="alert">${mensaje}</div>      	
      </c:if>
      
      <a href="${urlCreate }" class="btn btn-success" role="button" title="Nuevo Banner" >Nuevo</a><br><br>

      <div class="table-responsive">
        <table class="table table-hover table-striped table-bordered">
            <tr>
                <th>Id</th>
                <th>Titulo</th>                           
                <th>Fecha Publicacion</th>              
                <th>Nombre Archivo</th>
                <th>Estatus</th>
                <th>Opciones</th>              
            </tr>
            <c:forEach items="${ banners}" var="banner">
	            <tr>
	                <td>${banner.id }</td>
	                <td>${banner.titulo }</td>
	                <td><fmt:formatDate value="${banner.fecha }" pattern="dd/MM/yyyy" /></td>    
	                <td>${banner.archivo }</td>  
	                <c:choose>
						<c:when test="${banner.estatus == 'Activo' }">
							<td><span class="label label-success">${banner.estatus }</span></td>
						</c:when>
						<c:otherwise>
							<td><span class="label label-danger">${banner.estatus }</span></td>
						</c:otherwise>
					</c:choose>
	                                       
	                <td>
	                    <a href="#" class="btn btn-success btn-sm" role="button" title="Edit" ><span class="glyphicon glyphicon-pencil"></span></a>
	                    <a href="#" class="btn btn-danger btn-sm" role="button" title="Eliminar" ><span class="glyphicon glyphicon-trash"></span></a>
	                </td>
	            </tr>
 			</c:forEach> 
	
         
        </table>
      </div>  
      <hr class="featurette-divider">

      <!-- FOOTER -->
		<jsp:include page="../includes/footer.jsp"></jsp:include>

    </div> <!-- /container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
    <script src="${urlPublic }/bootstrap/js/bootstrap.min.js"></script>     
  </body>
</html>
