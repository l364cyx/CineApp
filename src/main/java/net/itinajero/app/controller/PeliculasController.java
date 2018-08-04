package net.itinajero.app.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IPeliculasService;
import net.itinajero.app.util.Utileria;

@Controller
@RequestMapping(value="/peliculas")
public class PeliculasController {
	
	@Autowired
	private IPeliculasService peliculasService;
	
	@GetMapping(value="/index")
	public String mostrarIndex(Model model)
	{
		List<Pelicula> lista = peliculasService.buscarTodas();
		
		model.addAttribute("peliculas", lista);
		
		return "peliculas/listPeliculas";
		
	}
	
	@GetMapping(value="/create")
	public String crear()
	{
		return "peliculas/formPeliculas";
	}

	@PostMapping(value="/save")
	public String guardar(Pelicula pelicula, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request)//Data Binding:Pelicula  no es necesario crear ni llamar a objeto Pelicula
	{
		if(result.hasErrors())
		{
			for (ObjectError error: result.getAllErrors())
			{
				System.out.println(error.getDefaultMessage());
			}
//			System.out.println("Existieron errores");
			return "peliculas/formPeliculas";
		}
		
		if (!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multiPart,request);
			System.out.println("Nombre de la imagen: " + nombreImagen);
			pelicula.setImagen(nombreImagen);
		}
		
		System.out.println("Recibiendo objeto película " + pelicula);
		
		System.out.println("Elementos en la lista antes de la inserción: " + peliculasService.buscarTodas().size());
		
		peliculasService.insertar(pelicula);
		
		System.out.println("Elementos en la lista después de la inserción: " + peliculasService.buscarTodas().size());
		
		//Esto nos permite poder enviar un mensaje antes de hacer la redirección, es temporal, al redireccionar son eliminados de la sesión
		attributes.addFlashAttribute("mensaje", "El registro fue guardado");
		
		return "redirect:/peliculas/index";
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
