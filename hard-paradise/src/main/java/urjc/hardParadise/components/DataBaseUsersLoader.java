package urjc.hardParadise.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import urjc.hardParadise.Usuario;
import urjc.hardParadise.repositories.UsuarioRepository;

@Component
public class DataBaseUsersLoader {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@PostConstruct
	private void initDataBase()
	{
	  	userRepository.save(new Usuario("user", "pass","email", "ROLE_USER"));
	  	userRepository.save(new Usuario("admin", "adminpass", "email","ROLE_USER", "ROLE_ADMIN"));
	}
}
