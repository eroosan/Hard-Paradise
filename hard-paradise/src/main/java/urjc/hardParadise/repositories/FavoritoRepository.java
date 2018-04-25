package urjc.hardParadise.repositories;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

import urjc.hardParadise.Favorito;
import urjc.hardParadise.Montaje;
import urjc.hardParadise.Usuario;

@CacheConfig(cacheNames = "test")
public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
	List<Favorito> findByUsuario(Usuario usuario);
	
	@CacheEvict(allEntries = true)
	Montaje save(Montaje montaje); 
}
