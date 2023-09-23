package com.demoqa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class AlertsPage {
    WebDriver driver;
    WebDriverWait wait;

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Alerts']")
    private WebElement alertsButton;

    @FindBy(id = "alertButton")
    private WebElement clickMeAlertButton;

    @FindBy(id="timerAlertButton")
    private WebElement clickMeTimerAlertButton;

    @FindBy(id="confirmButton")
    private WebElement clickMeConfirmButton;

    @FindBy(id="promtButton")
    private WebElement clickMePromtButton;

    @FindBy(id="confirmResult")
    private WebElement confirmResult;

    @FindBy(id="promptResult")
    private WebElement promptResult;
   @Step("20.Нажать на «Alerts»")
   public void navigateToAlerts() {
        alertsButton.click();
    }
   @Step(  "21.Нажать на кнопку «Click me»  рядом с Click Button to see alert" +
           "22.Закрыть уведомление")
   public void handleAlertButton() {
        clickMeAlertButton.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    @Step(  "23.Нажать на кнопку «Click me»  рядом с On button click, alert will appear after 5 seconds" +
            "24.Закрыть уведомление")
    public void handleTimerAlertButton() {
        clickMeTimerAlertButton.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

   @Step(  "25.Нажать на кнопку «Click me»  рядом с On button click, confirm box will appear" +
           "26.Нажать на кнопку «Да» в уведомление" +
           "27.Проверить, что появился текст You selected Ok")
   public void handleConfirmButton() {
        clickMeConfirmButton.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        WebElement confirmResultElement = wait.until(ExpectedConditions.visibilityOf(confirmResult));
        String actualResultText = confirmResultElement.getText();

        assertEquals("You selected Ok", actualResultText);
    }

    @Step(  "28.Нажать на кнопку «Click me»  рядом с On button click, prompt box will appear" +
            "29.Заполнить поле в уведомление данными: Test name" +
            "30.Проверить, что появился текст You entered Test name")
    public void handlePromtButton() {
        clickMePromtButton.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys("Test name");
        alert.accept();

        WebElement promtResultElement = wait.until(ExpectedConditions.visibilityOf(promptResult));

        String actualResultText = promtResultElement.getText();
        assertEquals("You entered Test name", actualResultText);

    }

}
