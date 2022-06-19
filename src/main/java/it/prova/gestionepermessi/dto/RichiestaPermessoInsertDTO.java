package it.prova.gestionepermessi.dto;

import java.util.Date;

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
	
	public RichiestaPermesso buildRichiestaPermesso() {
		
		RichiestaPermesso result = new RichiestaPermesso();
		
		result.setNota(this.nota);
		result.setCodiceCertificato(this.codiceCertificato);
		if (this.tipoPermesso == "MALATTIA") {
			result.setTipoPermesso(TipoPermesso.MALATTIA);
		}else {
			result.setTipoPermesso(TipoPermesso.FERIE);
		}
		result.setDataInizio(this.dataInizio);
		result.setDataFine(this.dataFine);
		
		return result;
		
	}

}
