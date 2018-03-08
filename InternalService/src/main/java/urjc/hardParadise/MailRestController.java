package urjc.hardParadise;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailRestController {
	@CrossOrigin
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/envioCorreo", method = RequestMethod.GET) 
	public ResponseEntity<Boolean> sendMail(@RequestParam String correo, @RequestParam String nombre) {
		
		System.out.println("Correo: "+correo+" nombre: "+nombre);
		
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
}
}
