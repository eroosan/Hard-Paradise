package urjc.hardParadise;

import java.util.List;

import javax.persistence.*;

@Entity
public class Montaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	private String descripcion;
	private String imagen;
	private Double valoracion;
	
	@ManyToOne
	private Usuario usuario;
	
	


	@OneToMany(mappedBy="montaje")
	private List<Comentario> comentarios;
	
	
	public Montaje() {
		
	}
	
	public Montaje(String descripcion, String imagen, Double valoracion)
	{
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.valoracion = valoracion;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


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
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
