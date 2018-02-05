package urjc.hardParadise;

import javax.persistence.*;

@Entity
public class Comentario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//private Usuario usuario;
	
	public Comentario(){
		
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUsuario(Usuario usuario) {
		//this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	//public Usuario getUsuario() {
		//return usuario;
	//}
	

}
