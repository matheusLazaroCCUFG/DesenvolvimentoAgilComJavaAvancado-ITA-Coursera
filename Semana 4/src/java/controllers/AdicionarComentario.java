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

import DAOs.ComentarioDAO;
import sistema.Comentario;
import sistema.Usuario;

/**
 *
 * @author mathe
 */
@WebServlet("/adicionarComentario")
public class AdicionarComentario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Comentario c = new Comentario();
        ComentarioDAO cDao = new ComentarioDAO();
        Usuario us = (Usuario) request.getSession().getAttribute("usuario");
        c.setLogin(us.getLogin());
        c.setComentario(request.getParameter("comentario"));
        int topicoID = Integer.parseInt(request.getParameter("id_topico"));
        c.setId_topico(topicoID);
        cDao.adicionaComentario(c);
        request.getRequestDispatcher("exibir?topicoID="+topicoID).forward(request, response);
    }

}
