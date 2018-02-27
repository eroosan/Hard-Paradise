package urjc.hardParadise;

import javax.persistence.*;

@Entity
public class Valoracion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private int valoracion; 
	
	@ManyToOne
	private Montaje montaje;
	
	public Valoracion()
	{
		
	}
	
	public Valoracion(int valoracion)
	{
		this.valoracion = valoracion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Montaje getMontaje() {
		return montaje;
	}

	public void setMontaje(Montaje montaje) {
		this.montaje = montaje;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	

}
