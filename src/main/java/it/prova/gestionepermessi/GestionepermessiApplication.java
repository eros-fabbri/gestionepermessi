package it.prova.gestionepermessi;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.model.Sesso;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RuoloService;
import it.prova.gestionepermessi.service.UtenteService;

@SpringBootApplication
public class GestionepermessiApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;


	public static void main(String[] args) {
		SpringApplication.run(GestionepermessiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ROLE_ADMIN"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Backoffice User", "ROLE_BO_USER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Backoffice User", "ROLE_BO_USER"));
		}

		if (utenteServiceInstance.findByUsername("admin") == null) {

			Dipendente dipendente = new Dipendente("paolo", "verdi", "YMXJWZ75A20C625T", "p.verdi@email.it", new Date(),
					new Date(), new Date(), Sesso.MASCHIO);

			Utente admin = new Utente("admin", "admin", new Date());
			admin.setDipendente(dipendente);
			dipendente.setUtente(admin);
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN"));
			utenteServiceInstance.inserisciNuovoConDipendente(admin, dipendente);
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}

		if (utenteServiceInstance.findByUsername("backoffice") == null) {
			Dipendente dipendente = new Dipendente("mario", "rossi", "YMXJWZ75A20C625T", "m.rossi@email.it", new Date(),
					new Date(), new Date(), Sesso.MASCHIO);

			Utente boUser = new Utente("backoffice", "backoffice", new Date());

			boUser.setDipendente(dipendente);
			dipendente.setUtente(boUser);
			boUser.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Backoffice User", "ROLE_BO_USER"));
			utenteServiceInstance.inserisciNuovoConDipendente(boUser, dipendente);
			utenteServiceInstance.changeUserAbilitation(boUser.getId());
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Dipendente User", "ROLE_DIPENDENTE_USER") == null)
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Dipendente User","ROLE_DIPENDENTE_USER"));

	}

}
