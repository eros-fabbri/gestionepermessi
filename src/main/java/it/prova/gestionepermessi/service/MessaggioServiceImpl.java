package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.repository.messaggio.MessaggioRepository;

@Service
public class MessaggioServiceImpl implements MessaggioService {
	
	@Autowired
	MessaggioRepository messaggioRepository;
	
	@Override
	public void inserisciNuovo(Messaggio messaggio) {
		messaggioRepository.save(messaggio);
	}

	@Override
	public List<Messaggio> findByUtenteId(Long id) {
		
		return messaggioRepository.findAllByUtenteId(id);
	}

}
