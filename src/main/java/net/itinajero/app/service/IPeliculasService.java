package net.itinajero.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.itinajero.app.model.Pelicula;

public interface IPeliculasService 
{
	void insertar(Pelicula pelicula);
	List<Pelicula> buscarTodas();
	Pelicula buscarPorId(int idPelicula);
	List<String> buscarGeneros();
	void eliminar(int idPelicula);
	Page<Pelicula> buscarTodas(Pageable page);
	
	// Con este metodo traemos las peliculas Activas. Para formar el select de Peliculas del FORM de nuevo Horario.
    List<Pelicula> buscarActivas();	
	List<Pelicula> buscarPorFecha(Date fecha);
}
