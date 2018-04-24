package urjc.hardParadise.repositories;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import urjc.hardParadise.Noticia;

@CacheConfig(cacheNames = "test")
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
	
	@Cacheable
	List<Noticia> findAll();
	
	@CacheEvict(allEntries=true)
	Noticia save(Noticia noticia);

}
