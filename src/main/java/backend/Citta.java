package backend;

public class Citta {

	private int id;
	private String nome;
	private String stato;
	
	public Citta() {
		
	}
	
	public Citta(int id, String n, String s) {
		this.id = id;
		this.nome = n;
		this.stato = s;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Città [id=" + id + ", nome=" + nome + ", stato=" + stato + "]";
	}
	
	
}