package pruebasjparepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.repository.NoticiasRepository;

public class AppDeleteAllInBatch {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		/* Metodo deleteAllInBatch de la interfaz JpaRepository -> (es más eficiente)
			delete from Noticias	
		*/
		
		/* Metodo deleteAll de la interfaz CrudRepository -> (no muy eficiente)
			delete from Noticias where id=?
			delete from Noticias where id=?
			delete from Noticias where id=?		
		*/
		repo.deleteAllInBatch();
		
		context.close();		
		
	}

}
