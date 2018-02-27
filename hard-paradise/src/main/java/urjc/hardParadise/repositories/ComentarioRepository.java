package urjc.hardParadise.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import urjc.hardParadise.Comentario;
import urjc.hardParadise.Montaje;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

	List<Comentario> findByMontaje(Montaje id);

}
