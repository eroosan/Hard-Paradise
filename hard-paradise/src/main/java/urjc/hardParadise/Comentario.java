package urjc.hardParadise;

import javax.persistence.*;

@Entity
public class Comentario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String textoComentario;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Montaje montaje;
	
	public Comentario(){
		
	}
	
	public Comentario(String texto ) {
		this.textoComentario = texto;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public long getId() {
		return id;
	}

	public String getTextoComentario() {
		return textoComentario;
	}

	public void setTextoComentario(String textoComentario) {
		this.textoComentario = textoComentario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Montaje getMontaje() {
		return montaje;
	}

	public void setMontaje(Montaje montaje) {
		this.montaje = montaje;
	}

	

}
