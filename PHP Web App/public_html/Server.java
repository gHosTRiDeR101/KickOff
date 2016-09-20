import java.io.*;
import javax.servlet.*;


public class Server extends HttpServlet {

	private String message; 

	public void init() throws ServletException {


	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<h1>" + message + "</h1>");
	}

	public void destroy() 
	{


	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                                                              
                                                        

                response.setContentType("text/html");

                                                               

                PrintWriter out = response.getWriter();

                out.println("<h1>" + message + "</h1>");

        }


}
