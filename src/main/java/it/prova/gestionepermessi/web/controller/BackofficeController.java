package it.prova.gestionepermessi.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RuoloService;
import it.prova.gestionepermessi.service.UtenteService;
import it.prova.gestionepermessi.utility.UtenteUtility;

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
		mv.setViewName("backoffice/list");
		return mv;
	}
	
	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("insert_dipendente_attr", new DipendenteDTO());
		return "backoffice/insert";
	}

	@PostMapping("/save")
	public String saveFilm(@Valid @ModelAttribute("insert_dipendente_attr") DipendenteDTO dipendenteDTO,
			BindingResult result, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "backoffice";
		}

		Dipendente dipendente = DipendenteDTO.buildDipendenteFromDTO(dipendenteDTO);
		utenteService.inserisciNuovoConDipendente(UtenteUtility.generaNuovoUtenteDaDipendente(dipendente), dipendente);
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");

		return "redirect:/backoffice";
	}
	
	@GetMapping("/show/{idDipendente}")
	public String showDipendente(@PathVariable(required = true) Long idDipendente, Model model) {

		model.addAttribute("show_dipendente_attr",
				DipendenteDTO.buildDTOFromDipendente(dipendenteService.caricaDipendente(idDipendente)));
		return "backoffice/show";
	}
}
