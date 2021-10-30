/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

/**
 * Implementar uma classe que implementa a interface  UsuarioDAO
 * Fazer acesso ao banco de dados coursera com JDBC
 * Cada um dos métodos deve ser testeado com DBUnit
 * 
 * @author mathe
 */
public class Usuario {
    private String login;
    private String email;
    private String nome;
    private String senha;
    private int pontos;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    @Override
    public String toString() {
        return "Usuario{" + "login=" + login + ", email=" + email + ", nome=" + nome + ", senha=" + senha + ", pontos=" + pontos + '}';
    }
    
    

    
}
