package pruebascrudrepo;

import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

public class AppRead {
	
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
		
		//Operacion CRUD: Read
		//Optional evita que nos salte un NullpointerException
		Optional<Noticia> noticia = repo.findById(1);
		System.out.println(noticia);
		
		
		//Cerramos en context
		context.close();
	}

}
