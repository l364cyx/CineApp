package net.itinajero.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.service.INoticiasService;
import net.itinajero.app.service.IPeliculasService;

@Controller
@RequestMapping("/noticias") //RequestMapping a nivel de clase
public class NoticiasController {

	//Con esta anotación Spring inyecta automáticamente la instancia de nuestra clase al arrancar la aplicación
	//Inyección de dependcencias 
	@Autowired
	private INoticiasService noticiasService;
	
	@GetMapping(value="/create")
	public String crear()
	{
		//El directorio no tiene que llamarse igual que el RequestMapping
		return "noticias/formNoticias";
	}
	
	@PostMapping(value="/save")
	public String guardar(@RequestParam("titulo") String titulo,@RequestParam("estatus") String estatus, 
							@RequestParam("detalle") String detalle)
	{
		Noticia noticia = new Noticia();
		noticia.setTitulo(titulo);
		noticia.setEstatus(estatus);
		noticia.setDetalle(detalle);
		
//		System.out.println(noticia);
		
		noticiasService.guardar(noticia);
		
		
		return "noticias/formNoticias";
	}
}
