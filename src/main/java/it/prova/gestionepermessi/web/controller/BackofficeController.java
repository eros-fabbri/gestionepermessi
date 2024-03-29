package it.prova.gestionepermessi.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RuoloService;
import it.prova.gestionepermessi.service.UtenteService;
import it.prova.gestionepermessi.utility.GenerazioneAutomaticaUtility;

@Controller
@RequestMapping("/backoffice")
public class BackofficeController {

	@Autowired
	DipendenteService dipendenteService;
	@Autowired
	UtenteService utenteService;
	@Autowired
	RuoloService ruoloService;

	@GetMapping
	public ModelAndView listAllDipendenti() {
		ModelAndView mv = new ModelAndView();
		List<Dipendente> dipendenti = dipendenteService.findAll();
		mv.addObject("dipendenti_list_attribute", DipendenteDTO.createDipendenteDTOLIstFromDipendenteList(dipendenti));
		mv.setViewName("backoffice/dipendente/list");
		return mv;
	}

	@GetMapping("/dipendente/insert")
	public String insert(Model model) {
		model.addAttribute("insert_dipendente_attr", new DipendenteDTO());
		return "backoffice/dipendente/insert";
	}

	@PostMapping("/dipendente/save")
	public String saveDipendente(@Valid @ModelAttribute("insert_dipendente_attr") DipendenteDTO dipendenteDTO,
			BindingResult result, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "backoffice/dipendente/insert";
		}

		Dipendente dipendente = DipendenteDTO.buildDipendenteFromDTO(dipendenteDTO);
		dipendente.setEmail(GenerazioneAutomaticaUtility.generaEmailDaDipendente(dipendente));
		Utente utente = GenerazioneAutomaticaUtility.generaNuovoUtenteDaDipendente(dipendente);
		utente.getRuoli().add(ruoloService.cercaPerDescrizioneECodice("Dipendente User", "ROLE_DIPENDENTE_USER"));
		if (utenteService.findByUsername(utente.getUsername()) != null) {
			redirectAttrs.addFlashAttribute("errorMessage", "ATTENZIONE: dipendente già censisto!");
			return "redirect:/home";
		}
		utente.setDipendente(dipendente);
		dipendente.setUtente(utente);
		utenteService.inserisciNuovoConDipendente(utente, dipendente);
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/backoffice";
	}

	@GetMapping("/dipendente/show/{idDipendente}")
	public String showDipendente(@PathVariable(required = true) Long idDipendente, Model model) {

		model.addAttribute("show_dipendente_attr",
				DipendenteDTO.buildDTOFromDipendente(dipendenteService.caricaDipendente(idDipendente)));
		return "backoffice/dipendente/show";
	}

	@GetMapping("/dipendente/search")
	public String searchDipendente(Model model) {
		model.addAttribute("search_dipendente_attr", new DipendenteDTO());
		return "backoffice/dipendente/search";
	}

	@PostMapping("/dipendente/list")
	public String listDipendente(DipendenteDTO dipendenteExample, ModelMap model) {
		model.addAttribute("dipendenti_list_attribute", DipendenteDTO.createDipendenteDTOLIstFromDipendenteList(
				dipendenteService.findByExample(dipendenteExample.buildDipendenteModel())));
		return "backoffice/dipendente/list";
	}
	
	@GetMapping("/dipendente/edit/{idDipendente}")
	public String editDipendente(@PathVariable(required = true) Long idDipendente, Model model) {

		model.addAttribute("edit_dipendente_attr",
				DipendenteDTO.buildDTOFromDipendente(dipendenteService.caricaDipendente(idDipendente)));
		return "backoffice/dipendente/edit";
	}
	
	@PostMapping("/dipendente/update")
	public String updateDipendente(@Valid @ModelAttribute("edit_dipendente_attr") DipendenteDTO dipendenteDTO,
			BindingResult result, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "backoffice/dipendente/insert";
		}
		Dipendente dipendente = DipendenteDTO.buildDipendenteFromDTO(dipendenteDTO);
		Utente utente = new Utente(GenerazioneAutomaticaUtility.generaUsernameDaDipendente(dipendente));
    	utente.setId(utenteService.findByDipendenteId(dipendente.getId()).getId());
		dipendente.setUtente(utente);
		dipendenteService.aggiorna(dipendente);
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/backoffice";
	}
	
	@GetMapping("/disableNotifica")
	public void disableNotfica(HttpServletRequest request) {
		request.getSession().setAttribute("newMessages", false);
	}
	
}
