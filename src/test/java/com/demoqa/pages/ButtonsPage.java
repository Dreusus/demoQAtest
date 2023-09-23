package com.demoqa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;


public class ButtonsPage {
    WebDriver driver;
    WebDriverWait wait;

   public ButtonsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//span[text()='Buttons']")
    private WebElement buttonsButton;

   @FindBy(id="doubleClickBtn")
    private WebElement doubleClickButton;

   @FindBy(id="rightClickBtn")
    private WebElement rigthClickButton;

   @FindBy(xpath = "//button[text()='Click Me']")
    private WebElement clickMeButton;

   @FindBy(id="doubleClickMessage")
   private WebElement doubleClickMessage;

   @FindBy(id="rightClickMessage")
   private WebElement rightClickMessage;

   @FindBy(id="dynamicClickMessage")
   private WebElement dynamicClickMessage;


   @Step("7.Нажать на «Buttons»")
    public void navigateToButtons() {
       buttonsButton.click();
   }

    @Step(  "8.Нажать на кнопку «Click me»" +
            "9.Проверить, что появился текст «You have done a dynamic click»")
    public void performClickMe() {
        clickMeButton.click();
        wait.until(ExpectedConditions.visibilityOf(dynamicClickMessage));
        String expectedText = "You have done a dynamic click";
        String actualText = dynamicClickMessage.getText();
        assertEquals(actualText,expectedText);
    }

    @Step(  "10.Нажать на кнопку «Right Click me»" +
            "11.Проверить, что появился текст «You have done a right click»")
    public void performRightClick() {
        Actions actions = new Actions(driver);
        actions.contextClick(rigthClickButton).perform();
        wait.until(ExpectedConditions.visibilityOf(rightClickMessage));
        String expectedText = "You have done a right click";
        String actualText = rightClickMessage.getText();
        assertEquals(actualText,expectedText);

    }
@Step(  "12.Нажать на кнопку «Double Click me»" +
        "13.Проверить, что появился текст «You have done a double click»")
   public void performDoubleClick() {
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleClickButton).perform();
        wait.until(ExpectedConditions.visibilityOf(doubleClickMessage));
        String expectedText = "You have done a double click";
        String actualText = doubleClickMessage.getText();
        assertEquals(actualText,expectedText);
    }



}
