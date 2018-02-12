package urjc.hardParadise;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class ControllerUsuario {
	
	@Autowired
	private Usuariorepository repositoryUsuario;
	
	@Autowired
	private MontajeRepository repositoryMontaje;
	
	@Autowired
	private ComentarioRepository repositoryComentario;
	
	@Autowired
	private FavoritoRepository repositoryFavorito;
	
	@Autowired ValoracionRepository repositoryValoracion;
	
	
	@PostMapping("/guardarusuario")
	public String guardarAnuncio(Model model, @RequestParam String nombre, @RequestParam String contraseña,
			@RequestParam String correo) {

		repositoryUsuario.save(new Usuario(nombre,contraseña,correo));
		
		model.addAttribute("nombre", nombre);
		model.addAttribute("correo",correo);

		return "verPerfil";
	}
	@PostMapping("/inicioSesion")
	public String iniciarSesion(Model model, @RequestParam String nombre, @RequestParam String contraseña,HttpSession sesion ) {
		
		Usuario U1=repositoryUsuario.findOne(nombre);
		
		sesion.setAttribute("Usuario", U1);
		model.addAttribute("nombre",U1.getNombre());

		return "inicioSesion";
	}
	@GetMapping("/verPerfil")
	public String verPerfil(Model model, HttpSession sesion ) {
		
		Usuario usuario = (Usuario) sesion.getAttribute("Usuario");
		model.addAttribute("nombre",usuario.getNombre());
		model.addAttribute("correo",usuario.getCorreo());
		
		return "verPerfil";
	}
	
	@PostMapping("/guardarMontaje")
	public String 	guardarMontaje(Model model, @RequestParam String descripcion, @RequestParam String imagen, HttpSession sesion ) throws SerialException, SQLException 
	{
	/*	File file =new File(imagen);
		Blob blob = null;
		byte[] imagenByte=null;
		if(file.exists()){
			try {
				BufferedImage bufferedImage=ImageIO.read(file);
				ByteArrayOutputStream byteOutStream=new ByteArrayOutputStream();
				ImageIO.write(bufferedImage, "png", byteOutStream);
				imagenByte=byteOutStream.toByteArray();
			    blob = new javax.sql.rowset.serial.SerialBlob(imagenByte);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		Montaje montaje1 = new Montaje(descripcion,imagen,0.0);
		montaje1.setUsuario((Usuario) sesion.getAttribute("Usuario"));
		montaje1.setImagen(imagen);
		model.addAttribute("Imagen", montaje1.getImagen());
		model.addAttribute("Descripcion",montaje1.getDescripcion());
		repositoryMontaje.save(montaje1);
	
			return "nuevo_montaje_guardado";
	}
	@GetMapping("/builds")
	public String paginaBuilds(Model model,HttpSession sesion ) {
		model.addAttribute("builds",repositoryMontaje.findAll());
		
		return "builds";
	}
	@RequestMapping("/montaje")
	public String mostrarMontaje(Model model,@RequestParam long id, HttpSession sesion ) {
		//Long idL = Long.parseLong(id);
		int votos=0, valoracionMedia=0;
		
		Montaje montaje1 = repositoryMontaje.findOne(id);
		model.addAttribute("id",montaje1.getId());
		model.addAttribute("imagen",montaje1.getImagen());
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
		
		model.addAttribute("id",montaje.getId());
		model.addAttribute("imagen",montaje.getImagen());
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
	
	@PostMapping("/marcarFavorito")
	public String marcarFavorito(Model model,@RequestParam long id, HttpSession sesion)
	{
		int votos=0;
		double valoracionMedia=0;

		Montaje montaje = repositoryMontaje.findOne(id);
		if(sesion.getAttribute("Usuario")!= null)	
		{
			Favorito favorito = new Favorito();
			favorito.setUsuario((Usuario) sesion.getAttribute("Usuario"));
			favorito.setMontaje(montaje);
			repositoryFavorito.save(favorito);
		}
		
		model.addAttribute("id",montaje.getId());
		model.addAttribute("imagen",montaje.getImagen());
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
	
	@PostMapping("/enviarValoracion")
	public String enviarValoracion(Model model, @RequestParam long id,  @RequestParam int valorar,HttpSession sesion)
	{
		int votos=0;
		double valoracionMedia=0;
		Montaje montaje = repositoryMontaje.findOne(id);
		if(sesion.getAttribute("Usuario")!= null)	
		{
			Valoracion valoracion = new Valoracion(valorar);
			valoracion.setMontaje(montaje);
			repositoryValoracion.save(valoracion);
		}
		model.addAttribute("id",montaje.getId());
		model.addAttribute("imagen",montaje.getImagen());
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
			List<Usuario> seguidos = usuariopropio.getSeguidos();
			seguidos.add(usuario);
			usuariopropio.setSeguidos(seguidos);
		}
		
		model.addAttribute("id",montaje.getId());
		model.addAttribute("imagen",montaje.getImagen());
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
