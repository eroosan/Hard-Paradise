package urjc.hardParadise.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import urjc.hardParadise.Usuario;
import urjc.hardParadise.repositories.UsuarioRepository;

@Component
public class DataBaseUsersLoader {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostConstruct
	private void initDataBase()
	{
		usuarioRepository.save(new Usuario("admin","admin","correo","ROLE_ADMIN"));
		usuarioRepository.save(new Usuario("usuario","1234","correo","ROLE_USER"));

	}
}
