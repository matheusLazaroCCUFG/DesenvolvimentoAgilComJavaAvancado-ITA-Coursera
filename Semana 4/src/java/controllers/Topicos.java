/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAOs.TopicosDAO;
import sistema.Topico;

/**
 * @author mathe
 */
@WebServlet("/topicos")
public class Topicos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        List<Topico> lista = new ArrayList<>();
        try {
            lista = new TopicosDAO().getTodosTopicos();
            request.getSession().setAttribute("topicos", lista);
            request.getRequestDispatcher("topicos.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Erro ao recuperar tópicos");
        }
    }
}
