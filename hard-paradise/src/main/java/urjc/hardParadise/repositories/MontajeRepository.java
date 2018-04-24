package urjc.hardParadise.repositories;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import urjc.hardParadise.Montaje;
import urjc.hardParadise.Usuario;

@CacheConfig(cacheNames = "test")
public interface MontajeRepository extends JpaRepository<Montaje, Long> {
	
	@Cacheable
	List<Montaje> findAll(Sort sort);
	
	@Cacheable
	Montaje findOne(long id);
	
	@CacheEvict(allEntries = true)
	Montaje save(Montaje montaje); 
	
	
	List<Montaje> findByUsuario(Usuario usuario);
	

}
