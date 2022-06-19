package it.prova.gestionepermessi.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.prova.gestionepermessi.model.RichiestaPermesso;

@Service
public interface RichiestaPermessoService {

	public void inserisciRichiestaConAllegato(RichiestaPermesso richiestaPermesso, MultipartFile file)
			throws IOException;

	public List<RichiestaPermesso> findByDipendeId(Long id);

	public void inserisciRichiesta(RichiestaPermesso richiestaPermesso);

	public Page<RichiestaPermesso> findByExample(RichiestaPermesso example, Integer pageNo, Integer pageSize,
			String sortBy);
}
