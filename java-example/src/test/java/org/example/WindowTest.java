package org.example;

import java.util.Set;
import java.util.stream.IntStream;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowTest extends BaseTest {
  @BeforeMethod
  public void login() {
    driver.get("http://localhost/litecart/admin/");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }

  @Test
  public void windowTest() {
    driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    driver.findElement(By.cssSelector("a.button")).click();
    int size = driver.findElements(By.cssSelector("form table a[target='_blank']")).size();
    String currentWindow = driver.getWindowHandle();
    IntStream.range(0, size).forEach(i -> toNewWindow(currentWindow, i));

  }

  private void toNewWindow(String currentWindow, int i) {
    Set<String> existingWindows = driver.getWindowHandles();
    driver.findElements(By.cssSelector("form table a[target='_blank']")).get(i).click();
    String newWindow = wait.until(anyWindowOtherThan(existingWindows));
    driver.switchTo().window(newWindow);
    driver.close();
    driver.switchTo().window(currentWindow);
  }


  public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
    return webDriver -> {
      Set<String> handels = driver.getWindowHandles();
      handels.removeAll(oldWindows);
      return handels.size() > 0 ? handels.iterator().next() : null;
    };
  }

  @AfterMethod
  public void logout() {
    driver.findElement(By.cssSelector("a[title=Logout]")).click();
  }
}
