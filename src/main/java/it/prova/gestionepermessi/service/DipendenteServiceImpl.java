package it.prova.gestionepermessi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.repository.dipendente.DipendenteRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DipendenteServiceImpl implements DipendenteService {

	@Autowired
	DipendenteRepository dipendenteRepository;

	@Override
	@Transactional(readOnly = true)
	public void inserisciNuovo(Dipendente dipendenteInstance) {
		dipendenteRepository.save(dipendenteInstance);
	}

}
