package net.itinajero.app.service;

import java.util.List;
import net.itinajero.app.model.Banner;

public interface IBannersService {

	void insertar(Banner banner); 
	List<Banner> buscarTodos();
	List<Banner> buscarActivos();
	void eliminar(int idBanner);
	Banner buscarPorId(int idBanner);
}
