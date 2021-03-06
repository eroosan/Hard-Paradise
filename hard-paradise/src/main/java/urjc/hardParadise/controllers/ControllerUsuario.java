package urjc.hardParadise.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import urjc.hardParadise.Comentario;
import urjc.hardParadise.Montaje;
import urjc.hardParadise.Usuario;
import urjc.hardParadise.Valoracion;
import urjc.hardParadise.repositories.ComentarioRepository;
import urjc.hardParadise.repositories.MontajeRepository;
import urjc.hardParadise.repositories.UsuarioRepository;
import urjc.hardParadise.repositories.ValoracionRepository;



@Controller
public class ControllerUsuario {
	
	@Autowired
	private UsuarioRepository repositoryUsuario;
	
	@Autowired
	private MontajeRepository repositoryMontaje;
	
	@Autowired
	private ComentarioRepository repositoryComentario;
	
	@Autowired 
	private ValoracionRepository repositoryValoracion;
	
	
	@PostMapping("/guardarusuario")
	public String guardarAnuncio(Model model, @RequestParam String nombre, @RequestParam String contraseña,
			@RequestParam String correo, HttpServletRequest request) {

		repositoryUsuario.save(new Usuario(nombre,contraseña,correo,"USER"));
		
		//wrequest.setAttribute("correo", correo);
		//model.addAttribute("correo",correo);
		RestTemplate rt=new RestTemplate();
	    String url= "http://192.168.33.13:8080/envioCorreo?correo="+correo+"&nombre="+nombre;
	    Boolean b=rt.getForObject(url, Boolean.class);
		
		return "noticias";
	}
	
	@PostMapping("/seguirUsuario")
	public String seguirUsuario(Model model, @RequestParam long id,HttpSession sesion,Authentication authentication)
	{
		int votos=0;
		double valoracionMedia=0;
		
		Montaje montaje = repositoryMontaje.findOne(id);
		Usuario usuariopropio = repositoryUsuario.findByNombre(authentication.getName());
		
			Usuario usuario = montaje.getUsuario();
			List<Usuario> seguidos = repositoryUsuario.findBySeguidos(usuariopropio);
			seguidos.add(usuario);
			usuariopropio.setSeguidos(seguidos);
			repositoryUsuario.save(usuariopropio);
		
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
