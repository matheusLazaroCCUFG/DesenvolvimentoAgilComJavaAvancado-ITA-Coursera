/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author mathe
 */

public class TestesSelenium {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void verRanking() throws Exception {
        driver.get(baseUrl + "/Forum/login.jsp");
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("joao");
        driver.findElement(By.name("senha")).clear();
        driver.findElement(By.name("senha")).sendKeys("1234");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.cssSelector("body > form > input[type=\"submit\"]")).click();
        assertEquals("Ranking dos usuários", driver.findElement(By.cssSelector("h1")).getText());
    }

    @Test
    public void verTopico() throws Exception {
        driver.get(baseUrl + "/Forum/login.jsp");
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("joao");
        driver.findElement(By.name("senha")).clear();
        driver.findElement(By.name("senha")).sendKeys("1234");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        assertEquals("Exibindo tópico:", driver.findElement(By.cssSelector("h2")).getText());
    }

    @Test
    public void cadastrarUsuario() throws Exception {
        driver.get(baseUrl + "/Forum/login.jsp");
        driver.findElement(By.xpath("//input[@value='Novo usuário']")).click();
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("fulano");
        driver.findElement(By.name("senha")).clear();
        driver.findElement(By.name("senha")).sendKeys("fula");
        driver.findElement(By.name("nome")).clear();
        driver.findElement(By.name("nome")).sendKeys("Fulano da Silva");
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("fulano@email.com");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("fulano");
        driver.findElement(By.name("senha")).clear();
        driver.findElement(By.name("senha")).sendKeys("fula");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.cssSelector("body > form > input[type=\"submit\"]")).click();
        assertEquals("Fulano da Silva", driver.findElement(By.xpath("//table[@id='2']/tbody/tr[4]/td[2]")).getText());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}