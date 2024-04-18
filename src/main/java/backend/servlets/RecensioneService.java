package backend.servlets;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

import com.mysql.cj.xdevapi.Result;

import backend.Città;
import backend.Recensione;
import backend.Utente;

public class RecensioneService {


	private static final String url = "jdbc:mysql://didattica.liceocuneo.it/db5F?useSSL=false";
	private static final String user = "quintaf";
	private static final String pwd = "pluto21";
	private static final String INSERT_UTENTE = "INSERT INTO ST_UtenteRagazza (Nome, Cognome, Email, Password, Nazionalità, DataDiNascita, Username) VALUES (?,?,?,?,?,?,?)";
	private static final String UPDATE_UTENTE = "UPDATE ST_UtenteRagazza SET Nome = ?, Cognome = ?, Email = ?, Password = ?, Nazionalità = ?, Username = ? WHERE Id = ?";
	private static final String DELETE_UTENTE = "DELETE FROM ST_UtenteRagazza WHERE Id = ?";
	
	//inserimento nel database della ragazza
	public void insert_UtenteRagazza(Utente u) {
		Connection conn = null;
		PreparedStatement stmtRag = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			try {
				conn = DriverManager.getConnection(url, user, pwd);
				stmtRag = conn.prepareStatement(
						"INSERT INTO ST_UtenteRagazza (Nome, Cognome, Email, Password, Nazionalità, DataDiNascita, Username) VALUES (?,?,?,?,?,?,?)");
				/*
				UUID uuid = UUID.randomUUID();
		        String id = uuid.toString().replaceAll("-", "");
		        
		        // Estrae i primi 4 caratteri per ottenere un ID di 4 cifre
		        id = id.substring(0, 4);
		        u.setId(Integer.parseInt(id));
		        */
				//3) Preparare lo statement
				if(u.getId() > 0) {
					stmtRag = conn.prepareStatement(UPDATE_UTENTE);
					stmtRag.setString(1, u.getNome());
					stmtRag.setString(2, u.getCognome());
					stmtRag.setString(3, u.getEmail());
					stmtRag.setString(4, u.getPwd());
					stmtRag.setString(5, u.getNazionalità());
					LocalDate dataNascita = u.getDataNascita();
					Date dataNascitaSQL = Date.valueOf(dataNascita);

					// Imposta la data di nascita nel PreparedStatement
					stmtRag.setString(6, u.getDataNascitaS());
					stmtRag.setString(7, u.getUser());
					
				}
				else {
					stmtRag = conn.prepareStatement(INSERT_UTENTE);
					stmtRag.setString(1, u.getNome());
					stmtRag.setString(2, u.getCognome());
					stmtRag.setString(3, u.getEmail());
					stmtRag.setString(4, u.getPwd());
					stmtRag.setString(5, u.getNazionalità());
					LocalDate dataNascita = u.getDataNascita();
					Date dataNascitaSQL = Date.valueOf(dataNascita);

					// Imposta la data di nascita nel PreparedStatement
					stmtRag.setString(6, u.getDataNascitaS());
					stmtRag.setString(7, u.getUser());
					
				}
				
				//4) Eseguire lo statement ed elaborare eventuali risultati
				stmtRag.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmtRag != null) {
				try {
					stmtRag.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//inserimento nel database della recensione
		public void insert_Recensione(Recensione r, Utente u, Città c) {
			Connection conn = null;
			PreparedStatement stmtRec = null;

			try {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				try {
					conn = DriverManager.getConnection(url, user, pwd);
					stmtRec = conn.prepareStatement(
							"INSERT INTO ST_Recensione (Id_Ragazza, Id_CittaEuropea, Descrizione, Voto, Data) VALUES (?,?,?,?,?)");
					
					stmtRec.setInt(1, u.getId());
					stmtRec.setInt(2, c.getId());
					stmtRec.setString(3, r.getDesc());
					stmtRec.setFloat(4, r.getVoto());
					stmtRec.setString(5, r.getDataS());
					
					stmtRec.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (stmtRec != null) {
					try {
						stmtRec.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		//funziona
		public Utente select_Utente(String username, String pwdU) {
			Utente u = null;
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet r = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection(url, user, pwd);
				stmt = conn.prepareStatement("SELECT * FROM ST_UtenteRagazza WHERE Username = ? AND Password = ?");
				stmt.setString(1, username);
				stmt.setString(2, pwdU);
				
				r = stmt.executeQuery();
				while(r.next()) {
					String c = r.getString("Cognome");
					String n = r.getString("Nome");
					String e = r.getString("Email");
					String nz = r.getString("Nazionalità");
					String d = r.getString("DataDiNascita");
					u = new Utente(c, n, e, username, pwdU, nz, d);
				}} catch (Exception e) {

				} finally {
					if (stmt != null) {
						try {
							stmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			return u;
		} 
		
		//select dei dati della città selezionata
				public Città select_Citta(int id) {
					Città c = null;
					Connection conn = null;
					PreparedStatement stmt = null;
					ResultSet r = null;
					try {
						Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
						conn = DriverManager.getConnection(url, user, pwd);
						stmt = conn.prepareStatement("SELECT * FROM ST_CittaEuropea WHERE Id = ?");
						stmt.setInt(1, id);
						
						r = stmt.executeQuery();
						while(r.next()) {
							String nomeC = r.getNString("Nome");
							String statoC = r.getString("Stato");
							c = new Città(id, nomeC, statoC);
						}} catch (Exception e) {

						} finally {
							if (stmt != null) {
								try {
									stmt.close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							if (conn != null) {
								try {
									conn.close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					return c;
				} 		
}