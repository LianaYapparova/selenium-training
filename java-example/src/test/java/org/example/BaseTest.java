package org.example;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
  public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
  public WebDriver driver;
  public WebDriverWait wait;

  @BeforeClass
  public void start() {
    if (tlDriver.get() != null) {
      driver = tlDriver.get();
      wait = new WebDriverWait(driver, Duration.ofSeconds(20));
      return;
    }

    driver = new ChromeDriver();
    tlDriver.set(driver);
    wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    Runtime.getRuntime().addShutdownHook(
        new Thread(() -> { driver.quit(); driver = null; }));
  }

  @AfterClass(alwaysRun = true)
  public void stop() {
    driver.quit();
  }
}
