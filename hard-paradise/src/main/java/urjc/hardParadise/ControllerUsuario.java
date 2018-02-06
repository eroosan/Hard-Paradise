package urjc.hardParadise;


import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ControllerUsuario {
	
	@Autowired
	private Usuariorepository repositoryUsuario;
	
	@Autowired
	private MontajeRepository repositoryMontaje;
	
	
	@PostConstruct
	public void init() {
		Usuario usuario1 = repositoryUsuario.save(new Usuario("doke", "1234", "XXXX"));
		Usuario usuario2 = repositoryUsuario.save(new Usuario("HULIO", "CABESA", "XXXX"));
		
		repositoryMontaje.save(new Montaje("des","imagen",10.0,usuario1));
		repositoryMontaje.save(new Montaje("des","imagen",10.0,usuario2));
	
	}
	
	@PostMapping("/guardarusuario")
	public String guardarAnuncio(Model model, @RequestParam String nombre, @RequestParam String contraseña,
			@RequestParam String correo) {

		repositoryUsuario.save(new Usuario(nombre,contraseña,correo));
		
		model.addAttribute("usuarios", repositoryUsuario.findAll());

		return "verPerfil";
	}
	@PostMapping("/inicioSesion")
	public String iniciarSesion(Model model, @RequestParam String nombre, @RequestParam String contraseña,HttpSession sesion ) {
		
		Usuario U1=repositoryUsuario.findOne(nombre);
		
		sesion.setAttribute("Usuario", U1);
		model.addAttribute("nombre",U1.getNombre());

		return "inicioSesion";
	}
	@PostMapping("/verPerfil")
	public String verPerfil(Model model, @RequestParam String nombre, @RequestParam String correo,HttpSession sesion ) {
		
		Usuario U1=repositoryUsuario.findOne(nombre);
		
		sesion.setAttribute("Usuario", U1);
		model.addAttribute("nombre",U1.getNombre());
		model.addAttribute("correo",U1.getCorreo());
		return "verPerfil";
	}
}
