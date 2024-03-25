package finestre;

public class Stanza {
	private int id;
	private String numeroStanza;
	private String nomeStanza;
	private String descrizione;
	private double prezzo;
	private boolean disponibile;
	
	
	//COSTRUTTORE

	public Stanza(int id, String numeroStanza, String nomeStanza, String descrizione, 
			double prezzo, boolean disponibile) {
		super();
		this.id = id;
		this.numeroStanza = numeroStanza;
		this.nomeStanza = nomeStanza;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.disponibile = disponibile;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNumeroStanza() {
		return numeroStanza;
	}


	public void setNumeroStanza(String numeroStanza) {
		this.numeroStanza = numeroStanza;
	}


	public String getNomeStanza() {
		return nomeStanza;
	}


	public void setNomeStanza(String nomeStanza) {
		this.nomeStanza = nomeStanza;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public double getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}


	public boolean isDisponibile() {
		return disponibile;
	}


	public void setDisponibile(boolean disponibile) {
		this.disponibile = disponibile;
	}
	
	
	
	
}
