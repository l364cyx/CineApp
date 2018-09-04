/**
 *  Clase de modelo que representa una noticia en la seccion Noticias / Novedades de la pagina principal
 */
package net.itinajero.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="Noticias")
public class Noticia {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY) // auto_increment MySQL
	private int id;
	private String titulo;
	private Date fecha;
	private String detalle;
	private String estatus;// posibles valores: Activa, Inactiva
	
	public Noticia(){
		this.fecha = new Date(); // por default la fecha del sistema
		this.estatus="Activa"; // por default la noticia esta Activa
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

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "Noticia [id=" + id + ", titulo=" + titulo + ", fecha=" + fecha + ", detalle=" + detalle + ", estatus="
				+ estatus + "]";
	}
	
	
	
}