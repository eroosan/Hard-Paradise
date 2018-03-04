package urjc.hardParadise.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import urjc.hardParadise.Usuario;

@Component
public class UsuarioRepositoryAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UsuarioRepository repositoryUsuario ;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
			Usuario usuario= repositoryUsuario.findByNombre(auth.getName());
			if(usuario==null)
			{
				throw new BadCredentialsException("User not found");				
			}
			String password = (String) auth.getCredentials();
			if(!new BCryptPasswordEncoder().matches(password, usuario.getContraseña()))
			{
				throw new BadCredentialsException("User not found");
			}
			List<GrantedAuthority> roles = new ArrayList<>();
			for(String rol : usuario.getRoles())
			{
				roles.add(new SimpleGrantedAuthority(rol));
			}
			
			return new UsernamePasswordAuthenticationToken(usuario.getNombre(),password,roles) ;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
