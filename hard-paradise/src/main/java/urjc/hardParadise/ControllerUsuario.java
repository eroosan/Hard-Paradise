package urjc.hardParadise;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ControllerUsuario {
	
	@Autowired
	private Usuariorepository repository;
	
	

	@PostMapping("/guardarusuario")
	public String guardarAnuncio(Model model, @RequestParam String nombre, @RequestParam String contraseña,
			@RequestParam String correo) {

		repository.save(new Usuario(nombre,contraseña,correo));
		
		model.addAttribute("usuarios", repository.findAll());

		return "anuncio";
	}
}
