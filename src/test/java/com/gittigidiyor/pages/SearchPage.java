package com.gittigidiyor.pages;

import com.gittigidiyor.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class SearchPage extends BasePage{

    @FindBy(xpath="//div/ul/li//img")
    public List<WebElement> productList;

    public void chooseRandomProduct (){

        Random rd = new Random();
        int randomProduct = rd.nextInt(productList.size());
        productList.get(randomProduct).click();
    }

    public WebElement searchPageElement(int page){
        WebElement element = Driver.get().findElement(By.xpath("//li[@data-testid]["+page+"]//span"));
        return element;
    }

}
