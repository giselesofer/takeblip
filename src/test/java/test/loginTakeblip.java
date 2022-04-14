package test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class loginTakeblip {
    String url = "https://account.blip.ai/login";
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void TestLoginValid() {
        driver.get(url);
        driver.manage().window().setSize(new Dimension(1280, 680));
        driver.findElement(By.id("email")).sendKeys("teste.auttst@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Tb123456@@");
        driver.findElement(By.id("blip-login")).click();
        assertThat(driver.findElement(By.cssSelector(".bp-fs-3")).getText(), is("My account"));
    }

    @Test
    public void TestLoginInValid() {
        driver.get(url);
        driver.manage().window().setSize(new Dimension(1280, 680));
        driver.findElement(By.id("email")).sendKeys("teste.auttst@gmail.com");
        driver.findElement(By.id("password")).sendKeys("123333");
        driver.findElement(By.id("blip-login")).click();
        assertThat(driver.findElement(By.cssSelector(".bottom-left > .hydrated")).getText(), is("Login e/ou senha inválidos"));
    }

}