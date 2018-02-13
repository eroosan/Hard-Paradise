package urjc.hardParadise;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface Usuariorepository extends JpaRepository<Usuario, String> {
	List<Usuario> findBySeguidos(Usuario usuario);
	
}
