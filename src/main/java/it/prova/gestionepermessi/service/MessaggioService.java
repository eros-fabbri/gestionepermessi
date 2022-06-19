package it.prova.gestionepermessi.service;

import java.util.List;

import it.prova.gestionepermessi.model.Messaggio;

public interface MessaggioService {
	
	public void inserisciNuovo(Messaggio messaggio);
	
	public List<Messaggio> findByUtenteId(Long id);
	
	
}
