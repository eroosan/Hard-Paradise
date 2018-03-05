package urjc.hardParadise.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import urjc.hardParadise.Comentario;
import urjc.hardParadise.Montaje;
import urjc.hardParadise.Usuario;
import urjc.hardParadise.Valoracion;
import urjc.hardParadise.repositories.ComentarioRepository;
import urjc.hardParadise.repositories.MontajeRepository;
import urjc.hardParadise.repositories.UsuarioRepository;
import urjc.hardParadise.repositories.ValoracionRepository;

@Controller
public class ControllerMontaje {
	
	@Autowired
	private MontajeRepository repositoryMontaje;
	
	@Autowired
	private ComentarioRepository repositoryComentario;
	
	@Autowired 
	private ValoracionRepository repositoryValoracion;
	
	@Autowired
	private UsuarioRepository repositoryUsuario;
	
	@GetMapping("/nuevo_montaje")
	public String crearMontaje()
	{
		return "nuevo_montaje";
	}
	@PostMapping("/guardarMontaje")
	public String 	guardarMontaje(Model model,@RequestParam String titulo, @RequestParam String descripcion, @RequestParam MultipartFile imagen,HttpSession sesion, HttpServletRequest request ) throws SerialException, SQLException 
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
			}/
		}*/
		
		File outputFile = null;
		try {
			outputFile = new File("src/main/resources/static/imagenes/"+sesion.getId()+imagen.getOriginalFilename());
			outputFile.createNewFile();
			System.out.println(outputFile);
			BufferedImage bufferedImage=ImageIO.read(imagen.getInputStream());
			ImageIO.write(bufferedImage, "png", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Montaje montaje1 = new Montaje(titulo,descripcion,outputFile.getPath(),0.0);
		Usuario usuario = repositoryUsuario.findByNombre(request.getUserPrincipal().getName());
		montaje1.setUsuario(usuario);
		montaje1.setImagen(outputFile.getName());
		model.addAttribute("Imagen", montaje1.getImagen());
		model.addAttribute("Descripcion",montaje1.getDescripcion());
		repositoryMontaje.save(montaje1);
	
			return "nuevo_montaje_guardado";
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
		model.addAttribute("nombre",montaje.getUsuario().getNombre());
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
