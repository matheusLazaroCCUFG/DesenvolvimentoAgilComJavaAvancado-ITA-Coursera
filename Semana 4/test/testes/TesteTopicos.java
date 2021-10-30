/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import static org.junit.Assert.*;
import java.util.List;
import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;
import DAOs.TopicosDAO;
import sistema.Topico;

/**
 *
 * @author mathe
 */
public class TesteTopicos {
    JdbcDatabaseTester jdt;

    @Before
    public void setUp() throws Exception {
        jdt = new JdbcDatabaseTester(
            "org.postgresql.Driver",
            "jdbc:postgresql://localhost/forum",
            "postgres",
            "senha"
        );
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        jdt.setDataSet(loader.load("/inicio.xml"));
        jdt.onSetup();
    }

    @Test
    public void recuperarUmTopico(){
        Topico top = new TopicosDAO().getTopico(1);
        assertEquals("Primeiro topico", top.getTitulo());
    }

    @Test
    public void recuperarTodosTopicos() {
        List<Topico> lista = new TopicosDAO().getTodosTopicos();
        assertEquals(1, lista.size());
    }

    @Test
    public void adicionarTopico() throws Exception{
        Topico top = new Topico();
        TopicosDAO topDao = new TopicosDAO();
        top.setLogin("maria");
        top.setTitulo("Segundo topico");
        top.setConteudo("segundo topico do forum");
        top.setId(2);
        topDao.adiconarTopico(top);

        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("topico");
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        IDataSet expectedDataSet = loader.load("/verifica.xml");
        ITable expectedTable = expectedDataSet.getTable("topico");
        Assertion.assertEquals(expectedTable, currentTable);
    }
}
