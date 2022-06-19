package it.prova.gestionepermessi.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import it.prova.gestionepermessi.model.RichiestaPermesso;

@Service
public interface RichiestaPermessoService {
	
	public void inserisciRichiestaConAllegato(RichiestaPermesso richiestaPermesso, MultipartFile file) throws IOException;
		
}