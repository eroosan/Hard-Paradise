package urjc.hardParadise.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import urjc.hardParadise.Usuario;


public interface Usuariorepository extends JpaRepository<Usuario, String> {
	List<Usuario> findBySeguidos(Usuario usuario);
	
}
