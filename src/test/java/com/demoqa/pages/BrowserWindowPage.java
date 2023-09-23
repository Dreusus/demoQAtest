package com.demoqa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class BrowserWindowPage {
    WebDriver driver;
    WebDriverWait wait;

    public  BrowserWindowPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[contains(text(),'Alerts, Frame & Windows')]")
    private WebElement alertsFrameWindowsButton;

    @FindBy(xpath = "//span[text()='Browser Windows']")
    private WebElement browserWindowsButton;

    @FindBy(id = "tabButton")
    private WebElement newTabButton;

    @FindBy(id = "windowButton")
    private WebElement newWindowButton;


    @Step("14.Нажать на «Alerts, Frame & Windows»")
    public void navigateToAlertsFrameWindows() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", alertsFrameWindowsButton);
        alertsFrameWindowsButton.click();

    }

    @Step("15.Нажать на «Browser Windows»")
    public void navigateToBrowserWindows() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", browserWindowsButton);
        browserWindowsButton.click();
    }


   @Step(  "16.Нажать на кнопку «New Tab»" +
           "17.Закрыть новую вкладку")
   public void handleNewTab() {
        String parentWindowHandle = driver.getWindowHandle();
        newTabButton.click();

        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }

        driver.switchTo().window(parentWindowHandle);
    }
    @Step( "18.Нажать на кнопку «New window»" +
            "19. Закрыть новое окно")
    public void handleNewWindow() {
        String parentWindowHandle = driver.getWindowHandle();
        newWindowButton.click();


        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindowHandle);
    }

}
