package com.gittigidiyor.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CartPage extends BasePage{

    @FindBy(xpath = "//select[@class='amount']")
    public WebElement dropdownElement;

    @FindBy(xpath = "//a[@title='Sil']")
    public WebElement delete;

    @FindBy(xpath = "//*[@id='empty-cart-container']//h2")
    public WebElement cartEmptyText;

    @FindBy(xpath="//div[@class='total-price']//strong")
    public WebElement productPrice;


    public void increaseQty(int qty){

        Select dropdownAmount = new Select(dropdownElement);
        dropdownAmount.selectByIndex(qty-1);
    }

    public void verifyProductInfo() throws FileNotFoundException {

        File file=new File("src/test/resources/textfile.txt");
        Scanner scanner=new Scanner(file);
        while (scanner.hasNextLine()){
            String price = scanner.nextLine();
            if (price.contains("Price")){
                Assert.assertEquals(price.substring(price.indexOf(":")+1).trim(),productPrice.getText());
            }

        }

        scanner.close();

    }

}
