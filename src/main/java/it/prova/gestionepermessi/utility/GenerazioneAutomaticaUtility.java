package it.prova.gestionepermessi.utility;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.model.TipoPermesso;
import it.prova.gestionepermessi.model.Utente;

public class GenerazioneAutomaticaUtility {

	@Autowired

	public static Utente generaNuovoUtenteDaDipendente(Dipendente dipendenteInput) {
		Utente result = new Utente("Password@01", new Date());
		result.setUsername(dipendenteInput.getNome().charAt(0) + "." + dipendenteInput.getCognome());
		return result;
	}

	public static String generaUsernameDaDipendente(Dipendente dipendenteInput) {
		return dipendenteInput.getNome().charAt(0) + "." + dipendenteInput.getCognome();
	}

	public static String generaEmailDaDipendente(Dipendente dipendenteInput) {
		return dipendenteInput.getNome().charAt(0) + "." + dipendenteInput.getCognome() + "@prova.it";
	}

	public static Messaggio generaMessaggio(RichiestaPermesso richiestaPermesso) {
		Messaggio result = new Messaggio();

		result.setOgetto("Richiesta permesso da parte di: " + richiestaPermesso.getDipendente().getNominativo());

		String testo = "Il dipendente " + richiestaPermesso.getDipendente().getNominativo() + " per "
				+ richiestaPermesso.getTipoPermesso();
		if (richiestaPermesso.getDataFine() != null)
			testo += ", con inizio il " + richiestaPermesso.getDataInizio() + " e fine il "
					+ richiestaPermesso.getDataFine();
		else {
			testo += ", per il giorno" + richiestaPermesso.getDataInizio();
		}
		if (richiestaPermesso.getTipoPermesso() == TipoPermesso.MALATTIA)
			testo += ", allegando il codice certificato:  " + richiestaPermesso.getCodiceCertificato();
		if (richiestaPermesso.getAttachment() != null)
			testo += " e il file: " + richiestaPermesso.getAttachment().getNomeFile();

		result.setTesto(testo);

		result.setRichiestaPermesso(richiestaPermesso);
		result.setDataInserimento(new Date());
		return result;

	}
}
