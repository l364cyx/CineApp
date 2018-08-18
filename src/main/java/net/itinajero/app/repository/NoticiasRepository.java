package net.itinajero.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.itinajero.app.model.Noticia;

@Repository//Crea un Bean en nuestro contenedor de Beans. Con esto le decimos a Spring que cree un Bean para operaciones con BD
//public interface NoticiasRepository extends CrudRepository<Noticia, Integer> { //<ENTIDADAD, TIPO CLAVE PRIMARIA>

public interface NoticiasRepository extends JpaRepository<Noticia, Integer> {
	//Spring Data JPA nos ofrece otros métodos adicionales a CrudRepository
	
	public List<Noticia> findByFecha(Date d);
}
