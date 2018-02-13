package urjc.hardParadise;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MontajeRepository extends JpaRepository<Montaje, Long> {
	List<Montaje> findByUsuario(Usuario usuario);

}
