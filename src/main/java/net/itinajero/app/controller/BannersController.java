package net.itinajero.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.app.model.Banner;
import net.itinajero.app.service.IBannersService;
import net.itinajero.app.util.Utileria;

@Controller
@RequestMapping("/banners")
public class BannersController {

	// Ejercicio: Inyectar instancia de la clase de servicio
	@Autowired
	private IBannersService bannersService;
		
	/**
	 * Metodo para mostrar el listado de banners
	 * @param model
	 * @return
	 */
	@GetMapping("/index")
	public String mostrarIndex(Model model) 
	{
		
		// Ejercicio: Implementar metodo
		List<Banner> lista = bannersService.buscarTodos();
		
		model.addAttribute("banners", lista);
		// Ejercicio: Crear vista listBanners.jsp. Utilizar el archivo listBanners.html de la plantilla 
		return "banners/listBanners";
	}
	
	/**
	 * Metodo para mostrar el formulario para crear un nuevo Banner
	 * @return
	 */
	@GetMapping("/create")
	public String crear() {
		
		// Ejercicio: Crear vista formBanner.jsp. Utilizar el archivo formBanner.html de la plantilla 
		return "banners/formBanner";
		
	}
	
	/**
	 * Metodo para guardar el objeto de modelo de tipo Banner
	 * @return
	 */
	@PostMapping("/save")
	public String guardar(Banner banner, //Data Binding:Banner  no es necesario crear ni llamar a objeto Banner 
							BindingResult result, //Control de Errores en Data Binding
							RedirectAttributes attributes, //Mensajes antes de recargar
							@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request)//Subida de Ficheros 
	{
		
		// Ejercicio: Implementar el metodo.
		System.out.println("Recibiendo objeto Banner " + banner);
		
		//Control de Errores en Data Binding
		if(result.hasErrors())
		{
			for (ObjectError error: result.getAllErrors())
			{
				System.out.println(error.getDefaultMessage());
			}
			return "banners/formBanner";
		}
		
		
		//Subida de Imagen
		if (!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multiPart,request);
			System.out.println("Nombre de la imagen: " + nombreImagen);
			banner.setArchivo(nombreImagen);
		}
		
		/*
		 * 1. Inserción nuevo Banner mediante DataBinding: no es necesario llamar objeto Banner, 
		 *    Con DataBinding Spring MVC extrae dinámicamente los datos de entrada del usuario y los asigna a 
		 *    objetos de Modelo de nuestra aplicación
		 *	 
		 * 2. bannerService: con @Autowired hacemos Inyección de Dependencias de una clase de servicio en un Controlador
		 * 
		 */
		bannersService.insertar(banner);
		
		//Esto nos permite poder enviar un mensaje antes de hacer la redirección, es temporal, al redireccionar son eliminados de la sesión
		attributes.addFlashAttribute("mensaje", "El registro de Banner fue guardado");
		
		//Hacemos redirección a listado banners
		return "redirect:/banners/index";
	}	
}
