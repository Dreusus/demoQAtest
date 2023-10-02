package com.demoqa.tests;

import com.demoqa.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import static org.junit.Assert.assertEquals;

@Epic("Demo QA Tests")
public class DemoQATest {

    private WebDriver driver;


@Before
    public void setUp() {
    ChromeOptions options = new ChromeOptions();
    options.setPageLoadStrategy(PageLoadStrategy.EAGER); //скрипт gpt.js не подргужается( на это уходит 41.14 сек),DOM дерево подгружается за .0268с
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver(options);
}

@Test
@Feature("Demo QA Test Scenario")
public void testScenario() {

    openDemoQaCom();
    handleHomePage();
    handleElementsPage();
    handleTextBoxPage();
    handleButtonsPage();
    handleBrowserWindowPage();
    handleAlertsPage();
}

   @Step("1.Перейти на demoqa.com")
    public void openDemoQaCom() {
    driver.get("https://demoqa.com/");
    }
    @Step("2.Нажать на «Elements»")
    public void handleHomePage() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnElementsSpan();
        assertEquals("https://demoqa.com/elements", driver.getCurrentUrl());
    }

    @Step("3.Нажать на «Text box»")
    public void handleElementsPage() {
        ElementsPage elementsPage = new ElementsPage(driver);
        elementsPage.clickOnTextBox();
        assertEquals("https://demoqa.com/text-box", driver.getCurrentUrl());
    }


    public void handleTextBoxPage() {
        TextBoxPage textBoxPage = new TextBoxPage(driver);
        textBoxPage.fillForm("QA Auto Polyakov Andrey", "pitak@dreusus.ru", "Earth", "Saint-Petersburg");
    }


    public void handleButtonsPage() {
        ButtonsPage buttonsPage = new ButtonsPage(driver);
        buttonsPage.navigateToButtons();
        buttonsPage.performDoubleClick();
        buttonsPage.performRightClick();
        buttonsPage.performClickMe();
    }


    public void handleBrowserWindowPage() {
    BrowserWindowPage browserWindowPage = new BrowserWindowPage(driver);
    browserWindowPage.navigateToAlertsFrameWindows();
    browserWindowPage.navigateToBrowserWindows();
    assertEquals("https://demoqa.com/browser-windows", driver.getCurrentUrl());
    browserWindowPage.handleNewTab();
    browserWindowPage.handleNewWindow();
    }


    public  void  handleAlertsPage() {
    AlertsPage alertsPage = new AlertsPage(driver);
    alertsPage.navigateToAlerts();
    alertsPage.handleAlertButton();
    alertsPage.handleTimerAlertButton();
    alertsPage.handleConfirmButton();
    alertsPage.handlePromtButton();
    }

@After
    public void tearDown() {
    if (driver !=null) {
        driver.quit();
    }
}
}
