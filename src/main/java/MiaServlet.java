import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MiaServlet
 */
@WebServlet("/MiaServlet")
public class MiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MiaServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String n = request.getParameter("nome");
		
		PrintWriter pw = new PrintWriter(response.getOutputStream());
		
		pw.write("<!doctype html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "  <head>\r\n"
				+ "    <meta charset=\"utf-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "    <title>Bootstrap demo</title>\r\n"
				+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">\r\n"
				+ "  </head>\r\n"
				+ "  <body>\r\n"
				+ "    <nav class=\"navbar navbar-expand-lg bg-body-tertiary\">\r\n"
				+ "  <div class=\"container-fluid\">\r\n"
				+ "    <a class=\"navbar-brand\" href=\"#\">Navbar</a>\r\n"
				+ "    <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n"
				+ "      <span class=\"navbar-toggler-icon\"></span>\r\n"
				+ "    </button>\r\n"
				+ "    <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\r\n"
				+ "      <ul class=\"navbar-nav me-auto mb-2 mb-lg-0\">\r\n"
				+ "        <li class=\"nav-item\">\r\n"
				+ "          <a class=\"nav-link active\" aria-current=\"page\" href=\"#\">Home</a>\r\n"
				+ "        </li>\r\n"
				+ "        <li class=\"nav-item\">\r\n"
				+ "          <a class=\"nav-link\" href=\"#\">Link</a>\r\n"
				+ "        </li>\r\n"
				+ "        <li class=\"nav-item dropdown\">\r\n"
				+ "          <a class=\"nav-link dropdown-toggle\" href=\"#\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">\r\n"
				+ "            Dropdown\r\n"
				+ "          </a>\r\n"
				+ "          <ul class=\"dropdown-menu\">\r\n"
				+ "            <li><a class=\"dropdown-item\" href=\"#\">Action</a></li>\r\n"
				+ "            <li><a class=\"dropdown-item\" href=\"#\">Another action</a></li>\r\n"
				+ "            <li><hr class=\"dropdown-divider\"></li>\r\n"
				+ "            <li><a class=\"dropdown-item\" href=\"#\">Something else here</a></li>\r\n"
				+ "          </ul>\r\n"
				+ "        </li>\r\n"
				+ "        <li class=\"nav-item\">\r\n"
				+ "          <a class=\"nav-link disabled\" aria-disabled=\"true\">Disabled</a>\r\n"
				+ "        </li>\r\n"
				+ "      </ul>\r\n"
				+ "      <form class=\"d-flex\" role=\"search\">\r\n"
				+ "        <input class=\"form-control me-2\" type=\"search\" placeholder=\"Search\" aria-label=\"Search\">\r\n"
				+ "        <button class=\"btn btn-outline-success\" type=\"submit\">Search</button>\r\n"
				+ "      </form>\r\n"
				+ "    </div>\r\n"
				+ "  </div>\r\n"
				+ "</nav>\r\n"
				+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL\" crossorigin=\"anonymous\"></script>\r\n"
				+ "  </body>\r\n"
				+ "</html>");
		for(int i=0; i<20; i++) {
			pw.write("Numero: "+i);
		}
		pw.close();
		
		//prendi utenti dal db
		// 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		info = request.getInputStream()
				
		// carica info su db
				
	}*/
}