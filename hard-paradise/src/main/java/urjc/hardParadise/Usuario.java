package urjc.hardParadise;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
public class Usuario {
	
	@Id
	private String nombre;

	private String passwordHash;
	private String correo;
	
	@OneToMany(mappedBy="usuario")
	private List<Montaje> montajes;
	
	@ManyToMany
	private List<Usuario> seguidos;
	
	@OneToMany(mappedBy="usuario")
	private List<Favorito> favoritos;
	
	@ElementCollection(fetch= FetchType.EAGER)
	private List<String> roles= new ArrayList<String>();
	
	
	public Usuario() {
		
	}
	public Usuario(String nombre, String password, String correo, String rol) {
		this.nombre = nombre;
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
		this.correo = correo;
		this.roles.add(rol);
		
	}
	public Usuario(String nombre, String password, String correo, String rol,String rol2) {
		this.nombre = nombre;
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
		this.correo = correo;
		this.roles.add(rol);
		this.roles.add(rol2);
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return passwordHash;
	}

	public void setContraseña(String contraseña) {
		this.passwordHash = contraseña;
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
	public void añadirSeguido(Usuario usuario)
	{
		seguidos.add(usuario);
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
	
}
