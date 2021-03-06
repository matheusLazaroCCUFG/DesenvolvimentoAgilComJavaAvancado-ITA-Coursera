// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.Dimension;

import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

public class ConverterTemperatura5Test {
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
  public void converterTemperatura6() {
    driver.get("http://localhost:8080/ConverterTemperatura/");
    driver.manage().window().setSize(new Dimension(732, 699));
    driver.findElement(By.cssSelector("label:nth-child(5)")).click();
    driver.findElement(By.name("valor")).click();
    driver.findElement(By.name("valor")).sendKeys("100");
    driver.findElement(By.name("converter")).click();
    driver.findElement(By.cssSelector("h1")).click();
    assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("O resultado da conversão é: 212° Fahrenheit"));
  }
}
