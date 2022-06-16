package it.prova.gestionepermessi.dto;

import java.util.ArrayList;
import java.util.List;

import it.prova.gestionepermessi.model.Dipendente;

public class DipendenteDTO {
	
	/*agggiungere validation constraints*/
	
	private String nome;
	private String cognome;
	private Long id;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static List<DipendenteDTO> createDipendenteDTOLIstFromDipendenteList(List<Dipendente> listDipendenti) {
		List<DipendenteDTO> result = new ArrayList<>();
		listDipendenti.forEach(d -> result.add(DipendenteDTO.buildDTOFromDipendente(d)));
		return result;
	}
	
	public static DipendenteDTO buildDTOFromDipendente(Dipendente dipendenteInput) {
		
		/*considerare, in generale, l'uso del buildbattern*/
		DipendenteDTO result = new DipendenteDTO();
		result.setNome(dipendenteInput.getNome());
		result.setCognome(dipendenteInput.getCognome());
		result.setId(dipendenteInput.getId());
		//aggiungere altri set 
		return result;
		
	}
}
