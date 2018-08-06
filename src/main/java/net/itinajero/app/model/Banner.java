/**
 * Clase que representa una imagen del Banner (Carousel) de la pagina principal
 */
package net.itinajero.app.model;

import java.util.Date;

public class Banner {

	private int id;
	private String titulo;
	private Date fecha; // Fecha de Publicacion del Banner
	private String archivo; // atributo para guardar el nombre de la imagen
	private String estatus;
	
	/**
	 * Constructor de la clase
	 */
	public Banner(){		
		this.fecha = new Date(); // Por default, la fecha del sistema
		this.estatus="Activo"; // Por default el banner esta activo
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "Banner [id=" + id + ", titulo=" + titulo + ", fecha=" + fecha + ", archivo=" + archivo + ", estatus="
				+ estatus + "]";
	}	
		
}
