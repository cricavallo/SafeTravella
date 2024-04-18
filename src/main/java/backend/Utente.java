package backend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utente {

	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	private String cognome;
	private String nome;
	private String email;
	private String user;
	private String pwd;
	private String nazionalità;
	private LocalDate dataNascita;
	private String dataNascitaS;

	public Utente(String cognome, String nome, String email, String user, String pwd, String nazionalità, LocalDate dataNascita) {
		super();
		this.cognome = cognome;
		this.nome = nome;
		this.email = email;
		this.user = user;
		this.pwd = pwd;
		this.nazionalità = nazionalità;
		this.dataNascita = dataNascita;
	}
	
	public Utente(String cognome, String nome, String email, String user, String pwd, String nazionalità, String dataNascitaS) {
		super();
		this.cognome = cognome;
		this.nome = nome;
		this.email = email;
		this.user = user;
		this.pwd = pwd;
		this.nazionalità = nazionalità;
		this.dataNascitaS = dataNascitaS;
		this.dataNascita = LocalDate.parse(dataNascitaS, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	@Override
	public String toString() {
		return "Utente [cognome=" + cognome + ", nome=" + nome + ", email=" + email + ", user=" + user + ", pwd=" + pwd
				+ ", nazionalità=" + nazionalità + ", dataNascitaS=" + dataNascita + "]";
	}

	public String getNazionalità() {
		return nazionalità;
	}

	public void setNazionalità(String nazionalità) {
		this.nazionalità = nazionalità;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Utente() {
	}

	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public String getDataNascitaS() {
		return dataNascitaS;
	}

	public void setDataNascitaS(String dataNascitaS) {
		this.dataNascitaS = dataNascitaS;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}