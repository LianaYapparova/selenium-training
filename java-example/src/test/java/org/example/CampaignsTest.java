package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CampaignsTest {
  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeClass
  public void start() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, Duration.ofMillis(100));
  }

  @BeforeMethod
  public void login() {
    driver.get(" http://localhost/litecart/admin/");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }

  @Test
  public void productTest() {
    driver.get("http://localhost/litecart/en/");
    WebElement webElement = driver.findElement(By.id("box-campaigns")).findElements(By.cssSelector("li")).get(0);
    String nameProduct = webElement.findElement(By.className("name")).getText();
    WebElement regularPrice = webElement.findElement(By.className("regular-price"));
    String regularPriceProduct = regularPrice.getText();
    String colorPriceProduct = regularPrice.getCssValue("color");
    String lineThroughPriceProduct = regularPrice.getCssValue("text-decoration");
    String sizePriceProduct = regularPrice.getCssValue("font-size");

    WebElement campaignPrice = webElement.findElement(By.className("campaign-price"));
    String campaignPriceProduct = campaignPrice.getText();
    String colorCampaignPriceProduct = campaignPrice.getCssValue("color");
    String stylePriceProduct = campaignPrice.getCssValue("font-weight");
    String sizeCampaignPriceProduct = campaignPrice.getCssValue("font-size");

    webElement.click();

    String nameProductDuck = driver.findElement(By.cssSelector("h1")).getText();
    WebElement regularPriceProductDuck = driver.findElement(By.cssSelector(".regular-price"));
    WebElement campaignPriceProductDuck = driver.findElement(By.cssSelector(".campaign-price"));

    String regularPriceProductDuckText = regularPriceProductDuck.getText();
    String colorPriceProductDuck = regularPriceProductDuck.getCssValue("color");
    String lineThroughPriceProductDuck = regularPriceProductDuck.getCssValue("text-decoration");
    String sizePriceProductDuck = regularPriceProductDuck.getCssValue("font-size");

    String campaignPriceProductDuckText = campaignPriceProductDuck.getText();
    String colorCampaignPriceProductDuck = campaignPriceProductDuck.getCssValue("color");
    String stylePriceProductDuck = campaignPriceProductDuck.getCssValue("font-weight");
    String sizeCampaignPriceProductDuck = campaignPriceProductDuck.getCssValue("font-size");

    Assert.assertEquals(nameProduct, nameProductDuck);
    Assert.assertEquals(regularPriceProduct, regularPriceProductDuckText);
    Assert.assertEquals(campaignPriceProduct, campaignPriceProductDuckText);
    Assert.assertEquals(colorPriceProduct, "rgba(119, 119, 119, 1)");
    Assert.assertTrue(lineThroughPriceProduct.contains("line-through"));
    Assert.assertEquals(colorCampaignPriceProduct, "rgba(204, 0, 0, 1)");
    Assert.assertEquals(stylePriceProduct, "700");
    Assert.assertTrue(Float.parseFloat(sizePriceProduct.replaceFirst("px", ""))
        < Float.parseFloat(sizeCampaignPriceProduct.replaceFirst("px", "")));

    Assert.assertEquals(colorPriceProductDuck, "rgba(102, 102, 102, 1)");
    Assert.assertTrue(lineThroughPriceProductDuck.contains("line-through"));
    Assert.assertEquals(colorCampaignPriceProductDuck, "rgba(204, 0, 0, 1)");
    Assert.assertEquals(stylePriceProductDuck, "700");
    Assert.assertTrue(Float.parseFloat(sizePriceProductDuck.replaceFirst("px", ""))
        < Float.parseFloat(sizeCampaignPriceProductDuck.replaceFirst("px", "")));
  }

  @AfterClass(alwaysRun = true)
  public void stop() {
    driver.quit();
  }
}
