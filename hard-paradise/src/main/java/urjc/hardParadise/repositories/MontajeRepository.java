package urjc.hardParadise.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import urjc.hardParadise.Montaje;
import urjc.hardParadise.Usuario;

public interface MontajeRepository extends JpaRepository<Montaje, Long> {
	List<Montaje> findByUsuario(Usuario usuario);

}
