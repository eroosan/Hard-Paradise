package urjc.hardParadise.repositories;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

import urjc.hardParadise.Comentario;
import urjc.hardParadise.Montaje;

@CacheConfig(cacheNames = "test")
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

	List<Comentario> findByMontaje(Montaje id);
	
	@CacheEvict(allEntries = true)
	Comentario save(Comentario comentario); 
}
