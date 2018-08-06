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
      <title>Creacion de imagenes del Banner</title>    

 	<!-- Variable al modelo con la URL relativa a resources -->
	<spring:url value="/resources" var="urlPublic" />
	
	<spring:url value="/banners/save" var="urlForm" />
	
      <link href="${urlPublic }/bootstrap/css/bootstrap.min.css" rel="stylesheet">   
      <link href="${urlPublic }/bootstrap/css/theme.css" rel="stylesheet">

   </head>

   <body>

      <!-- Fixed navbar -->
      <jsp:include page="../includes/menu.jsp"></jsp:include>

    
      <div class="container theme-showcase" role="main">

         <h3 class="blog-title"><span class="label label-success">Datos de la imagen</span></h3>

	<%--Control de Errores en Data Binding--%>
		<spring:hasBindErrors name="pelicula">
			<div class='alert alert-danger' role='alert'>
				Por favor corrija los siguientes errores:
				<ul>
					<c:forEach var="error" items="${errors.allErrors}">
						<li><spring:message message="${error}" /></li>
					</c:forEach>
				</ul>
			</div>
		</spring:hasBindErrors>

         <form action="${urlForm }" method="post" enctype="multipart/form-data">
            <div class="row">         
               <div class="col-sm-6">
                  <div class="form-group">
                     <label for="titulo">Titulo</label>             
                     <input type="text" class="form-control" name="titulo" id="titulo" required="required"/>
                  </div>
               </div>


               <div class="col-sm-3">
                  <div class="form-group">
                     <label for="imagen">Imagen</label>
                     <input type="file" id="archivoImagen" name="archivoImagen" required="required" />
                     <p class="help-block">Tamaño recomendado: 1140 x 250 </p>
                  </div> 
               </div> 

               <div class="col-sm-3">
                  <div class="form-group">
                     <label for="estatus">Estatus</label>             
                     <select id="estatus" name="estatus" class="form-control">
                        <option value="Activo">Activo</option>
                        <option value="Inactivo">Inactivo</option>                
                     </select>  
                  </div>
               </div>
            </div>

            <button type="submit" class="btn btn-danger" >Guardar</button>
         </form> 

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
