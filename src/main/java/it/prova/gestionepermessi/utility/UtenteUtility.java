package it.prova.gestionepermessi.utility;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;

public class UtenteUtility {
	
	@Autowired
	
	public static Utente generaNuovoUtenteDaDipendente(Dipendente dipendenteInput) {
		Utente result = new Utente("Password@01",  new Date());
		result.setUsername(dipendenteInput.getNome().charAt(0)+"."+dipendenteInput.getCognome());
		return result;
	}
}
