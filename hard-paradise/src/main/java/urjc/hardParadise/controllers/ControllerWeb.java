package urjc.hardParadise.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import urjc.hardParadise.Comentario;
import urjc.hardParadise.Favorito;
import urjc.hardParadise.Montaje;
import urjc.hardParadise.Usuario;
import urjc.hardParadise.Valoracion;
import urjc.hardParadise.repositories.ComentarioRepository;
import urjc.hardParadise.repositories.FavoritoRepository;
import urjc.hardParadise.repositories.MontajeRepository;
import urjc.hardParadise.repositories.NoticiaRepository;
import urjc.hardParadise.repositories.UsuarioRepository;
import urjc.hardParadise.repositories.ValoracionRepository;



@Controller
public class ControllerWeb {
	
	@Autowired
	private UsuarioRepository repositoryUsuario;
	
	@Autowired
	private MontajeRepository repositoryMontaje;
	
	@Autowired
	private ComentarioRepository repositoryComentario;
	
	@Autowired
	private FavoritoRepository repositoryFavorito;	
	
	@Autowired
	private NoticiaRepository repositoryNoticia;
	
	@Autowired 
	private ValoracionRepository repositoryValoracion;
	
	@GetMapping("/verPerfil")
	public String verPerfil(Model model, HttpSession sesion) {

			
		Usuario usuario = (Usuario) sesion.getAttribute("Usuario");
		model.addAttribute("nombre",usuario.getNombre());
		model.addAttribute("correo",usuario.getCorreo());
		
		return "verPerfil";
	}
	
	@GetMapping("/builds")
	public String paginaBuilds(Model model,HttpSession sesion ) {
		model.addAttribute("builds",repositoryMontaje.findAll(new Sort(new Order(Sort.Direction.DESC, "id"))));
		
		return "builds";
	}
	
	@RequestMapping("/montaje")
	public String mostrarMontaje(Model model,@RequestParam long id, HttpSession sesion ) {
		//Long idL = Long.parseLong(id);
		int votos=0, valoracionMedia=0;
		
		Montaje montaje1 = repositoryMontaje.findOne(id);
		model.addAttribute("nombre",montaje1.getUsuario().getNombre());
		model.addAttribute("id",id);
		model.addAttribute("titulo",montaje1.getTitulo());
		model.addAttribute("Imagen", montaje1.getImagen());
		model.addAttribute("descripcion",montaje1.getDescripcion());
		List<Comentario> comentarios = repositoryComentario.findByMontaje(montaje1);
		model.addAttribute("comentarios",comentarios);
		List<Valoracion> valoraciones = repositoryValoracion.findByMontaje(montaje1);
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
	
	@RequestMapping("/inicio_sesion")
	public String iniciarSesion()
	{
			return "inicio_sesion";
	}
	@RequestMapping("/registro")
	public String registro()
	{
		return "registro";
	}
	@GetMapping("/inicio_error")
	public String inicioError()
	{
		return "inicio_error";
	}	
	@GetMapping("/logOut")
	public String logOut()
	{
		return "/cerrar_sesion";
	}
	@GetMapping("/")
	public String index()
	{
		return "/noticias";
	}
	

	@GetMapping("verFavoritos")
	public String verFavoritos(Model model,HttpSession sesion)
	{
		List<Montaje> montajes= new ArrayList<Montaje>();
		Usuario usuario = (Usuario) sesion.getAttribute("Usuario");
		
		List<Favorito> favoritos = repositoryFavorito.findByUsuario(usuario);
		
			for(Favorito favorito: favoritos)
			{
				montajes.add(favorito.getMontaje());
			}
			model.addAttribute("favoritos",montajes);
			return "favoritosPerfil";
	}
	
	@GetMapping("/noticias")
	public String paginaNoticias(Model model,HttpServletRequest request ) {
		
		model.addAttribute("noticias",repositoryNoticia.findAll());
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return "noticias";
	}

	@GetMapping("/crear_noticia")
	public String crearNoticia()
	{
		return "crear_noticia";
	}
	@GetMapping("/verPerfilInvitado")
	public String verPerfilInvitado (Model model, @RequestParam String id, HttpSession sesion)
	{
		Usuario usuario = repositoryUsuario.findOne(id);
		model.addAttribute("nombre",usuario.getNombre());
		List<Montaje> montajes = repositoryMontaje.findByUsuario(usuario);
		model.addAttribute("builds",montajes);
		return "verPerfilInvitado";
	}
}
