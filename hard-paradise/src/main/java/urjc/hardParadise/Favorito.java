package urjc.hardParadise;

import javax.persistence.*;


@Entity
public class Favorito {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Montaje montaje;
	
	public Favorito() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}
