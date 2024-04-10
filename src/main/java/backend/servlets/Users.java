package backend.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.database.DBService;
import backend.database.beans.UserBean;

/**
 * Servlet implementation class Users
 */
@WebServlet("/Users")
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String SELECT_DOCENTE = "SELECT * FROM DOC_Docente WHERE Id = ?";
	private static final String INSERT_DOCENTE = "INSERT INTO DOC_Docente (Cognome, Nome) VALUES (?, ?)";
	private static final String UPDATE_DOCENTE = "UPDATE DOC_Docente SET Cognome = ?, Nome = ? WHERE Id = ?";
	private static final String DELETE_DOCENTE = "DELETE FROM DOC_Docente WHERE Id = ?";
	
	private static final String SELECT_STUDENTI_VOTO = "SELECT * FROM DOC_Studente INNER JOIN DOC_Voto ON DOC_Studente.Id = DOC_Voto.IdStudente ORDER BY DOC_Studente.Id";

	private static final String SELECT_USERS = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Users() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBService ser = new DBService();
		ResultSet set = ser.query(SELECT_USERS);
		
		try {
			ArrayList<UserBean> users = new ArrayList();
			// Prendi info dal database
			while(set.next()) {
				continue;
			}
			
			// Manda info a chi fa una GET request a /Users
			ServletOutputStream stream = response.getOutputStream();
			
			for (UserBean u : users) {
				stream.write(u.getData());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(set != null) {
				try {
					set.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
