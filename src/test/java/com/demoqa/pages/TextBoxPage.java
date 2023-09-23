package com.demoqa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TextBoxPage {
    WebDriver driver;
    WebDriverWait wait;

    public TextBoxPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="userName")
    private WebElement fullNameField;

    @FindBy(id="userEmail")
    private WebElement  emailField;

    @FindBy(css="textarea#currentAddress")
    private WebElement currentAddressField;

    @FindBy(css="textarea#permanentAddress")
    private WebElement permanentAddressField;

    @FindBy(id="submit")
    private WebElement submitButton;

    @FindBy(id="output")
    private WebElement outputDiv;
@Step(  "4.Заполнить поля: Full Name, Email, Current Address, Permanent Address " +
        "5.Нажать на кнопку «Submit» " +
        "6.Проверить, что данные в блоке сохранены корректно")
    public void fillForm(String fullName, String email, String currentAddress, String permanentAddress) {
        fullNameField.sendKeys(fullName);
        emailField.sendKeys(email);
        currentAddressField.sendKeys(currentAddress);
        permanentAddressField.sendKeys(permanentAddress);
        submitButton.click();

        WebElement nameOutput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
        WebElement emailOutput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        WebElement currentAddressOutput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@id='currentAddress']")));
        WebElement permanentAddressOutput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@id='permanentAddress']")));

        String actualName = nameOutput.getText();
        String actualEmail = emailOutput.getText();
        String actualCurrentAddress = currentAddressOutput.getText();
        String actualPermanentAddress = permanentAddressOutput.getText();

        assertTrue(actualName.contains(fullName));
        assertTrue(actualEmail.contains(email));
        assertTrue(actualCurrentAddress.contains(currentAddress));
        assertTrue(actualPermanentAddress.contains(permanentAddress));


    }


}
