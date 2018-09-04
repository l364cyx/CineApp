package net.itinajero.app.service;

import java.util.List;
import net.itinajero.app.model.Noticia;

public interface INoticiasService {

	void guardar(Noticia noticia);
	List<Noticia> buscarUltimas();
	List<Noticia> buscarTodas();
	void eliminar(int idNoticia);
	Noticia buscarPorId(int idNoticia);
}
