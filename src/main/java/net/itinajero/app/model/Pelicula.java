package net.itinajero.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Peliculas")
public class Pelicula {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Auto-incrementable
	private int id;
	private String titulo;
	private int duracion=100;
	private String clasificacion="B";
	private String genero;
	private String imagen = "cinema.png"; // imagen por default	
	private Date fechaEstreno;	
	private String estatus="Activa";
	
	//@Transient  //Se usa para indicar que un atributo no debe ser persistente y no es tomado en cuenta al persistir el objeto
	@OneToOne
	@JoinColumn(name = "idDetalle")//Relacion 1 a 1 entre la entidad Película y la Entidad Detalle
	private Detalle detalle;
	
	
	/*
	 * Con la constante EAGER le decimos que cada vez que consultemos un registro de tipo Pelicula queremos que también se ejecute una consulta 
	 * en la tabla Horarios para traernos todos los horarios que pertenezcan a la Película que estamos consultando.
	 */
	@OneToMany(mappedBy="pelicula", fetch= FetchType.EAGER)
	private List<Horario> horarios;
	
	public List<Horario> getHorarios() {
		return horarios;
	}
	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
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
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public Date getFechaEstreno() {
		return fechaEstreno;
	}
	public void setFechaEstreno(Date fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public Detalle getDetalle() {
		return detalle;
	}
	public void setDetalle(Detalle detalle) {
		this.detalle = detalle;
	}
	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", duracion=" + duracion + ", clasificacion="
				+ clasificacion + ", genero=" + genero + ", imagen=" + imagen + ", fechaEstreno=" + fechaEstreno
				+ ", estatus=" + estatus + ", detalle=" + detalle + "]";
	}


	
}
