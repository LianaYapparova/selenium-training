package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StickerTest {

  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeClass
  public void start() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, Duration.ofMillis(100));
  }

  @Test
  public void stickerTest() {
    driver.get("http://localhost/litecart/en/");
    List<WebElement> webElementList = driver.findElements(By.className("product"));
    IntStream.range(0, webElementList.size()).forEach(i -> {
      Assert.assertNotNull(webElementList.get(i).findElement(By.className("sticker")));
    });
  }

  @AfterClass(alwaysRun = true)
  public void stop() {
    driver.quit();
  }
}
