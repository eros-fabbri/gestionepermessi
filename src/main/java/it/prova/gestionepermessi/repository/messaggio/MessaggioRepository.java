package it.prova.gestionepermessi.repository.messaggio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepermessi.model.Messaggio;

public interface MessaggioRepository extends CrudRepository<Messaggio, Long> {
	
	@Query("from Messaggio m join fetch m.richiestaPermesso r join r.dipendente d join d.utente u where u.id=?1")
	public List<Messaggio> findAllByUtenteId(Long id);

}
