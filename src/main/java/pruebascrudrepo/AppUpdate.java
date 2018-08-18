package pruebascrudrepo;

import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

public class AppUpdate {

	public static void main(String[] args) 
	{
	
	/*
	 * Existen varias implementaciones de la interfaz ApplicationContext de Spring, en los ejemplos vamos a trabajar con la clase ClassPathXmlApplicationContext,
	 * esta clase es la que va a crear una instancia del ApplicationContext de Spring leyendo la configuración de los Beans desde un archivo XML, 
	 * el cual ya tenenmos creado "root-context.xml"
	 */
		//Se crea una instancia del ApplicationContext con todos los Beans que estén declarados en el archivo xml
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		//Operacion CRUD: Update
		
		// 1. Primero buscamos la entidad que vamos a modificar
		Optional<Noticia> optional = repo.findById(1);

		// 2. Modificamos la entidad y la guardamos
		if(optional.isPresent())
		{
			Noticia noticia = optional.get();
			
			noticia.setDetalle("El mes de septiembre se estrena la nueva entrega de SAW 8");
			noticia.setEstatus("Inactiva");
			
			//Spring para saber si es un Insert o un Update toma el valor por defecto del ID
			repo.save(noticia);
			
		}
		
		//Cerramos en context
		context.close();
	}
}
