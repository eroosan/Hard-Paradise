package urjc.hardParadise;

import javax.persistence.*;

@Entity
public class Noticia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String noticia;
	
	public Noticia() {
		
	}
	public void setId(long id)
	{
		this.id=id;
	}
	public long getId()
	{
		return this.id;
	}
	public void setNoticia(String noticia)
	{
		this.noticia=noticia;
	}
	public String getNoticia()
	{
		return this.noticia;
	}
}
