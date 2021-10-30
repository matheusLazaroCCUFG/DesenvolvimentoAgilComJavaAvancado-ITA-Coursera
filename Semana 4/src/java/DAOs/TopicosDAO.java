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
import sistema.Topico;

/**
 *
 * @author mathe
 */
public class TopicosDAO {
    public List<Topico> getTodosTopicos(){
        List<Topico> topicos = new ArrayList<>();

        try (Connection c = ConnectionFactory.getConexao()) {
            String sql = "select * from topico";
            PreparedStatement stm = c.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Topico topico = new Topico();
                topico.setId(rs.getInt("id_topico"));
                topico.setLogin(rs.getString("login"));
                topico.setTitulo(rs.getString("titulo"));
                topico.setConteudo(rs.getString("conteudo"));
                topicos.add(topico);
            }
            return topicos;
        } catch (Exception e) {
            throw new RuntimeException(
                    "Erro ao recuperar topicos",
                    e
            );
        }   
    }
    
    public Topico getTopico(int id){
        Topico topico = new Topico();

        try (Connection c = ConnectionFactory.getConexao()) {
            PreparedStatement stm = c.prepareStatement(
                "select * from topico where id_topico=?"
            );
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            rs.next();
            topico.setConteudo(rs.getString("conteudo"));
            topico.setId(id);
            topico.setTitulo(rs.getString("titulo"));
            topico.setLogin(rs.getString("login"));

            return topico;
        } catch (Exception e) {
            throw new RuntimeException(
                    "Erro ao recuperar topico",
                    e
            );
        }
    }

    public void adiconarTopico(Topico top) {
        try (Connection c = ConnectionFactory.getConexao()) {
            if (top.getId() == 0) {
                PreparedStatement stmTopico = c.prepareStatement(
                    "INSERT INTO topico" +
                    "(titulo, conteudo, login) VALUES (?, ?, ?);"
                );
                stmTopico.setString(1, top.getTitulo());
                stmTopico.setString(2, top.getConteudo());
                stmTopico.setString(3, top.getLogin());
                stmTopico.executeUpdate();
            } else {
                PreparedStatement stmTopico = c.prepareStatement(
                    "INSERT INTO topico" +
                    "(titulo, conteudo, login, id_topico) VALUES (?, ?, ?, ?);"
                );
                stmTopico.setString(1, top.getTitulo());
                stmTopico.setString(2, top.getConteudo());
                stmTopico.setString(3, top.getLogin());
                stmTopico.setInt(4, top.getId());
                stmTopico.executeUpdate();
            }

            PreparedStatement stmUsuario = c.prepareStatement(
                "UPDATE usuario SET " +
                "pontos=pontos+10 WHERE login=?"
            );
            stmUsuario.setString(1, top.getLogin());
            stmUsuario.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar topico no banco de dados");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
