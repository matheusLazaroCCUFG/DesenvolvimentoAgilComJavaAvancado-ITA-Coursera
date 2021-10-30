package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathe
 */
public class UsuarioDB implements UsuarioDAO {
    
    static{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void inserir(Usuario u) {
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:8080/coursera", "postgres", "admin")){
            String sql = "INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement stm = con.prepareStatement(sql);
            
            stm.setString(1, u.getLogin());
            stm.setString(2, u.getEmail());
            stm.setString(3, u.getNome());
            stm.setString(4, u.getSenha());
            stm.setInt(5, u.getPontos());
            stm.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException("Erro ao inserir usuario", e);
        }
    }

    @Override
    public Usuario recuperar(String login) {
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:8080/coursera", "postgres", "admin")){
            String sql = "SELECT * FROM usuario WHERE login = ?";
            
            PreparedStatement stm = con.prepareStatement(sql);
            Usuario usuario = new Usuario();
            
            stm.setString(1, login);
            ResultSet rs = stm.executeQuery();
            rs.next();
            usuario.setEmail(rs.getString("email"));
            usuario.setLogin(rs.getString("login"));
            usuario.setNome(rs.getString("nome"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setPontos(rs.getInt("pontos"));
            
            return usuario;
        }catch(SQLException e){
            System.out.println(e);
            throw new RuntimeException("Erro ao recuperar usuário", e);
        }
    }

    @Override
    public void adicionarPontos(String login, int pontos) {
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:8080/coursera", "postgres", "admin")){
            String sql = "UPDATE usuario SET pontos = pontos + ? WHERE login = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            
            stm.setInt(1, pontos);
            stm.setString(2, login);
            stm.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException("Erro ao adicionar pontos ao usuário", e);
        }
    }

    @Override
    public List<Usuario> ranking() {
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:8080/coursera", "postgres", "admin")){
            String sql = "SELECT * FROM usuario ORDER BY pontos DESC";
            PreparedStatement stm = con.prepareStatement(sql);
            List<Usuario> lista = new ArrayList<>();
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setEmail(rs.getString("email"));
                usuario.setLogin(rs.getString("login"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setPontos(rs.getInt("pontos"));
                lista.add(usuario);
            }
            return lista;
        }catch(SQLException e){
            throw new RuntimeException("Erro ao ranquear usuário.", e);
        }
    }
    
}
