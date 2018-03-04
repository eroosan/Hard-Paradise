package urjc.hardParadise.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import urjc.hardParadise.Usuario;


public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	
	List<Usuario> findBySeguidos(Usuario usuario);
	Usuario findByNombre(String nombre);
	
}
