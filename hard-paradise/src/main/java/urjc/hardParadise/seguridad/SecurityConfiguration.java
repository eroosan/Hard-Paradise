package urjc.hardParadise.seguridad;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Override
	 protected void configure(HttpSecurity http) throws Exception {

	 // Public pages

	 http.authorizeRequests().antMatchers("/").permitAll();
	 http.authorizeRequests().antMatchers("/index").permitAll();
	 http.authorizeRequests().antMatchers("/inicio_sesion.html").permitAll();
	 http.authorizeRequests().antMatchers("/registro.html").permitAll();
	 http.authorizeRequests().antMatchers("/noticias").permitAll();
	 // Private pages (all other pages)
	 http.authorizeRequests().anyRequest().authenticated();
	 // Login form
	 http.formLogin().loginPage("/inicio_sesion.html");
	 http.formLogin().usernameParameter("nombre");
	 http.formLogin().passwordParameter("contraseña");
	 http.formLogin().defaultSuccessUrl("/noticias");
	 http.formLogin().failureUrl("/inicio_error");
	 // Logout
	 http.logout().logoutUrl("/cerrar_sesion");
	 http.logout().logoutSuccessUrl("/noticias");

	 // Disable CSRF at the moment
	 http.csrf().disable();
	 }

}
