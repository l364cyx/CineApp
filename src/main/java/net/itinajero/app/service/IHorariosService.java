package net.itinajero.app.service;

import java.util.Date;
import java.util.List;

import net.itinajero.app.model.Horario;

public interface IHorariosService {

	List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha);
}
