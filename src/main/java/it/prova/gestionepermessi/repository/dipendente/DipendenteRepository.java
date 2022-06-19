package it.prova.gestionepermessi.repository.dipendente;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;


public interface DipendenteRepository extends CrudRepository<Dipendente, Long>{
	
	@Query("from Dipendente d join fetch d.utente")
	public List<Dipendente> findAllDipendenteFilmEager();
	
	public Dipendente findByUtenteIdEquals(Long id);
}
