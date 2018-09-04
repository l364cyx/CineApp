package pruebasrelaciones_JPA;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Detalle;
import net.itinajero.app.repository.DetallesRepository;
import net.itinajero.app.repository.PeliculasRepository;

public class AppRepoDetalles {

	public static void main(String[] args) {
	
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		//Recuperar todas las entidades de tipo Detalle
		DetallesRepository repo = context.getBean("detallesRepository", DetallesRepository.class);//instancia del repositorio
		
		List<Detalle> lista = repo.findAll();
		
		for(Detalle d: lista)
		{
			System.out.println(d);
		}

		
		context.close();
	}

}
