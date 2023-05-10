package automacaoWeb;

import br.com.chronosacademy.core.Driver;
import br.com.chronosacademy.pages.CursoPage;
import br.com.chronosacademy.pages.PrincipalPage;
import enulls.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class TestWeb {
    WebDriver driver;
    Driver driverWeb;
    PrincipalPage principalPage;
    CursoPage cursoPage;
    @Before
    public void inicializaTeste(){
      driverWeb = new Driver(Browser.CHROME);
      driver = Driver.getDriver();
      driver.get("https://www.chronosacademy.com.br/");
      principalPage = new PrincipalPage(driver);
      cursoPage = new CursoPage(driver);
    }
    @Test
    public void primeiroTeste(){
        String Titulo = principalPage.getTitulo();
        assertEquals("Porque Tempo É Conhecimento.",Titulo);


    }



    @Test
    public void segundoTeste(){
        principalPage.clickBotao();

        String Titulo = cursoPage.getTitulo2();
        assertEquals("Conheça todos os nossos cursos.",Titulo);
    }





    @After
    public void finalizaTeste(){
        driver.quit();
    }
}
