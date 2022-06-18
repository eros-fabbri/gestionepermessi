package it.prova.gestionepermessi.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.RuoloDTO;
import it.prova.gestionepermessi.dto.UtenteDTO;
import it.prova.gestionepermessi.dto.UtenteSearchDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RuoloService;
import it.prova.gestionepermessi.service.UtenteService;
import it.prova.gestionepermessi.validation.ValidationNoPassword;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	DipendenteService dipendenteService;
	@Autowired
	UtenteService utenteService;
	@Autowired
	RuoloService ruoloService;

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

	@PostMapping("/dipendente/list")
	public String listDipendente(DipendenteDTO dipendenteExample, ModelMap model) {
		model.addAttribute("dipendenti_list_attribute", DipendenteDTO.createDipendenteDTOLIstFromDipendenteList(
				dipendenteService.findByExample(dipendenteExample.buildDipendenteModel())));
		return "admin/dipendente/list";
	}

	@GetMapping("/utente/search")
	public String searchUtente(Model model) {
		model.addAttribute("search_utente_attr", new UtenteSearchDTO());
		model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
		return "admin/utente/search";
	}

	@PostMapping("/utente/list")
	public String listUtenti(UtenteSearchDTO utenteExample, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {

		List<Utente> utenti = utenteService.findByExample(utenteExample, pageNo, pageSize, sortBy).getContent();

		model.addAttribute("utenti_list_attribute", UtenteDTO.buildListUtenteDTOfromUtenteList(utenti));
		return "admin/utente/list";
	}

	@GetMapping("/utente/edit/{idUtente}")
	public String editUtente(@PathVariable(required = true) Long idUtente, Model model) {

		model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
		model.addAttribute("edit_utente_attr",
				UtenteDTO.buildUtenteDTOFromModel(utenteService.caricaSingoloUtente(idUtente)));
		return "admin/utente/edit";
	}
	
	@PostMapping("/utente/update")
	public String update(@Validated(ValidationNoPassword.class) @ModelAttribute("edit_utente_attr") UtenteDTO utenteDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("ruoli_totali_attr", RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll()));
			return "admin/utente/edit";
		}
		utenteService.aggiornaEdit(utenteDTO.buildUtenteModel(true));

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/utente/list";
	}

	
	

}
