package net.itinajero.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.app.model.Horario;
import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IPeliculasService;

@Controller
@RequestMapping(value="/horarios")
public class HorariosController {
		
	@Autowired
	private IPeliculasService peliculasService;
	/**
	 * Metodo para mostrar el formulario para crear un nuevo horario
	 * @return
	 */
	@GetMapping(value = "/create")
	public String crear(@ModelAttribute Horario horario, Model model) {
		
		// Ejercicio: Recuperar lista de peliculas para poblar <select>
		List<Pelicula> lista = peliculasService.buscarTodas();
 		// Ejercicio: agregar al modelo listado de peliculas
		model.addAttribute("listaPeliculas", lista);
		// Ejercicio: crear archivo formHorario.jsp y configurar el dise�o utilizando el codigo HTML
		// del archivo formHorario.html de la plantilla (utilizar Form Tag Library)
		
		return "horarios/formHorario";
	}
		
	/**
	 * Metodo para guardar el registro del Horario
	 * @param horario
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/save")
	public String guardar(@ModelAttribute Horario horario,
			BindingResult result, //Control de Errores en Data Binding
			RedirectAttributes attributes, //Mensajes antes de recargar
			Model model) {				
		
		
		
		
		// Ejercicio: Verificar si hay errores en el Data Binding
		//Control de Errores en Data Binding
		if(result.hasErrors())
		{
			for (ObjectError error: result.getAllErrors())
			{
				List<Pelicula> lista = peliculasService.buscarTodas();
				model.addAttribute("listaPeliculas", lista);
				System.out.println(error.getDefaultMessage());
			}
			return "horarios/formHorario";
		}
		
//		if (result.hasErrors()){
//			List<Pelicula> listaPeliculas = peliculasService.buscarTodas();
//			model.addAttribute("peliculas", listaPeliculas);
//			return "horarios/formHorario";
//		}
		// Ejercicio: En caso de no haber errores, imprimir en consola el objeto de model Horario 
		// que ya debera de tener los datos del formulario

		System.out.println("Guardando el objeto Horario: " + horario);//Esto nos permite poder enviar un mensaje antes de hacer la redirección, es temporal, al redireccionar son eliminados de la sesiÃ³n
		//Esto nos permite poder enviar un mensaje antes de hacer la redirección, es temporal, al redireccionar son eliminados de la sesiÃ³n
		attributes.addFlashAttribute("mensaje", "El registro Horario fue guardado");
		// De momento, hacemos un redirect al mismo formulario 
		return "redirect:/horarios/create";
	}
	
	// Ejercicio: Crear metodo para personalizar el Data Binding para las todas las propiedades de tipo Date
	/**
	 * Personalizamos el Data Binding para todas las propiedades de tipo Date
	 * @param binder
	 */
	@InitBinder("horario")
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));		
	}
	
}