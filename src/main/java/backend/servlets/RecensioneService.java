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
	private Connection conn;
	
	//funziona
	public void insert_UtenteRagazza(Utente u) {
		conn = null;
		PreparedStatement stmtRag = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			try {
				conn = DriverManager.getConnection(url, user, pwd);
				stmtRag = conn.prepareStatement(
						"INSERT INTO ST_UtenteRagazza (Nome, Cognome, Email, Password, Nazionalità, DataDiNascita, Username) VALUES (?,?,?,?,?,?,?)");
				
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
	
	//CONTROLLA
	public void insert_Recensione(Recensione r, Utente u, Città c) {
		conn = null;
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
			conn = null;
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
		
	
	public void logout() {
		 if (conn != null) {
	            try {
	                conn.close();
	                System.out.println("Connessione al database chiusa correttamente.");
	            } catch (SQLException e) {
	               
	                System.err.println("Errore durante la chiusura della connessione al database: " + e.getMessage());
	            } finally {
	                // Codice da eseguire sempre, anche in caso di eccezione
	                conn = null; 
	            }
	     }
	}

	
	//select dei dati della città selezionata
	public Città select_Citta(int id) {
					Città c = null;
					conn = null;
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


	//select elenco delle recensioni inserite in database
	/*public ElencoRecensioni select_Recensioni() {
		Utente u = null;
		ElencoRecensioni elencoR = new ElencoRecensioni();
		conn = null;
		PreparedStatement stmt = null;
		ResultSet r = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, pwd);
			stmt = conn.prepareStatement("SELECT * FROM ST_Recensione");
			
			r = stmt.executeQuery();
			while(r.next()) {
				int idU = r.getInt("Id_Ragazza");
				String descr = r.getNString("Descrizione");
				int voto = r.getInt("Voto");
				String data = r.getNString("Data");
				u = this.Select_Utente1(idU);
				Recensione re = new Recensione(descr, voto, data);
				re.setU(u);
				elencoR.add(re);
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
		return elencoR;
	} 
	*/
	
	
	//select dati della ragazza dato id, controlla
	public Utente Select_Utente1(int id) {
		Utente u = null;
		conn = null;
		PreparedStatement stmt = null;
		ResultSet r = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, pwd);
			stmt = conn.prepareStatement("SELECT * FROM ST_UtenteRagazza WHERE Id = ? ");
			
			r = stmt.executeQuery();
			while(r.next()) {
				String c = r.getString("Cognome");
				String n = r.getString("Nome");
				String e = r.getString("Email");
				String nz = r.getString("Nazionalità");
				String d = r.getString("DataDiNascita");
				String username = r.getString("Username");
				String pwdU = r.getString("Password");
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



	//select elenco delle recensioni di una determinata città inserita in database
	/*public ElencoRecensioni select_RecensioniCitta(Città c) {
		Utente u = null;
		ElencoRecensioni elencoR = new ElencoRecensioni();
		conn = null;
		PreparedStatement stmt = null;
		ResultSet r = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, pwd);
			stmt = conn.prepareStatement("SELECT * FROM ST_Recensione WHERE Id_CittaEuropea = ?");
			stmt.setInt(1, c.getId());
			
			r = stmt.executeQuery();
			while(r.next()) {
				int idU = r.getInt("Id_Ragazza");
				String descr = r.getNString("Descrizione");
				int voto = r.getInt("Voto");
				String data = r.getNString("Data");
				u = this.select_Utente1(idU);
				Recensione re = new Recensione(descr, voto, data);
				re.setU(u);
				elencoR.add(re);
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
		return elencoR;
	} 
	*/
}