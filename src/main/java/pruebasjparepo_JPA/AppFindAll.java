package pruebasjparepo_JPA;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

public class AppFindAll {

	public static void main(String[] args) 
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		List<Noticia> lista = repo.findAll();
		
		for(Noticia n: lista)
		{
			System.out.println(n);
		}
		
		context.close();
	}
}
