package it.prova.gestionepermessi.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.prova.gestionepermessi.dto.RichiestaPermessoInsertDTO;
import it.prova.gestionepermessi.dto.UtenteDTO;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.service.AttachmentService;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RichiestaPermessoService;

@Controller
@RequestMapping("/dipendente")
public class DIpendenteController {

	@Autowired
	AttachmentService attachmentService;
	@Autowired
	RichiestaPermessoService permessoService;
	@Autowired
	DipendenteService dipendenteService;

	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("insert_richiesta_attr", new RichiestaPermessoInsertDTO());
		return "dipendente/insert";
	}

	@PostMapping("/save")
	public String uploadFile(@RequestParam("userId") Long userId, RichiestaPermessoInsertDTO richiestaPermessoInsertDTO,
			@RequestParam("file") MultipartFile file) {
		try {
			RichiestaPermesso richiesta = richiestaPermessoInsertDTO.buildRichiestaPermesso();
			richiesta.setDipendente(dipendenteService.findByUtenteId(userId));

			if (!file.getOriginalFilename().isBlank()) {
				permessoService.inserisciRichiestaConAllegato(richiesta, file);
			}

			return "home";

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "home";

	}
}
