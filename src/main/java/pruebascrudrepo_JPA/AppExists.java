package pruebascrudrepo_JPA;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import net.itinajero.app.repository.NoticiasRepository;

public class AppExists {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		// Metodo para verificar si una entidad existe en la base de datos [metodo existsById del repositorio]
		int idNoticia=1;
		System.out.println(repo.existsById(idNoticia));
		
		context.close();
	}

}
