package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.epam.healenium.SelfHealingDriver;
import java.time.Duration;

public class GoogleTest {

  SelfHealingDriver driver;
  WebDriverWait wait;

  @BeforeMethod
  public void setup() {
    WebDriver delegate = new ChromeDriver();
    driver = SelfHealingDriver.create(delegate);
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  @Test
  public void formularioCompleto() throws InterruptedException {

    driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
    System.out.println("✅ Página abierta correctamente");

    //  PASO 1:  my-text-id (correcto)
    //  PASO 2:  my-text-MODIFICADO
    WebElement txtInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("my-text-id")));
    txtInput.sendKeys("Hola Grupo 371");
    System.out.println("✅ Text input completado");

    WebElement txtPassword = wait.until(ExpectedConditions.elementToBeClickable(By.name("my-password")));
    txtPassword.sendKeys("123456");
    System.out.println("✅ Password completado");

    WebElement txtArea = wait.until(ExpectedConditions.elementToBeClickable(By.name("my-textarea")));
    txtArea.sendKeys("Texto de prueba");
    System.out.println("✅ Textarea completado");

    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.name("my-select")));
    Select select = new Select(dropdown);
    select.selectByVisibleText("Two");
    System.out.println("✅ Dropdown seleccionado");

    WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("my-check-1")));
    if (!checkbox.isSelected()) checkbox.click();
    System.out.println("✅ Checkbox marcado");

    WebElement radio = wait.until(ExpectedConditions.elementToBeClickable(By.id("my-radio-2")));
    radio.click();
    System.out.println("✅ Radio button seleccionado");

    WebElement datePicker = wait.until(ExpectedConditions.elementToBeClickable(By.name("my-date")));
    datePicker.sendKeys("03/28/2026");
    System.out.println("✅ Fecha ingresada");

    WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("button[type='submit']")));
    submitBtn.click();
    System.out.println("✅ Submit presionado");

    // ✅ Sin validación del mensaje — solo espera y termina
    Thread.sleep(3000);
    System.out.println("✅ Test completado exitosamente");
  }

  @AfterTest
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}