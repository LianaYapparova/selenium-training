package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.stream.IntStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MenuAdminTest {
  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeClass
  public void start() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, Duration.ofMillis(100));
  }

  @Test
  public void menuAdminTest() {
    driver.get(" http://localhost/litecart/admin/");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
    int size = driver.findElement(By.id("box-apps-menu")).findElements(By.id("app-")).size();
    clickAllElements(size);
  }

  private void clickAllElements(int size) {
    IntStream.range(0, size).forEach(i -> {
      driver.findElement(By.id("box-apps-menu")).findElements(By.id("app-")).get(i).click();
      clickInnerElement(driver.findElements(By.cssSelector(".selected li")).size());
    });
  }

  private void clickInnerElement(int size) {
    IntStream.range(0, size).forEach(i -> {
      driver.findElements(By.cssSelector(".selected li")).get(i).click();
      driver.findElement(By.cssSelector("h1"));
    });
  }


  @AfterClass(alwaysRun = true)
  public void stop() {
    driver.quit();
  }
}
