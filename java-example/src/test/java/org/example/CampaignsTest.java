package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.Colors;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CampaignsTest extends BaseTest {

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
    Assert.assertTrue(checkGrey(colorPriceProduct));
    Assert.assertTrue(lineThroughPriceProduct.contains("line-through"));
    Assert.assertTrue(checkRed(colorCampaignPriceProduct));
    Assert.assertTrue(Integer.parseInt(stylePriceProduct) >= 700);
    Assert.assertTrue(Float.parseFloat(sizePriceProduct.replaceFirst("px", ""))
        < Float.parseFloat(sizeCampaignPriceProduct.replaceFirst("px", "")));

    Assert.assertTrue(checkGrey(colorPriceProductDuck));
    Assert.assertTrue(lineThroughPriceProductDuck.contains("line-through"));
    Assert.assertTrue(checkRed(colorCampaignPriceProductDuck));
    Assert.assertTrue(Integer.parseInt(stylePriceProductDuck) >= 700);
    Assert.assertTrue(Float.parseFloat(sizePriceProductDuck.replaceFirst("px", ""))
        < Float.parseFloat(sizeCampaignPriceProductDuck.replaceFirst("px", "")));
  }

  private boolean checkRed(String color) {
    int blue = Color.fromString(color).getColor().getBlue();
    int green = Color.fromString(color).getColor().getGreen();
    return blue == 0 && green == 0;
  }

  private boolean checkGrey(String color) {
    int red = Color.fromString(color).getColor().getRed();
    int blue = Color.fromString(color).getColor().getBlue();
    int green = Color.fromString(color).getColor().getGreen();
    return red == blue && blue == green;
  }

}
