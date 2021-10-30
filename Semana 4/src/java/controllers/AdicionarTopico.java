/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAOs.TopicosDAO;
import sistema.Topico;
import sistema.Usuario;

/**
 *
 * @author mathe
 */
@WebServlet("/adicionarTopico")
public class AdicionarTopico extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Topico top = new Topico();
        TopicosDAO topDao = new TopicosDAO();
        Usuario us = (Usuario) request.getSession().getAttribute("usuario");

        top.setTitulo(request.getParameter("titulo"));
        top.setConteudo(request.getParameter("conteudo"));
        top.setLogin(us.getLogin());

        topDao.adiconarTopico(top);
        request.getRequestDispatcher("topicos").forward(request, response);

    }
}
