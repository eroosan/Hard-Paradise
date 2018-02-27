package urjc.hardParadise.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import urjc.hardParadise.Comentario;
import urjc.hardParadise.Montaje;
import urjc.hardParadise.Usuario;
import urjc.hardParadise.Valoracion;
import urjc.hardParadise.repositories.ComentarioRepository;
import urjc.hardParadise.repositories.MontajeRepository;
import urjc.hardParadise.repositories.ValoracionRepository;

@Controller
public class ControllerComentario {
	@Autowired
	private MontajeRepository repositoryMontaje;
	
	@Autowired
	private ComentarioRepository repositoryComentario;
	
	@Autowired 
	private ValoracionRepository repositoryValoracion;
	
	@PostMapping("/guardarComentario")
	public String guardarComentario(Model model,@RequestParam long id,@RequestParam String textoComentario, HttpSession sesion)
	{
		int votos=0;
		double valoracionMedia=0;
		Montaje montaje = repositoryMontaje.findOne(id);
		Usuario usuario= (Usuario) sesion.getAttribute("Usuario");
		if(textoComentario!="" && usuario!=null )
		{
			Comentario comentario = new Comentario(textoComentario);
			comentario.setMontaje(montaje);
			comentario.setUsuario((Usuario) sesion.getAttribute("Usuario"));
			repositoryComentario.save(comentario);
		}
		model.addAttribute("nombre",montaje.getUsuario().getNombre());
		model.addAttribute("usuario.nombre",montaje.getUsuario().getNombre());
		model.addAttribute("id",id);
		model.addAttribute("titulo",montaje.getTitulo());
		model.addAttribute("Imagen", montaje.getImagen());
		model.addAttribute("descripcion",montaje.getDescripcion());
		List<Comentario> comentarios = repositoryComentario.findByMontaje(montaje);
		model.addAttribute("comentarios",comentarios);
		
		List<Valoracion> valoraciones = repositoryValoracion.findByMontaje(montaje);
		if(!valoraciones.isEmpty() )
		{
			for(Valoracion valoracion:valoraciones)
			{
				votos++;
				valoracionMedia += valoracion.getValoracion();
			}
			valoracionMedia = valoracionMedia/votos;
		}
		model.addAttribute("valoracion",valoracionMedia);
		model.addAttribute("nVotos",votos);
		return"montaje";
	}

}
