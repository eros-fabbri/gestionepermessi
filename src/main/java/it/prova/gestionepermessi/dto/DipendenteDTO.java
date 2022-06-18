package it.prova.gestionepermessi.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Sesso;

public class DipendenteDTO {

	/* agggiungere validation constraints */

	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String cognome;
	@Size(min = 16, max = 16)
	private String codFis;
	private String email;
	@NotNull
	private Date dataNascita;
	@NotNull
	private Date dataAssunzione;
	private Date dataDimissione;

	@NotNull
	private String sesso;

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

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public static List<DipendenteDTO> createDipendenteDTOLIstFromDipendenteList(List<Dipendente> listDipendenti) {
		List<DipendenteDTO> result = new ArrayList<>();
		listDipendenti.forEach(d -> result.add(DipendenteDTO.buildDTOFromDipendente(d)));
		return result;
	}

	public Dipendente buildDipendenteModel() {
		Dipendente result = new Dipendente();
		result.setId(this.id);
		result.setNome(this.nome);
		result.setCognome(this.cognome);
		result.setCodFis(this.codFis);
		result.setDataNascita(this.dataNascita);
		result.setDataAssunzione(this.dataAssunzione);
		result.setDataDimissione(this.dataDimissione);
		// alcune cose sono da rivedere riguardo al sesso e ai ruoli
		result.setSesso(Sesso.abbreviazioneToSesso(this.sesso));
		return result;
	}

	public static DipendenteDTO buildDTOFromDipendente(Dipendente dipendenteInput) {

		/* considerare, in generale, l'uso del buildbattern */
		DipendenteDTO result = new DipendenteDTO();
		result.setNome(dipendenteInput.getNome());
		result.setCognome(dipendenteInput.getCognome());
		result.setId(dipendenteInput.getId());
		result.setCodFis(dipendenteInput.getCodFis());
		result.setDataNascita(dipendenteInput.getDataNascita());
		result.setDataAssunzione(dipendenteInput.getDataAssunzione());
		result.setDataDimissione(dipendenteInput.getDataNascita());
		result.setSesso(dipendenteInput.getSesso().getAbbreviazione());
		result.setEmail(dipendenteInput.getEmail());
		
			if (dipendenteInput.getUtente() !=null) {
				//da implementare
			}

		return result;

	}

	public static Dipendente buildDipendenteFromDTO(DipendenteDTO dipendenteDTO) {
		Dipendente result = new Dipendente();

		result.setId(dipendenteDTO.getId());
		result.setNome(dipendenteDTO.getNome());
		result.setCognome(dipendenteDTO.getCognome());
		result.setCodFis(dipendenteDTO.getCodFis());
		result.setDataNascita(dipendenteDTO.getDataNascita());
		result.setDataAssunzione(dipendenteDTO.getDataAssunzione());
		result.setDataDimissione(dipendenteDTO.getDataNascita());
		// alcune cose sono da rivedere riguardo al sesso e ai ruoli
		result.setSesso(Sesso.abbreviazioneToSesso(dipendenteDTO.getSesso()));

		return result;
	}

}
