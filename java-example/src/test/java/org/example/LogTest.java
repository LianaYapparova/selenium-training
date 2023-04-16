package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.stream.IntStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class LogTest {
  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeClass
  public void start() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(20));
  }

  @BeforeMethod
  public void login() {
    driver.get("http://localhost/litecart/admin/");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }

  @Test
  public void logTest() {
    driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
    int size = driver.findElements(By.cssSelector(".dataTable img ~ a")).size();
    IntStream.range(0, size).forEach(this::checkTheLog);
  }

  private void checkTheLog(int i) {
    driver.findElements(By.cssSelector(".dataTable img ~ a")).get(i).click();
    Assert.assertEquals(driver.manage().logs().get("browser").getAll().size(), 0);
    driver.navigate().back();
  }
  @AfterMethod
  public void logout() {
    driver.findElement(By.cssSelector("a[title=Logout]")).click();
  }

  @AfterClass(alwaysRun = true)
  public void stop() {
    driver.quit();
  }
}
