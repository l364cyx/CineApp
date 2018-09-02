package net.itinajero.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.itinajero.app.model.Banner;
//@Service
public class BannersServiceImpl implements IBannersService {

	private List<Banner> lista = null; 
	/**
	 * En el constructor creamos una lista enlazada con objetos de tipo Banner
	 */
	public BannersServiceImpl() {
		
		System.out.println("Creando una instancia de la clase BannersServiceImpl");
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
		// Ejercicio: Crear una nueva lista enlazada		
		lista = new LinkedList<Banner>();
		// Ejercicio: Crear algunos objetos de tipo Banner (estaticos)
		Banner banner1 = new Banner();
		banner1.setId(1);
		banner1.setTitulo("Slide 1");
		banner1.setFecha(formatter.parse("06/08/2018"));
		banner1.setArchivo("slide1.jpg");
		banner1.setEstatus("Activo");
		
		Banner banner2 = new Banner();
		banner2.setId(1);
		banner2.setTitulo("Slide 2");
		banner2.setFecha(formatter.parse("07/08/2018"));
		banner2.setArchivo("slide2.jpg");
		banner2.setEstatus("Activo");
		
		Banner banner3 = new Banner();
		banner3.setId(1);
		banner3.setTitulo("Slide 3");
		banner3.setFecha(formatter.parse("08/08/2018"));
		banner3.setArchivo("slide3.jpg");
		banner3.setEstatus("Activo");
		
		Banner banner4 = new Banner();
		banner4.setId(1);
		banner4.setTitulo("Slide 4");
		banner4.setFecha(formatter.parse("09/08/2018"));
		banner4.setArchivo("slide4.jpg");
		banner4.setEstatus("Activo");
		
		
		// Ejercicio: Agregar los objetos Banner a la lista
		lista.add(banner1);
		lista.add(banner2);
		lista.add(banner3);
		lista.add(banner4);
		
		} catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/**
	 * Insertamos un objeto de tipo Banner a la lista
	 */
	@Override
	public void insertar(Banner banner) {
		
		lista.add(banner);
		
	}

	/**
	 * Regresamos la lista de objetos Banner
	 */
	@Override
	public List<Banner> buscarTodos() {
		
		return lista;
		
	}

	@Override
	public List<Banner> buscarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(int idBanner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Banner buscarPorId(int idBanner) {
		// TODO Auto-generated method stub
		return null;
	}

}
