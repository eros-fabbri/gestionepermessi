package it.prova.gestionepermessi.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.domain.Sort.Direction;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Sesso;

public class DipendenteDTO {

	/* agggiungere validation constraints */

	private Long id;
	@NotNull
	private String nome;
	@NotNull
	private String cognome;
	@Size(min = 16, max = 16)
	private String codFis;
	@NotNull
	private String email;
	@NotNull
	private Date dataNascita;
	@NotNull
	private Date dataAssunzione;
	@NotNull
	private Date dataDimissione;
	@NotNull
	private RuoloDTO ruolo;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Sesso sesso;

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

	public String getCodFis() {
		return codFis;
	}

	public void setCodFis(String codFis) {
		this.codFis = codFis;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Date getDataAssunzione() {
		return dataAssunzione;
	}

	public void setDataAssunzione(Date dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}

	public Date getDataDimissione() {
		return dataDimissione;
	}

	public void setDataDimissione(Date dataDimissione) {
		this.dataDimissione = dataDimissione;
	}

	public Sesso getSesso() {
		return sesso;
	}

	public void setSesso(Sesso sesso) {
		this.sesso = sesso;
	}

	public static List<DipendenteDTO> createDipendenteDTOLIstFromDipendenteList(List<Dipendente> listDipendenti) {
		List<DipendenteDTO> result = new ArrayList<>();
		listDipendenti.forEach(d -> result.add(DipendenteDTO.buildDTOFromDipendente(d)));
		return result;
	}

	public static DipendenteDTO buildDTOFromDipendente(Dipendente dipendenteInput) {

		/* considerare, in generale, l'uso del buildbattern */
		DipendenteDTO result = new DipendenteDTO();
		result.setNome(dipendenteInput.getNome());
		result.setCognome(dipendenteInput.getCognome());
		result.setId(dipendenteInput.getId());
		// aggiungere altri set
		return result;

	}

	public static Dipendente buildDipendenteFromDTO(DipendenteDTO dipendenteDTO) {
		Dipendente result = new Dipendente();
		
		result.setNome(dipendenteDTO.getNome());
		result.setCognome(dipendenteDTO.getCognome());
		result.setCodFis(dipendenteDTO.getCodFis());
		result.setDataNascita(dipendenteDTO.getDataNascita());
		result.setDataAssunzione(dipendenteDTO.getDataAssunzione());
		result.setDataDimissione(dipendenteDTO.getDataNascita());
		//alcune cose sono da rivedere riguardo al sesso e ai ruoli
		result.setSesso(dipendenteDTO.getSesso());
		

		return result;
	}
}
