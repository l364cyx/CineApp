package net.itinajero.app.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import net.itinajero.app.model.Contacto;
import net.itinajero.app.service.IPeliculasService;

@Controller
public class ContactoController {

	@Autowired
	private IPeliculasService peliculasService;
	
	@GetMapping(value="/contacto")
	public String mostrarFormulario(@ModelAttribute("instanciaContacto") Contacto contacto,//Se puede cambiar el nombre de contaco a instanciaContacto que es el nombre que hay que pasar a la vista
									Model model)
	{
		model.addAttribute("generos", peliculasService.buscarGeneros());
		model.addAttribute("tipos", tipoNotificaciones());
		
		return "formContacto";
	}
	
	@PostMapping(value="/contacto")
	public String guardar(@ModelAttribute("instanciaContacto") Contacto contacto,//Con esto vamos a tener los datos que metió el usuario @ModelAtributte
							Model model)
	{
		System.out.println(contacto);
		model.addAttribute("generos", peliculasService.buscarGeneros());
		
		
		return "redirect:/contacto";
	}
	
	private List<String> tipoNotificaciones(){
		// Nota: La lista se podria generar apartir de una BD
		List<String> tipos = new LinkedList<>();
		tipos.add("Estrenos");
		tipos.add("Promociones");
		tipos.add("Noticias");
		tipos.add("Preventas");
		return tipos;
	}
}
