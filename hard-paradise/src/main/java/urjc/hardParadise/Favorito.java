package urjc.hardParadise;

import javax.persistence.*;


@Entity
public class Favorito {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	/*private Usuario usuario;
	private Montaje montaje;*/
	
	public Favorito() {
		
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
	}

	public Montaje getMontaje() {
		return montaje;
	}

	public void setMontaje(Montaje montaje) {
		this.montaje = montaje;
	}
	*/

}
