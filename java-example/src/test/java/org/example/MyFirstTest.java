package org.example;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MyFirstTest {

  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeClass
  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, Duration.ofMillis(100));
  }

  @Test
  public void myFirstTest() {
    driver.get("http://www.google.com");
    driver.findElement(By.name("q")).sendKeys("webdriver");
    driver.findElements(By.name("btnK")).get(1).click();
    wait.until(titleIs("webdriver - Поиск в Google"));
  }

  @AfterClass
  public void stop() {
    driver.quit();
//    driver = null;
  }
}
