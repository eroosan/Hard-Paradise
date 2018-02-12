package urjc.hardParadise;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
	List<Favorito> findByUsuario(Usuario usuario);
}
