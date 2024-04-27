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

import backend.Citta;
import backend.ElencoCitta;
import backend.ElencoRecensioni;
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
	
	//FUNZIONA
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
	
	
	//FUNZIONA
	public void insert_Recensione(Recensione r, Citta c) {
		conn = null;
		PreparedStatement stmtRec = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			try {
				conn = DriverManager.getConnection(url, user, pwd);
				stmtRec = conn.prepareStatement(
						"INSERT INTO ST_Recensione (Id_Ragazza, Id_CittaEuropea, Descrizione, Voto, Data) VALUES (?,?,?,?,?)");

				stmtRec.setInt(1, r.getU().getId());
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
			
	
	//FUNZIONA
	public Citta select_Citta(String nome) {
			Citta c = null;
			conn = null;
			PreparedStatement stmt = null;
			ResultSet r = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection(url, user, pwd);
				stmt = conn.prepareStatement("SELECT * FROM ST_CittaEuropea WHERE Nome = ?");
				stmt.setString(1, nome);
				
				r = stmt.executeQuery();
				while(r.next()) {
					int id = r.getInt("Id");
					String s = r.getString("Stato");
					c = new Citta(id,nome,s);
					
				}} catch (Exception e) {
					e.getMessage();
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
	//FUNZIONA
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
				int id = r.getInt("Id");
				String c = r.getString("Cognome");
				String n = r.getString("Nome");
				String e = r.getString("Email");
				String nz = r.getString("Nazionalità");
				String d = r.getString("DataDiNascita");
				u = new Utente(c, n, e, username, pwdU, nz, d);
				u.setId(id);
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
	
	//FUNZIONA
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

	
	//FUNZIONA
	public Citta select_Citta1(int id) {
					Citta c = null;
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
							String nomeC = r.getString("Nome");
							String statoC = r.getString("Stato");
							c = new Citta(id, nomeC, statoC);
						}} catch (Exception e) {
							e.getStackTrace();
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
	//FUNZIONA
	public ElencoCitta select_Citta() {
		Citta c = null;
		ElencoCitta ec = new ElencoCitta();
		conn = null;
		PreparedStatement stmt = null;
		ResultSet r = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, pwd);
			stmt = conn.prepareStatement("SELECT * FROM ST_CittaEuropea");
			
			r = stmt.executeQuery();
			while(r.next()) {
				
				c = new Citta();
                c.setId(r.getInt("id"));
                c.setNome(r.getString("nome"));
                c.setStato(r.getString("stato"));
				ec.add(c);
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
		return ec;
	} 

	//select elenco delle recensioni inserite in database, CONTROLLA
	public ElencoRecensioni select_Recensioni() {
		Utente u = null;
		Citta c = null;
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
				u = select_Utente1(idU);
				int idC = r.getInt("Id_CittaEuropea");
				String descr = r.getString("Descrizione");
				float voto = r.getFloat("Voto");
				String data = r.getString("Data");
				
				c = select_Citta1(idC);
				Recensione re = new Recensione(descr, voto, data);
				re.setU(u);
				re.setC(c);
				elencoR.add(re);
			}} catch (Exception e) {
				e.getStackTrace();
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
	
	
	//select dati della ragazza dato id, controlla
	public Utente select_Utente1(int id) {
		Utente u = null;
		conn = null;
		PreparedStatement stmt = null;
		ResultSet r = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, pwd);
			stmt = conn.prepareStatement("SELECT * FROM ST_UtenteRagazza WHERE Id = ? ");
			stmt.setInt(1, id);
			r = stmt.executeQuery();
			while(r.next()) {
				String n = r.getString("Nome");
				String c = r.getString("Cognome");
				String e = r.getString("Email");
				String pwdU = r.getString("Password");
				String nz = r.getString("Nazionalità");
				String d = r.getString("DataDiNascita");
				String username = r.getString("Username");

				u = new Utente(c, n, e, username, pwdU, nz, d);
			}} catch (Exception e) {
				e.getStackTrace();
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
	//FUNZIONA
	public ElencoRecensioni Select_RecensioniUtente(Utente u) {
		ElencoRecensioni elencoR = new ElencoRecensioni();
		Citta c = null;
		Recensione re = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet r = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, pwd);
			stmt = conn.prepareStatement("SELECT * FROM ST_Recensione WHERE Id_Ragazza = ?");
			stmt.setInt(1, u.getId());
			
			r = stmt.executeQuery();
			while(r.next()) {
				int idC = r.getInt("Id_CittaEuropea");
				String descr = r.getString("Descrizione");
				float voto = r.getFloat("Voto");
				String data = r.getString("Data");
				c = select_Citta1(idC);
				re = new Recensione(descr, voto, data);
				re.setU(u);
				re.setC(c);
				
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

	//select elenco delle recensioni di una determinata città inserita in database, CONTROLLA
	public ElencoRecensioni select_RecensioniCitta(Citta c) {
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
				String descr = r.getString("Descrizione");
				float voto = r.getFloat("Voto");
				String data = r.getString("Data");
				u = select_Utente1(idU);
				Recensione re = new Recensione(descr, voto, data);
				re.setU(u);
				re.setC(c);
				elencoR.add(re);
			}} catch (Exception e) {
				e.getStackTrace();
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
	
	public static void main(String[] args) {
		RecensioneService rs = new RecensioneService();
		Utente u = null;
		u = rs.select_Utente1(4252);
		System.out.println(u.toString());		
	}
}