package net.itinajero.app.service;

import net.itinajero.app.model.Detalle;

public interface IDetallesService {

	void insertar(Detalle detalle);
	void eliminar(int idDetalle);
}
