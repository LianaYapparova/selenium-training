package org.example;

import java.util.List;
import java.util.stream.IntStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StickerTest extends BaseTest{
  @Test
  public void stickerTest() {
    driver.get("http://localhost/litecart/en/");
    List<WebElement> webElementList = driver.findElements(By.className("product"));
    IntStream.range(0, webElementList.size()).forEach(i -> {
      Assert.assertEquals(webElementList.get(i).findElements(By.className("sticker")).size(), 1);
    });
  }

}
