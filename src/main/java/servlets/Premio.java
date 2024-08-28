package servlets;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class Premio extends  jakarta.servlet.http.HttpServlet{
	
	public int visitas;
	public String mensajePersonalizado;
	
	//Consigue el parámetro "mensajePersonalizado del archivo web.xml
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.mensajePersonalizado = config.getInitParameter("mensajePersonalizado");
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws IOException {
		
		this.visitas +=1;
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<html><body>");
		out.println("<h1>" + this.modificarMensajePersonalizado(request.getParameter("nombre")) + "</h1>");
		out.println("</body></html>");
		out.close();
	}
	
	//genera el mensaje personalizado tomando el parámetro que se recibe del formulario.
	private String modificarMensajePersonalizado(String requestParameterNombre) {
		return this.mensajePersonalizado.replace("@", requestParameterNombre).replace("#", String.valueOf(this.visitas));
	}
	
	


}
