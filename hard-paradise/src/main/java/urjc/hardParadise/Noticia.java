package urjc.hardParadise;

import javax.persistence.*;

@Entity
public class Noticia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	public Noticia() {
		
	}

}
