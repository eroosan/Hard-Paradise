package urjc.hardParadise;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long>{
	
	List<Valoracion> findByMontaje(Montaje id);

}
