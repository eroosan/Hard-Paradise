package urjc.hardParadise;

import javax.persistence.*;

@Entity
public class Montaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//private Usuario usuario;
	private String descripcion;
	private String imagen;
	private Double valoracion;
	
	
	public Montaje() {
		
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	/*public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}*/


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public Double getValoracion() {
		return valoracion;
	}


	public void setValoracion(Double valoracion) {
		this.valoracion = valoracion;
	}

}
