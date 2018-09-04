package net.itinajero.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IDetallesService;
import net.itinajero.app.service.IPeliculasService;
import net.itinajero.app.util.Utileria;

@Controller
@RequestMapping(value="/peliculas")
public class PeliculasController {
	
	// Inyectamos una instancia desde nuestro Root ApplicationContext
	@Autowired
	private IDetallesService detallesService;
	
	// Inyectamos una instancia desde nuestro Root ApplicationContext
	@Autowired
	private IPeliculasService peliculasService;
	
	
	/**
	 * Metodo que muestra la lista de peliculas
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/index")
	public String mostrarIndex(Model model) {
		List<Pelicula> lista = peliculasService.buscarTodas();
		model.addAttribute("peliculas", lista);
		return "peliculas/listPeliculas";
	}
	
	/**
	 * Metodo que muestra la lista de peliculas con paginacion
	 * @param model
	 * @param page
	 * @return
	 */
	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Pelicula> lista = peliculasService.buscarTodas(page);
		model.addAttribute("peliculas", lista);
		return "peliculas/listPeliculas";
	}
	
	/**
	 * Metodo para mostrar el formulario para crear una pelicula
	 * @return
	 */
	@GetMapping(value="/create")
	public String crear(@ModelAttribute Pelicula pelicula, Model model)
	{
		/*
		 * public String crear() --> En caso de introducir mal los datos del formulario no mantiene los datos en el formulario 
		 * 
		 * public String crear(@ModelAttribute Pelicula pelicula) --> con @ModelAttribute es lo mismo pero con los tags de formularios: 
		 * <form:form  >nos mantiene los datos en el formulario ya que se crea el objeto pelicula
		 */
		
		
		//model.addAttribute("generos", peliculasService.buscarGeneros());
		
		return "peliculas/formPeliculas";
	}

	/**
	 * Metodo para guardar los datos de la pelicula (CON ARCHIVO DE IMAGEN)
	 * @param pelicula
	 * @param result
	 * @param model
	 * @param multiPart
	 * @param request
	 * @return
	 */
	@PostMapping(value="/save")
	public String guardar(@ModelAttribute Pelicula pelicula, //Data Binding:Pelicula  no es necesario crear ni llamar a objeto Pelicula
							BindingResult result, //Control de Errores en Data Binding
							RedirectAttributes attributes, //Mensajes antes de recargar
							@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request)//Subida de Ficheros
	{
		//Control de Errores en Data Binding
		if(result.hasErrors())
		{
			for (ObjectError error: result.getAllErrors())
			{
				System.out.println(error.getDefaultMessage());
			}
//			System.out.println("Existieron errores");
			return "peliculas/formPeliculas";
		}
		
		//Subida de Imagen
		if (!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multiPart,request);
			System.out.println("Nombre de la imagen: " + nombreImagen);
			if (nombreImagen!=null){ // La imagen si se subio				
				pelicula.setImagen(nombreImagen); // Asignamos el nombre de la imagen
			}
		}

		// Primero insertamos el detalle
		detallesService.insertar(pelicula.getDetalle());
		
		
		/*
		 * 1.Inserción nueva Película mediante DataBinding: no es necesario llamar objeto Película, 
		 *    Con DataBinding Spring MVC extrae dinámicamente los datos de entrada del usuario y los asigna a 
		 *    objetos de Modelo de nuestra aplicación.
		 * 
		 * 2.peliculasService: con @Autowired hacemos Inyección de Dependencias de una clase de servicio en un Controlador
		 * 
		 */
		// Como el Detalle ya tiene id, ya podemos guardar la pelicula
		peliculasService.insertar(pelicula);
		
		//Esto nos permite poder enviar un mensaje antes de hacer la redirección, es temporal, al redireccionar son eliminados de la sesiÃ³n
		attributes.addFlashAttribute("mensaje", "El registro Película fue guardado");
		
//		return "redirect:/peliculas/index";
		return "redirect:/peliculas/indexPaginate";	
	}
	
	/**
	 * Metodo que muestra el formulario para editar una pelicula
	 * @param idPelicula
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/edit/{id}")
	public String editar(@PathVariable("id") int idPelicula, Model model)
	{
		Pelicula pelicula = peliculasService.buscarPorId(idPelicula);
		model.addAttribute("pelicula", pelicula);
		//model.addAttribute("generos", peliculasService.buscarGeneros());
		
		
		return "peliculas/formPeliculas";
	}

	/**
	 * Metodo para eliminar una pelicula
	 * @param idPelicula
	 * @param attributes
	 * @return
	 */
	@GetMapping(value = "/delete/{id}")
	public String eliminar(@PathVariable("id") int idPelicula, RedirectAttributes attributes)
	{
		// Buscamos primero la pelicula
		Pelicula p = peliculasService.buscarPorId(idPelicula);
		
		// Eliminamos la pelicula. Tambien al borrar la pelicula, se borraran los Horarios (ON CASCADE DELETE)
		peliculasService.eliminar(idPelicula);
		
		// Eliminamos el registro del detalle
		detallesService.eliminar(p.getDetalle().getId());
		
		attributes.addFlashAttribute("mensaje", "La Película fue eliminada");
		
//		return "redirect:/peliculas/index";
		return "redirect:/peliculas/indexPaginate";
	}
	
	
	/**
	 * Agregamos al Model la lista de Generos: De esta forma nos evitamos agregarlos en los metodos
	 * crear y editar. 
	 * @return
	 */
	@ModelAttribute("generos")
	public List<String> getGeneros()
	{
		return peliculasService.buscarGeneros();
	}
	
	/**
	 * Personalizamos el Data Binding para todas las propiedades de tipo Date
	 * @param webDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) 
	{
		//Controlamos el error de formato de Fecha ya que por defecto coge el formato de fecha del sistema dd/mm/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
