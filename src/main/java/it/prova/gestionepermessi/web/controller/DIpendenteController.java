package it.prova.gestionepermessi.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.prova.gestionepermessi.model.RichiestaPermesso;

@Controller
@RequestMapping("/dipendente")
public class DIpendenteController {
	
	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("insert_richiesta_attr", new RichiestaPermesso());
		return "dipendente/insert";
	}

}
