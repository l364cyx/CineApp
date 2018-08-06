package net.itinajero.app.model;

public class Detalle {
	
	private int id;
	private String director;
	private String actores;
	private String sinopsis;
	private String trailer;
	
	public Detalle() {
		//System.out.println("Constructor Detalle");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActores() {
		return actores;
	}

	public void setActores(String actores) {
		this.actores = actores;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	@Override
	public String toString() {
		return "Detalle [id=" + id + ", director=" + director + ", actores=" + actores + ", sinopsis=" + sinopsis
				+ ", trailer=" + trailer + "]";
	}
	
}
