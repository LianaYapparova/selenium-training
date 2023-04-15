package org.example;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.stream.IntStream;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class BucketProductTest {
  private WebDriver driver;
  private WebDriverWait wait;
  private static final Faker faker = new Faker();

  @BeforeClass
  public void start() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(20));
  }

  @Test
  public void bucketProductTest() {
    driver.get("http://localhost/litecart/en/");
    IntStream.range(0,3).forEach(i-> addProductToBucket());
    driver.findElement(By.cssSelector("#cart .content")).click();
    int size = driver.findElements(By.cssSelector(".shortcuts li")).size();
    IntStream.range(0,--size).forEach(i-> removeProduct());
  }

  private void removeProduct() {
    int sizeTable = driver.findElements(By.cssSelector(".dataTable tr")).size();
    driver.findElements(By.name("remove_cart_item")).get(0).click();
    wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".dataTable tr"), --sizeTable));
  }

  private void addProductToBucket(){
    driver.findElements(By.cssSelector(".listing-wrapper li a:first-child")).get(0).click();
    int counter = Integer.parseInt(driver.findElement(By.cssSelector("#cart .quantity")).getText());
    if (driver.findElements(By.name("options[Size]")).size() > 0){
      WebElement selectElement = driver.findElement(By.name("options[Size]"));
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'))", selectElement);
    }
    driver.findElement(By.name("add_cart_product")).click();
    wait.until(ExpectedConditions.textToBe(By.cssSelector("#cart .quantity"), String.valueOf(++counter)));
    driver.navigate().back();
  }

  @AfterClass(alwaysRun = true)
  public void stop() {
    driver.quit();
  }
}
