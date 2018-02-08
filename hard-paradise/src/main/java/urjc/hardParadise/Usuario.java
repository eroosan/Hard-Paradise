package urjc.hardParadise;

import java.util.List;

import javax.persistence.*;


@Entity
public class Usuario {
	
	@Id
	private String nombre;

	private String contraseña;
	private String correo;
	
	@OneToMany(mappedBy="usuario")
	private List<Montaje> montajes;
	
	@ManyToMany
	private List<Usuario> seguidos;
	
	@OneToMany(mappedBy="usuario")
	private List<Favorito> favoritos;
	
	
	public Usuario() {
		
	}
	public Usuario(String nombre, String contraseña, String correo) {
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.correo = correo;
	}
/*
	public long getId() {
		return id;
	}

	
	public void setId(long id) {
		this.id = id;
	}
*/
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public void setMontajess(List<Montaje> montaje) {
		this.montajes = montaje;
	}
	public List<Montaje> getMontaje() {
		return montajes;
	}
	public List<Montaje> getMontajes() {
		return montajes;
	}
	public void setMontajes(List<Montaje> montajes) {
		this.montajes = montajes;
	}
	public List<Usuario> getSeguidos() {
		return seguidos;
	}
	public void setSeguidos(List<Usuario> seguidos) {
		this.seguidos = seguidos;
	}
	
	
	
}
