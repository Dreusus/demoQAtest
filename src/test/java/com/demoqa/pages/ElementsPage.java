package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ElementsPage {

      WebDriver driver;
      public ElementsPage(WebDriver driver) {
         this.driver = driver;
         PageFactory.initElements(driver,this);
    }
        @FindBy(xpath = "//span[text()='Text Box']")
        private WebElement textBoxButton;

      public void clickOnTextBox() {
          textBoxButton.click();
      }

}