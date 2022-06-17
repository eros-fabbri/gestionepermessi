package it.prova.gestionepermessi.model;

public enum Sesso {
	MASCHIO("M"), FEMMINA("F");

	private String abbreviazione;

	Sesso(String abbreviazione) {
		this.abbreviazione = abbreviazione;
	}

	public String getAbbreviazione() {
		return abbreviazione;
	}
	
	//ASSOLUTAMENTE DA CAMBIARE
	
	public static Sesso abbreviazioneToSesso(String abbreviazione) {
		if (abbreviazione == "M")
			return MASCHIO;
		return FEMMINA;
	}

}
