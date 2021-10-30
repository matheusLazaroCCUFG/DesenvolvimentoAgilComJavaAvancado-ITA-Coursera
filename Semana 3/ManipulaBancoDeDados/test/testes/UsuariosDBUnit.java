package testes;

import java.util.List;
import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import static org.junit.Assert.*;
import sistema.Usuario;
import sistema.UsuarioDB;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mathe
 */
public class UsuariosDBUnit {
    JdbcDatabaseTester jdt;
    UsuarioDB conectarBD;
    
    @Before
    public void SetUp() throws Exception{
        jdt = new JdbcDatabaseTester("org.postgresql.Driver", "jdbc:postgresql://localhost:8080/coursera", "postgres", "admin");
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        jdt.setDataSet(loader.load("/inicio.xml"));
        jdt.onSetup();
        conectarBD = new UsuarioDB();
    }

    @Test
    public void inserir() throws Exception{
        Usuario usuario = new Usuario();
        usuario.setLogin("usuario1");
        usuario.setEmail("usuario1@email.com");
        usuario.setNome("Nome Usuario1");
        usuario.setSenha("senha");
        usuario.setPontos(10);
        
        conectarBD.inserir(usuario);
        
        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("usuario");
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        IDataSet expectedDataSet = loader.load("/adcUsuario.xml");
        ITable expectedTable = expectedDataSet.getTable("usuario");
        Assertion.assertEquals(expectedTable, currentTable);
    }
    
    @Test
    public void recuperar(){
        Usuario usuario = conectarBD.recuperar("usuario1");
        assertEquals("Nome Usuario1", usuario.getNome());
        assertEquals("usuario1@email.com", usuario.getEmail());
    }
    
    @Test
    public void adicionarPontos() throws Exception{
        conectarBD.adicionarPontos("usuario1", 5);
        
        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("usuario");
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        IDataSet expectedDataSet = loader.load("/adcPontos.xml");
        ITable expectedTable = expectedDataSet.getTable("usuario");
        Assertion.assertEquals(expectedTable, currentTable);
    }
    

    
    
    @Test
    public void ranking(){
        List<Usuario> lista = conectarBD.ranking();
        assertEquals("usuario2", lista.get(0).getLogin());
        assertEquals("usuario1", lista.get(1).getLogin());
    }

}
