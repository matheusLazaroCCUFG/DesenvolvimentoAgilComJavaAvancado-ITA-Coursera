package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sistema.Traducao;

/**
 * Aplicação Web com MVC utilizando Servlets e JSP
 * Campo texto + botão "Traduzir"
 * Processar a palavra e retornar uma página com a tradução
 * Buscar as traduções em um arquivo com as palavras em Inglês e Português
 * Formato do arquivo por conta do aluno
 * Arquivo com pelo menos 20 pares de palavra + tradução
 * Caso não seja encontrada a palavra, retornar a própria palavra.
 * 
 * Testes:
 * Criar 3 testes funcionais com Selenium
 * 2 Testes para palavras diferentes que existem no arquivo
 * 1 teste com palavra que não existe no arquivo.
 * 
 * @author mathe
 */

@WebServlet("/traduzir")
public class WebTradutorController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String palavra = request.getParameter("palavra");
        Traducao traducao = new Traducao();
        
        request.setAttribute("traducao", traducao.traduzir(palavra));
        request.getRequestDispatcher("traducao.jsp").forward(request, response);
    }
}
