package urjc.hardParadise;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ControllerPerfil {
	
	@RequestMapping("/perfil/{nombre}")
	public String perfil(Model model, @PathVariable String nombre)
	{
	
		
		return "perfil";
	}
	
}
