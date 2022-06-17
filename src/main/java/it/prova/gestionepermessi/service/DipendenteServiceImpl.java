package it.prova.gestionepermessi.service;

import java.util.List;

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
	@Transactional
	public void inserisciNuovo(Dipendente dipendenteInstance) {
		dipendenteRepository.save(dipendenteInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Dipendente> findAll() {
		return (List<Dipendente>) dipendenteRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Dipendente caricaDipendente(Long id) {
		return dipendenteRepository.findById(id).orElse(null);
	}

}
