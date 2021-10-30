import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/ConverterTemperaturaServlet", "/converter" })
public class ConverterTemperaturaServlet extends HttpServlet {       

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		int checkbox = Integer.parseInt(request.getParameter("converterPara"));
		int valor = Integer.parseInt(request.getParameter("valor"));
		int resultado;
		String strResultado;

		if(checkbox == 1) {
			resultado = fahrenheitParaCelsius(valor);
			strResultado = " Celsius";
		}else {
			resultado = celsiusParaFahrenheit(valor);
			strResultado = " Fahrenheit";

		}
		

		
		try(PrintWriter out = response.getWriter()){
			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<head>");
			out.print("<title>Resultado da conversao</title>");
			out.print("</head>");
			out.print("</body>");
			out.print("<h1>O resultado da conversão é: " + resultado + "° " + strResultado + "</h1>");
			out.print("</body>");
			out.print("</html>");
		}
	}
	
	public int celsiusParaFahrenheit(int celsius) {
		return((celsius * 9)/5)+32;
	}
	
	public int fahrenheitParaCelsius(int fahrenheit) {
		return((fahrenheit - 32) * 5)/9;
	}

}
