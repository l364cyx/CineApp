package pruebasrelaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Horario;
import net.itinajero.app.model.Pelicula;
import net.itinajero.app.repository.HorariosRepository;
import net.itinajero.app.repository.PeliculasRepository;

public class AppGetHorariosPelicula {

	public static void main(String[] args) 
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		//Recuperar todas las entidades de tipo Horario
		PeliculasRepository repo = context.getBean("peliculasRepository", PeliculasRepository.class);

		Optional<Pelicula> optional = repo.findById(7);
		
//		System.out.println(optional.get().getHorarios().size());
		
		context.close();

	}

}
