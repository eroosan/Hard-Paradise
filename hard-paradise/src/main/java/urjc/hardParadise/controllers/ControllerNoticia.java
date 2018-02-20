package urjc.hardParadise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import urjc.hardParadise.Noticia;
import urjc.hardParadise.repositories.NoticiaRepository;

@Controller
public class ControllerNoticia {
	
	
	@Autowired
	private NoticiaRepository repositoryNoticia;
	
	
	@PostMapping("/guardarNoticia")
	public String guardarNoticia(Model model, @RequestParam String noticia) {

		Noticia noticia1 =new Noticia();
		noticia1.setNoticia(noticia);
		repositoryNoticia.save(noticia1);
		
		model.addAttribute("noticia", noticia);
		model.addAttribute("noticias",repositoryNoticia.findAll());
		return "noticias";
	}

}
