package net.itinajero.app.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Banner;
import net.itinajero.app.model.Horario;
import net.itinajero.app.model.Noticia;
import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IBannersService;
import net.itinajero.app.service.IHorariosService;
import net.itinajero.app.service.INoticiasService;
import net.itinajero.app.service.IPeliculasService;
import net.itinajero.app.util.Utileria;

@Controller
public class HomeController 
{
	//Con esta anotación Spring inyecta automáticamente la instancia de nuestra clase al arrancar la aplicación
	@Autowired
	private IPeliculasService servicePeliculas;
	
	@Autowired
	private IBannersService bannersService;
	
	@Autowired
	private IHorariosService serviceHorarios;
	
	@Autowired
	private INoticiasService serviceNoticias;
	
	
	private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	

//	//@GetMapping(value="/home")
//	@RequestMapping(value="/home", method=RequestMethod.GET)
//	public String goHome()
//	{
//		return "home";//nombre de la vista
//	}
	
	/**
	 * Metodo para mostrar la pagina principal de la aplicacion
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)//borrar fichero index.jsp para que cargue nuestro jsp home.jsp
	public String mostrarPrincipal(Model model)
	{
		Date fechaSinHora;
		try {
			
			fechaSinHora = df.parse(df.format(new Date()));
			List<String> listaFechas = Utileria.getNextDays(4);
			List<Pelicula> peliculas = servicePeliculas.buscarPorFecha(fechaSinHora);

			model.addAttribute("fechas", listaFechas);
			model.addAttribute("fechaBusqueda", df.format(new Date()));
			model.addAttribute("peliculas", peliculas);
			
			//Agragar al modelo listado de Banners para desplegarlo
//			List<Banner> banners = bannersService.buscarTodos();
					
//			model.addAttribute("banners", banners);
		
		} catch (ParseException e) {
			System.out.println("Error: HomeController.mostrarPrincipal" + e.getMessage());
		}
		
		return "home";//nombre de la vista
	}
	
	//@PostMapping(value="/search")
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String buscar(@RequestParam("fecha")  String fecha, Model model)
	{
		Date fechaSinHora;
		try {
			
			fechaSinHora = df.parse(df.format(fecha));
			List<String> listaFechas = Utileria.getNextDays(4);
			//Agragar al modelo listado de Películas para desplegarlo
			List<Pelicula> peliculas = servicePeliculas.buscarPorFecha(fechaSinHora);
			model.addAttribute("fechas", listaFechas);
			// Regresamos la fecha que selecciono el usuario con el mismo formato
			model.addAttribute("fechaBusqueda", df.format(fecha));
			model.addAttribute("peliculas", peliculas);
			
			//Agragar al modelo listado de Banners para desplegarlo
			//List<Banner> banners = bannersService.buscarTodos();
//			model.addAttribute("banners", banners);
		
		} catch (ParseException e) {
			System.out.println("Error: HomeController.buscar" + e.getMessage());
		}
		
		return "home";//nombre de la vista
	}


//	@RequestMapping(value="/detalle", method=RequestMethod.GET)
//	public String mostrarDetalle(Model model, @RequestParam("idMovie") int idPelicula, @RequestParam("fecha") String fecha)
//	{
//		System.out.println("Buscando Horarios para la Pelicula: " + idPelicula);
//		System.out.println("Para la Fecha: " + fecha);
//
//		return "detalle";//nombre de la vista
//	}
	
	/**
	 * Metodo para ver los detalles y horarios de una pelicula
	 * @param idPelicula
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/detalle/{id}/{fecha}", method=RequestMethod.GET)
	public String mostrarDetalle(Model model, @PathVariable("id") int idPelicula, @PathVariable("fecha") Date fecha)
	{

		List<Horario> horarios = serviceHorarios.buscarPorIdPelicula(idPelicula, fecha);
		
		model.addAttribute("horarios", horarios);
		model.addAttribute("fechaBusqueda", df.format(fecha));
		model.addAttribute("pelicula", servicePeliculas.buscarPorId(idPelicula));
		
		return "detalle";//nombre de la vista
	}
	
	/**
	 * Metodo que muestra la vista de la pagina de Acerca
	 * @return
	 */
	@RequestMapping(value = "/about")
	public String mostrarAcerca() {			
		return "acerca";
	}
	
	
	@ModelAttribute("banners")
	public List<Banner> getBanners(){
		return bannersService.buscarActivos();
	}
	
	
	//Disponible en cualquier parte en el Controlador
	@ModelAttribute("noticias")
	public List<Noticia> getNoticias(){
		return serviceNoticias.buscarUltimas();
	}
	
	
	
	
	/*
	 * Personalizamos el DataBinding para las propiedades de tipo Date
	 * para que cuando da error de formato Fecha lo formatee a nuestro formato
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) 
	{
		//Controlamos el error de formato de Fecha ya que por defecto coge el formato de fecha del sistema dd/mm/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	
}
