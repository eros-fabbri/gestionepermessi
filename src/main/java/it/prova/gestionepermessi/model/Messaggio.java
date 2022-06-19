package it.prova.gestionepermessi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="messaggio")
public class Messaggio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Lob
	@Column(name = "testo")
	private String testo;
	@Column(name = "ogetto")
	private String ogetto;
	@Column(name = "dataInserimento")
	private Date dataInserimento;
	@Column(name = "dataLettura")
	private Date dataLettura;
	

	@OneToOne
	@JoinColumn(name = "richiestaPermesso_id", referencedColumnName = "id")
	private RichiestaPermesso richiestaPermesso;
	
	public Messaggio() {
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getOgetto() {
		return ogetto;
	}

	public void setOgetto(String ogetto) {
		this.ogetto = ogetto;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Date getDataLettura() {
		return dataLettura;
	}

	public void setDataLettura(Date dataLettura) {
		this.dataLettura = dataLettura;
	}

	public RichiestaPermesso getRichiestaPermesso() {
		return richiestaPermesso;
	}

	public void setRichiestaPermesso(RichiestaPermesso richiestaPermesso) {
		this.richiestaPermesso = richiestaPermesso;
	}
	
	
	
}
