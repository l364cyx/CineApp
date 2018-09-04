package pruebasrelaciones_JPA;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Horario;
import net.itinajero.app.repository.HorariosRepository;

public class AppRepoHorarios {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		//Recuperar todas las entidades de tipo Horario
		HorariosRepository repo = context.getBean("horariosRepository", HorariosRepository.class);

		List<Horario> list = repo.findAll();
		
		System.out.println("No. de entidades " + list.size());
		
		for(Horario h: list)
		{
			System.out.println(h);
		}
		
		context.close();

	}

}
