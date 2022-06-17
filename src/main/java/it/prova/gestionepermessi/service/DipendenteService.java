package it.prova.gestionepermessi.service;

import java.util.List;

import it.prova.gestionepermessi.model.Dipendente;

public interface DipendenteService {
	
	public void inserisciNuovo(Dipendente dipendenteInstance);
	
	public List<Dipendente> findAll();
	
	public Dipendente caricaDipendente(Long id);
	
	public List<Dipendente> findByExample(Dipendente example);
	
}
