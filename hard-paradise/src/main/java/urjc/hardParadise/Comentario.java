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
	
	public Comentario(){
		
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public long getId() {
		return id;
	}

	

}
