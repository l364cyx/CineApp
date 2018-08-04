<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!-- Tag Library para acceder a recursos estáticos -->
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Creacion de Noticias</title>    

	<!-- Variable al modelo con la URL relativa a resources -->
	<spring:url value="/resources" var="urlPublic" />
	

    <link href="${urlPublic }/bootstrap/css/bootstrap.min.css" rel="stylesheet">   
    <link href="${urlPublic }/bootstrap/css/theme.css" rel="stylesheet">

  </head>

  <body>

   <!-- Incluímos Fichero jsp-->
    <jsp:include page="../includes/menu.jsp"></jsp:include>

    <div class="container theme-showcase" role="main">

      <h3 class="blog-title"><span class="label label-success">Datos de la Noticia</span></h3>

	  <spring:url value="/noticias/save" var="urlForm"></spring:url>
		
      <form action="${urlForm }" method="post">
        <div class="row">         
          <div class="col-sm-6">
            <div class="form-group">
              <label for="titulo">Titulo</label>             
              <input type="text" class="form-control" name="titulo" id="titulo" required="required"/>
            </div>
          </div>
          <div class="col-sm-3">
            <div class="form-group">
              <label for="estatus">Estatus</label>             
              <select id="estatus" name="estatus" class="form-control">
                <option value="Activa">Activa</option>
                <option value="Inactiva">Inactiva</option>                
              </select>  
            </div>
          </div>
        </div>
        <div class="row"> 
          <div class="col-sm-12">
            <div class="form-group">
              <label for="detalle">Detalles</label>             
              <textarea class="form-control" name="detalle" id="detalle" rows="10"></textarea>
            </div>  
          </div>
        </div>

        <button type="submit" class="btn btn-danger" >Guardar</button>
      </form> 

      <hr class="featurette-divider">

      <!-- FOOTER -->
       <!-- Incluímos Fichero jsp-->
    	<jsp:include page="../includes/footer.jsp"></jsp:include>

    </div> <!-- /container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
    <script src="${urlPublic }/bootstrap/js/bootstrap.min.js"></script> 
    <script src="${urlPublic }/tinymce/tinymce.min.js"></script>
    <script>
    	//Configuración de la barra de herramientas
    	//https://www.tiny.cloud/docs/get-started/
      tinymce.init({
          selector: '#detalle',
          plugins: "textcolor, table lists code",
          toolbar: " undo redo | bold italic | alignleft aligncenter alignright alignjustify \n\
                    | bullist numlist outdent indent | forecolor backcolor table code"
      });
    </script>
  </body>
</html>