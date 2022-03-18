package com.gittigidiyor.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class Dashboard extends BasePage{

    @FindBy(xpath = "(//input[@name='k'])[1]")
    public WebElement searchBox;

    @FindBy(xpath = "(//button/span[text()='BUL'])[1]")
    public WebElement searchButton;

    @FindBy(xpath = "//span[.='Kapat']")
    public WebElement closeCookies;

    @FindBy(xpath = "//span[.='Sepetim']")
    public WebElement myCart;

}
