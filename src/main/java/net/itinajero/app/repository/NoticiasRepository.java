package net.itinajero.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.itinajero.app.model.Noticia;

/**
 * Marcamos esta clase como un Bean de tipo Repository en nuestro Root
 * ApplicationContext. Nota: La anotacion @Repository es opcional ya que al
 * extender la interfaz JpaRepository Spring crea una instancia en nuestro Root
 * ApplicationContext.
 */

/*
 * Crea un Bean en nuestro contenedor de Beans. Con esto le decimos a Spring que cree un Bean para operaciones con BD
 */
@Repository
//public interface NoticiasRepository extends CrudRepository<Noticia, Integer> { //<ENTIDADAD, TIPO CLAVE PRIMARIA>
public interface NoticiasRepository extends JpaRepository<Noticia, Integer> {
	//Spring Data JPA nos ofrece otros métodos adicionales a CrudRepository
	
	//Select * from Noticias
	public List<Noticia> findBy();
	
	//Select * from Noticias where status = ?
	public List<Noticia> findByEstatus(String status);
	
	// where fecha = ?
	public List<Noticia> findByFecha(Date fecha);
	
	// where status =? and fecha = ?
	public List<Noticia> findByEstatusAndFecha(String status, Date fecha);
	
	// where status =? and fecha = ?
	public List<Noticia> findByEstatusOrFecha(String status, Date fecha);
	
	// where fecha between ? and ?
	public List<Noticia> findByFechaBetween(Date fecha1, Date fecha2);
	
	// where fecha between ? and ?
	public List<Noticia> findByIdBetween(int n1, int n2);

	// select * from Noticias where estatus = ? order by id desc limit 3
	public List<Noticia> findTop3ByEstatusOrderByIdDesc(String string);
}
