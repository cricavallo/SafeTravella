package backend;

import java.util.ArrayList;

public class UtenteService {
	
	private ArrayList<Utente> elenco;
	
	public UtenteService() {
		elenco = new ArrayList<Utente>();
	}

	public Utente getUtente(String user, String pwd) {
		//accesso alla base dati
		Utente ris = null;
		//simulo
		
		if(pwd.equals("pippo")) {
			ris = new Utente();
			ris.setCognome("Cavallo");
			ris.setNome("Cristina");
			ris.setEmail("cristina.cavallo@liceocuneo.it");
			ris.setUser(user);
		}
		return ris;
	}
	
	public void salvaUtente(Utente u) {
		//accesso alla base dati
		elenco.add(u);
	}
}