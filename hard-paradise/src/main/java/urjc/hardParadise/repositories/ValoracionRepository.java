package urjc.hardParadise.repositories;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

import urjc.hardParadise.Montaje;
import urjc.hardParadise.Valoracion;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long>{
	
	List<Valoracion> findByMontaje(Montaje id);
	
	
	@CacheEvict(allEntries=true)
	Valoracion save(Valoracion valoracion);

}
