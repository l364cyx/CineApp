package net.itinajero.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Banner;
import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IBannersService;
import net.itinajero.app.service.IPeliculasService;
import net.itinajero.app.util.Utileria;

@Controller
public class HomeController 
{
	//Con esta anotaci�n Spring inyecta autom�ticamente la instancia de nuestra clase al arrancar la aplicaci�n
	@Autowired
	private IPeliculasService servicePeliculas;
	
	@Autowired
	private IBannersService bannersService;
	
	private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	
	//@GetMapping(value="/home")
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String goHome()
	{
		return "home";//nombre de la vista
	}
	
	//@PostMapping(value="/search")
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String buscar(@RequestParam("fecha")  String fecha, Model model)
	{
		System.out.println("Buscando todas las pel�culas en exhibici�n para la fecha: " + fecha);
		
		List<String> listaFechas = Utileria.getNextDays(10);
		
		//Agragar al modelo listado de Pel�culas para desplegarlo
		List<Pelicula> peliculas = servicePeliculas.buscarTodas();
		
		//Agragar al modelo listado de Banners para desplegarlo
		List<Banner> banners = bannersService.buscarTodos();
		
		model.addAttribute("fechaBusqueda", fecha);
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("fechas", listaFechas);
		model.addAttribute("banners", banners);
		
		
		
		return "home";//nombre de la vista
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)//borrar fichero index.jsp para que cargue nuestro jsp home.jsp
	public String mostrarPrincipal(Model model)
	{
		List<String> listaFechas = Utileria.getNextDays(10);
		
		List<Pelicula> peliculas = servicePeliculas.buscarTodas();
		
		//Agragar al modelo listado de Banners para desplegarlo
		List<Banner> banners = bannersService.buscarTodos();
				
		model.addAttribute("fechaBusqueda", df.format(new Date()));
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("fechas", listaFechas);
		model.addAttribute("banners", banners);
		
		
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
	
	@RequestMapping(value="/detalle/{id}/{fecha}", method=RequestMethod.GET)
	public String mostrarDetalle(Model model, @PathVariable("id") int idPelicula, @PathVariable("fecha") String fecha)
	{
		System.out.println("Buscando Horarios para la Pelicula: " + idPelicula);
		System.out.println("Para la Fecha: " + fecha);

		model.addAttribute("pelicula", servicePeliculas.buscarPorId(idPelicula));
		
		return "detalle";//nombre de la vista
	}
	
	
	
}
