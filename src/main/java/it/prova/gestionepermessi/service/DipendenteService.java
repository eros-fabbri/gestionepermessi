package it.prova.gestionepermessi.service;

import java.util.List;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;

public interface DipendenteService {
	
	public void inserisciNuovo(Dipendente dipendenteInstance);
	
	public List<Dipendente> findAll();
	
	public List<Dipendente> findAllEagerUtente();
	
	public Dipendente caricaDipendente(Long id);
	
	public Dipendente findByUtenteId(Long id);
	
	public List<Dipendente> findByExample(Dipendente example);
	
	public void aggiorna(Dipendente dipendenteInstance);
	
}
