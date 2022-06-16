package it.prova.gestionepermessi.service;

import java.util.List;

import it.prova.gestionepermessi.model.Dipendente;

public interface DipendenteService {
	
	public void inserisciNuovo(Dipendente dipendenteInstance);
	
	public List<Dipendente> findAll();
	
}
