/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.List;
import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;
import DAOs.ComentarioDAO;
import sistema.Comentario;

/**
 *
 * @author mathe
 */
public class TesteComentarios {
    JdbcDatabaseTester jdt;

    //@Before
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

    //@Test
    public void RecuperarComentarios() {
        List<Comentario> lista = new ComentarioDAO().getComentariosTopico(1);
        assertEquals(1, lista.size());
        assertEquals("maria", lista.get(0).getLogin());
    }

    //@Test
    public void adiconarComentario() throws SQLException, Exception{
        Comentario com = new Comentario();
        ComentarioDAO comDao = new ComentarioDAO();
        com.setComentario("segundo comentario");
        com.setLogin("maria");
        com.setId_topico(1);
        com.setId_comentario(2);

        comDao.adicionaComentario(com);

        IDataSet currentDataSet = jdt.getConnection().createDataSet();
        ITable currentTable = currentDataSet.getTable("comentario");
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        IDataSet expectedDataSet = loader.load("/verifica.xml");
        ITable expectedTable = expectedDataSet.getTable("comentario");
        Assertion.assertEquals(expectedTable, currentTable);
    }
}
