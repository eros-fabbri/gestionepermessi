package it.prova.gestionepermessi.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.model.TipoPermesso;
import it.prova.gestionepermessi.validation.constraint.RichiestaPermessoValid;

@RichiestaPermessoValid
public class RichiestaPermessoInsertDTO {

	private String tipoPermesso;
	private Date dataInizio;
	private Date dataFine;
	private String codiceCertificato;
	private String nota;
	private Boolean giornoSingolo;
	private Boolean approvato;

	public String getCodiceCertificato() {
		return codiceCertificato;
	}

	public void setCodiceCertificato(String codiceCertificato) {
		this.codiceCertificato = codiceCertificato;
	}

	public String getTipoPermesso() {
		return tipoPermesso;
	}

	public void setTipoPermesso(String tipoPermesso) {
		this.tipoPermesso = tipoPermesso;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public boolean isGiornoSingolo() {
		return giornoSingolo;
	}

	public void setGiornoSingolo(boolean giornoSingolo) {
		this.giornoSingolo = giornoSingolo;
	}
	
	public Boolean getGiornoSingolo() {
		return giornoSingolo;
	}

	public void setGiornoSingolo(Boolean giornoSingolo) {
		this.giornoSingolo = giornoSingolo;
	}

	public Boolean getApprovato() {
		return approvato;
	}

	public void setApprovato(Boolean approvato) {
		this.approvato = approvato;
	}

	public RichiestaPermesso buildRichiestaPermesso() {

		RichiestaPermesso result = new RichiestaPermesso();

		result.setNota(this.nota);
		result.setCodiceCertificato(this.codiceCertificato);
		if (this.tipoPermesso == "MALATTIA") {
			result.setTipoPermesso(TipoPermesso.MALATTIA);
		} else {
			result.setTipoPermesso(TipoPermesso.FERIE);
		}
		result.setDataInizio(this.dataInizio);
		result.setDataFine(this.dataFine);

		return result;

	}

	public static RichiestaPermessoInsertDTO buildDtoFromModel(RichiestaPermesso richiestaInput) {
		RichiestaPermessoInsertDTO result = new RichiestaPermessoInsertDTO();

		result.setCodiceCertificato(richiestaInput.getCodiceCertificato());
		result.setDataInizio(richiestaInput.getDataInizio());
		result.setDataFine(richiestaInput.getDataFine());
		result.setNota(richiestaInput.getNota());
		if (richiestaInput.getTipoPermesso() == TipoPermesso.FERIE) {
			result.setTipoPermesso("FERIE");
		} else {
			result.setTipoPermesso("MALATTIA");
		}
		result.setApprovato(richiestaInput.isApprovato());
		return result;

	}

	public static List<RichiestaPermessoInsertDTO> buildDTOListFromModelList(List<RichiestaPermesso> list) {
		List<RichiestaPermessoInsertDTO> result = new ArrayList<>();
		list.forEach(r -> result.add(RichiestaPermessoInsertDTO.buildDtoFromModel(r)));
		return result;

	}

}
