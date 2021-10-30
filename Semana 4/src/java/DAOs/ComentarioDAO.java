/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import JDBC.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistema.Comentario;
import sistema.Usuario;

/**
 *
 * @author mathe
 */
public class ComentarioDAO {
    
    public List<Comentario> getComentariosTopico(int id) {
        List<Comentario> comentarios = new ArrayList<>();

        try (Connection c = ConnectionFactory.getConexao()) {
            PreparedStatement stm = c.prepareStatement(
                "select * from comentario where id_topico=?"
            );

            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Comentario com = new Comentario();

                com.setComentario(rs.getString("comentario"));
                com.setLogin(rs.getString("login"));
                com.setId_comentario(rs.getInt("id_comentario"));
                com.setId_topico(rs.getInt("id_topico"));
                comentarios.add(com);
            }

            return comentarios;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar comentários", e);
        }
    }
    
    public void adicionaComentario(Comentario com) {
        try (Connection c = ConnectionFactory.getConexao()) {

            if (com.getId_comentario() == 0) {
                PreparedStatement stm = c.prepareStatement(
                    "insert into comentario" +
                    "(comentario, login, id_topico) VALUES (?, ?, ?)"
                );
                stm.setString(1, com.getComentario());
                stm.setString(2, com.getLogin());
                stm.setInt(3, com.getId_topico());
                stm.executeUpdate();
            } else {
                PreparedStatement stm = c.prepareStatement(
                    "insert into comentario" +
                    "(comentario, login, id_topico, id_comentario) VALUES (?, ?, ?, ?)"
                );
                stm.setString(1, com.getComentario());
                stm.setString(2, com.getLogin());
                stm.setInt(3, com.getId_topico());
                stm.setInt(4, com.getId_comentario());
                stm.executeUpdate();
            }

            Usuario u = new UsuarioDAO().getUsuario(com.getLogin());
            PreparedStatement stmUsuario = c.prepareStatement(
                    "UPDATE usuario SET " + "pontos=pontos+3 WHERE login=?"
            );
            stmUsuario.setString(1, u.getLogin());
            stmUsuario.executeUpdate();
        } catch (
            SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
}

    public List<Usuario> getAutores(List<Comentario> comentarios) {
        List<Usuario> lista = new ArrayList<>();
        
        for (Comentario comentario : comentarios) {
            try (Connection c = ConnectionFactory.getConexao()) {
                Usuario us = new UsuarioDAO().getUsuario(comentario.getLogin());
                lista.add(us);
            } catch (Exception e) {
                throw new RuntimeException(
                        "Erro ao recuperar autores",
                        e
                );
            }
        }
        return lista;
    }
}
