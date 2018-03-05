package urjc.hardParadise.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import urjc.hardParadise.repositories.UsuarioRepositoryAuthenticationProvider;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public UsuarioRepositoryAuthenticationProvider authenticationProvider;
	
	@Override
	 protected void configure(HttpSecurity http) throws Exception 
	{
		 // Public pages	
		
		 http.authorizeRequests().antMatchers("/").permitAll();
		 http.authorizeRequests().antMatchers("/index").permitAll();
		 http.authorizeRequests().antMatchers("/inicio_sesion").permitAll();
		 http.authorizeRequests().antMatchers("/registro").permitAll();
		 http.authorizeRequests().antMatchers("/noticias").permitAll();
		 http.authorizeRequests().antMatchers("/inicio_error").permitAll();
		 http.authorizeRequests().antMatchers("/guardarusuario").permitAll();
		 http.authorizeRequests().antMatchers("/css/**", "/font-awesome/**","/fonts/**", "/imagenes/**", "/js/**").permitAll();
		 // Private pages (all other pages)
		 http.authorizeRequests().anyRequest().authenticated();
		 http.authorizeRequests().antMatchers("/crear_noticia").hasAnyRole("ADMIN");

		 // Login form2
		 http.formLogin().loginPage("/inicio_sesion");
		 http.formLogin().usernameParameter("nombre");
		 http.formLogin().passwordParameter("contraseña");
		 http.formLogin().defaultSuccessUrl("/noticias");
		 http.formLogin().failureUrl("/inicio_error");
		 
		 // Logout
		 http.logout().logoutUrl("/cerrar_sesion");
		 http.logout().logoutSuccessUrl("/noticias");
	
		 // Disable CSRF at the moment
		 //http.csrf().disable();
	 }
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		//.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
		auth.authenticationProvider(authenticationProvider);
	}
}
