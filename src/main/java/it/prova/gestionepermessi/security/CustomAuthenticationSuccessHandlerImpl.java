package it.prova.gestionepermessi.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.UtenteDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.repository.dipendente.DipendenteRepository;
import it.prova.gestionepermessi.repository.utente.UtenteRepository;


@Component
public class CustomAuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private DipendenteRepository dipendenteRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		
		Utente utenteFromDb = utenteRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Username " + authentication.getName() + " not found"));
		UtenteDTO utenteParziale = new UtenteDTO();
		Dipendente dipendente = dipendenteRepository.findByUtenteIdEquals(utenteFromDb.getId());
		System.out.println(dipendente);
		utenteParziale.setDipendenteDTO(DipendenteDTO.buildDTOFromDipendente(dipendente));
		utenteParziale.getDipendenteDTO().setNome(utenteFromDb.getDipendente().getNome());
		utenteParziale.getDipendenteDTO().setCognome(utenteFromDb.getDipendente().getCognome());
		utenteParziale.setId(utenteFromDb.getId());
		request.getSession().setAttribute("userInfo", utenteParziale);
		response.sendRedirect("home");

	}

}
