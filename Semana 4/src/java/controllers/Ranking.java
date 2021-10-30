/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAOs.UsuarioDAO;
import sistema.Usuario;

/**
 *
 * @author mathe
 */
@WebServlet("/ranking")
public class Ranking extends HttpServlet{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            List<Usuario> lista = new UsuarioDAO().getTodosUsuarios();
            req.setAttribute("usuarios", lista);
            req.getRequestDispatcher("ranking.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
