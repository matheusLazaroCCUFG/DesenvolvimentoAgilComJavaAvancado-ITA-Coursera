/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import JDBC.ConnectionFactory;
import sistema.Usuario;

/**
 *
 * @author mathe
 */
public class UsuarioDAO {
    public boolean login(String login, String senha) throws Exception {
        try (Connection c = ConnectionFactory.getConexao()) {
            PreparedStatement stm = c.prepareStatement(
                "select nome from usuario" +
                " where login=? and senha=?"
            );
            stm.setString(1, login);
            stm.setString(2, senha);
            ResultSet rs = stm.executeQuery();

            if (rs.next())
                return true;
            else
                return false;
        } catch (Exception e) {
            throw new Exception("Erro ao fazer login", e);
        }
    }

    public Usuario getUsuario(String login) throws Exception {
        Usuario usuario = new Usuario();
        try (Connection c = ConnectionFactory.getConexao()) {
            PreparedStatement stm = c.prepareStatement(
                    "select * from usuario where login=?"
            );
            stm.setString(1, login);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                usuario.setEmail(rs.getString("email"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setPontos(rs.getInt("pontos"));
            }
            return usuario;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar usuário", e);
        }
    }

    public List<Usuario> getTodosUsuarios() throws Exception {
        List<Usuario> lista = new ArrayList<>();
        try (Connection c = ConnectionFactory.getConexao()) {
            PreparedStatement stm = c.prepareStatement(
                    "select * from usuario order by pontos desc"
            );
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setEmail(rs.getString("email"));
                u.setLogin(rs.getString("login"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                u.setPontos(rs.getInt("pontos"));
                lista.add(u);
            }
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro ao recuperar usuários", e);
        }
    }

    public void adicionarUsuario(Usuario us) {
        try (Connection c = ConnectionFactory.getConexao()) {
            String sql = 
            "insert into usuario(login, email, nome, senha, pontos)" +
            " values(?, ?, ?, ?, ?);";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, us.getLogin());
            stm.setString(2, us.getEmail());
            stm.setString(3, us.getNome());
            stm.setString(4, us.getSenha());
            stm.setInt(5, 0);

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
