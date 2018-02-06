package urjc.hardParadise;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ControllerUsuario {
	
	@Autowired
	private Usuariorepository repositoryUsuario;
	
	@Autowired
	private MontajeRepository repositoryMontaje;
	
	
	@PostConstruct
	public void init() {
		Usuario usuario1 = repositoryUsuario.save(new Usuario("Pepe", "Hola caracola", "XXXX"));
		Usuario usuario2 = repositoryUsuario.save(new Usuario("Juan", "Hola caracola", "XXXX"));
		
		repositoryMontaje.save(new Montaje("des","imagen",10.0,usuario1));
		repositoryMontaje.save(new Montaje("des","imagen",10.0,usuario2));
		
		
		
	
	}
	@PostMapping("/guardarusuario")
	public String guardarAnuncio(Model model, @RequestParam String nombre, @RequestParam String contraseña,
			@RequestParam String correo) {

		repositoryUsuario.save(new Usuario(nombre,contraseña,correo));
		
		model.addAttribute("usuarios", repositoryUsuario.findAll());

		return "anuncio";
	}
}
