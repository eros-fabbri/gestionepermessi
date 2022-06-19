package it.prova.gestionepermessi.repository.richiestapermesso;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepermessi.model.RichiestaPermesso;

public interface RichiestaPermessoRepository extends CrudRepository<RichiestaPermesso, Long> {

	 public List<RichiestaPermesso> findAllByDipendenteId(Long id);
	 
	 public Page<RichiestaPermesso> findAll(Specification<RichiestaPermesso> specificationCriteria, Pageable pagin);
}
