package net.itinajero.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.itinajero.app.model.Pelicula;

/** Marcamos esta clase como un Bean de tipo Repository en nuestro Root ApplicationContext.
	Nota: La anotacion @Repository es opcional ya que al extender la interfaz JpaRepository Spring 
	crea una instancia en nuestro Root ApplicationContext.
*/
@Repository
public interface PeliculasRepository extends JpaRepository<Pelicula, Integer> {

	// Listado de peliculas filtradas por estatus
	public List<Pelicula> findByEstatus_OrderByTitulo(String estatus);
}
