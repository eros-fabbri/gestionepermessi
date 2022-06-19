package it.prova.gestionepermessi.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.prova.gestionepermessi.dto.RichiestaPermessoInsertDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.service.AttachmentService;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.MessaggioService;
import it.prova.gestionepermessi.service.RichiestaPermessoService;
import it.prova.gestionepermessi.utility.GenerazioneAutomaticaUtility;

@Controller
@RequestMapping("/dipendente")
public class DipendenteController {

	@Autowired
	AttachmentService attachmentService;
	@Autowired
	RichiestaPermessoService permessoService;
	@Autowired
	DipendenteService dipendenteService;
	@Autowired
	MessaggioService messaggioService;

	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("insert_richiesta_attr", new RichiestaPermessoInsertDTO());
		return "dipendente/insert";
	}

	@GetMapping("/richiestepermesso")
	public String list(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null) {
			throw new RuntimeException("Errore!");
		}
		Dipendente dipendenteInSessione = dipendenteService.findByUtenteUsername(auth.getName());
		if (dipendenteInSessione == null) {
			throw new RuntimeException("Errore!");
		}

		model.addAttribute("list_richiesta_attr",
				permessoService.findByDipendeId(dipendenteInSessione.getUtente().getId()));
		return "dipendente/list";
	}

	@PostMapping("/save")
	public String uploadFile(@RequestParam("userId") Long userId, RichiestaPermessoInsertDTO richiestaPermessoInsertDTO,
			@RequestParam("file") MultipartFile file) {
		try {
			RichiestaPermesso richiesta = richiestaPermessoInsertDTO.buildRichiestaPermesso();
			richiesta.setDipendente(dipendenteService.findByUtenteId(userId));
			richiesta.setApprovato(false);

			if (!file.getOriginalFilename().isBlank()) {
				permessoService.inserisciRichiestaConAllegato(richiesta, file);
			} else {
				permessoService.inserisciRichiesta(richiesta);
			}
			
			Messaggio messaggio = GenerazioneAutomaticaUtility.generaMessaggio(richiesta);
			
			messaggioService.inserisciNuovo(messaggio);

			return "home";

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "home";

	}
}
