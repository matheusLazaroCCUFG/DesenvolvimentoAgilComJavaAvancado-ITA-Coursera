package testes;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class WebTradutorTestes {
private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
	  @Test
	  public void webTradutor1() throws Exception {
	     driver.get("http://localhost:8084/WebTradutor/");
            driver.findElement(By.name("palavra")).click();
            driver.findElement(By.name("palavra")).sendKeys("monkey");
            driver.findElement(By.cssSelector("input:nth-child(2)")).click();
            driver.findElement(By.id("traducao")).click();
            assertThat(driver.findElement(By.id("traducao")).getText(), is("monkey pode ser traduzido como: macaco."));
	  }
	  
	  @Test
	  public void webTradutor2() throws Exception {
	     driver.get("http://localhost:8084/WebTradutor/");
            driver.findElement(By.name("palavra")).click();
            driver.findElement(By.name("palavra")).sendKeys("seconds");
            driver.findElement(By.cssSelector("input:nth-child(2)")).click();
            driver.findElement(By.id("traducao")).click();
            assertThat(driver.findElement(By.id("traducao")).getText(), is("seconds pode ser traduzido como: segundos."));
            
	  }
	  
	   @Test
	  public void webTradutor3() throws Exception {
	     driver.get("http://localhost:8084/WebTradutor/");
            driver.findElement(By.name("palavra")).click();
            driver.findElement(By.name("palavra")).sendKeys("table");
            driver.findElement(By.cssSelector("input:nth-child(2)")).click();
            driver.findElement(By.id("traducao")).click();
            assertThat(driver.findElement(By.id("traducao")).getText(), is("table pode ser traduzido como: table."));
	  }

	

}
