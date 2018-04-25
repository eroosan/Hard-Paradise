package urjc.hardParadise.repositories;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import urjc.hardParadise.Montaje;
import urjc.hardParadise.Usuario;

@CacheConfig(cacheNames = "test")
public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	
	List<Usuario> findBySeguidos(Usuario usuario);
	Usuario findByNombre(String nombre);
	
	@CacheEvict(allEntries = true)
	Usuario save(Usuario usuario); 
	
}
