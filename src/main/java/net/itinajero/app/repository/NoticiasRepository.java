package net.itinajero.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.itinajero.app.model.Noticia;

@Repository//Crea un Bean en nuestro contenedor de Beans. Con esto le decimos a Spring que cree un Bean para operaciones con BD
public interface NoticiasRepository extends CrudRepository<Noticia, Integer> {

	
	
}
