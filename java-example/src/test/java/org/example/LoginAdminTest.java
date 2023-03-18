package org.example;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginAdminTest {

  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeClass
  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, Duration.ofMillis(100));
  }

  @Test
  public void loginAdminTest() {
    driver.get(" http://localhost/litecart/admin/");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }

  @AfterClass(alwaysRun = true)
  public void stop() {
    driver.quit();
  }
}
