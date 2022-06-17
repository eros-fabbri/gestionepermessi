package it.prova.gestionepermessi.dto;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;

public class UtenteDipendenteDTO  extends UtenteDTO{

	private String nome;
	private String cognome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public Utente buildUtenteConDipendente() {
		
		Utente result =  new Utente();
		result.setUsername(super.username);
		result.setStato(super.stato);
		result.setDateCreated(super.dateCreated);
		if (!super.ruoli.isEmpty()) {
			super.ruoli.forEach(r -> result.getRuoli().add(RuoloDTO.buildRuoloFromDTO(r)));
		}
		result.setDipendente(new Dipendente(nome, cognome));
		return result;
	}
	
	
}
