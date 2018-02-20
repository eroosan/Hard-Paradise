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
import urjc.hardParadise.repositories.Usuariorepository;
import urjc.hardParadise.repositories.ValoracionRepository;



@Controller
public class ControllerUsuario {
	
	@Autowired
	private Usuariorepository repositoryUsuario;
	
	@Autowired
	private MontajeRepository repositoryMontaje;
	
	@Autowired
	private ComentarioRepository repositoryComentario;
	
	@Autowired 
	private ValoracionRepository repositoryValoracion;
	
	
	@PostMapping("/guardarusuario")
	public String guardarAnuncio(Model model, @RequestParam String nombre, @RequestParam String contraseña,
			@RequestParam String correo) {

		repositoryUsuario.save(new Usuario(nombre,contraseña,correo));
		
		model.addAttribute("nombre", nombre);
		model.addAttribute("correo",correo);

		return "verPerfil";
	}
	
	@PostMapping("/seguirUsuario")
	public String seguirUsuario(Model model, @RequestParam long id,HttpSession sesion)
	{
		int votos=0;
		double valoracionMedia=0;
		
		Montaje montaje = repositoryMontaje.findOne(id);
		Usuario usuariopropio = (Usuario) sesion.getAttribute("Usuario");
		if(sesion.getAttribute("Usuario")!= null)	
		{
			Usuario usuario = montaje.getUsuario();
			List<Usuario> seguidos = repositoryUsuario.findBySeguidos(usuariopropio);
			seguidos.add(usuario);
			usuariopropio.setSeguidos(seguidos);
			repositoryUsuario.save(usuariopropio);
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
		
		return "montaje";

	}

}
