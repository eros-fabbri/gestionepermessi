package it.prova.gestionepermessi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.UtenteDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.UtenteService;
import it.prova.raccoltafilmspringmvc.dto.FilmDTO;
import it.prova.raccoltafilmspringmvc.model.Film;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	DipendenteService dipendenteService; 
	@Autowired
	UtenteService utenteService;

	@GetMapping("/dipendente")
	public ModelAndView listAllDipendenti() {
		ModelAndView mv = new ModelAndView();
		List<Dipendente> dipendenti = dipendenteService.findAll();
		mv.addObject("dipendenti_list_attribute", DipendenteDTO.createDipendenteDTOLIstFromDipendenteList(dipendenti));
		mv.setViewName("admin/dipendente/list");
		return mv;
	}
	
	@GetMapping("/utente")
	public ModelAndView listAllUtenti() {
		ModelAndView mv = new ModelAndView();
		List<Utente> utenti = utenteService.listAllUtenti();
		mv.addObject("utenti_list_attribute", UtenteDTO.buildListUtenteDTOfromUtenteList(utenti));
		mv.setViewName("admin/utente/list");
		return mv;
	}

	
	@GetMapping("/dipendente/show/{idDipendente}")
	public String showDipendente(@PathVariable(required = true) Long idDipendente, Model model) {

		model.addAttribute("show_dipendente_attr",
				DipendenteDTO.buildDTOFromDipendente(dipendenteService.caricaDipendente(idDipendente)));
		return "admin/dipendente/show";
	}
	
	@GetMapping("/utente/show/{idUtente}")
	public String showUtente(@PathVariable(required = true) Long idUtente, Model model) {

		model.addAttribute("show_utente_attr",
				UtenteDTO.buildUtenteDTOFromModel(utenteService.caricaSingoloUtente(idUtente)));
		return "admin/utente/show";
	}
	
	@GetMapping("/dipendente/search")
	public String searchDipendente(Model model) {
		model.addAttribute("search_dipendente_attr", new DipendenteDTO());
		return "admin/dipendente/search";
	}
	
	@PostMapping("/list")
	public String listFilms(DipendenteDTO dipendenteExample, ModelMap model) {
		List<Dipendente> dipendenti = dipendenteService.findByExample(dipendenteExample.buildDipendenteModel());
		model.addAttribute("film_list_attribute", DipendenteDTO.createDipendenteDTOLIstFromDipendenteList(dipendenti));
		return "film/list";
	}
}
