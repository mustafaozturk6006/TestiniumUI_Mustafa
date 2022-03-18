package com.gittigidiyor.pages;

import com.gittigidiyor.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ProductPage extends BasePage{

    File file=new File("src/test/resources/textfile.txt");

    @FindBy(xpath="//button[@id='add-to-basket']")
    public WebElement addToCartBtn;

    @FindBy(xpath="//h1[@id='sp-title']")
    public WebElement productInfo;

    @FindBy(xpath="//div[@id='spp-price-grayContainer']//div[@id='sp-price-lowPrice']")
    public WebElement productPrice;

    @FindBy(xpath = "//div[@class='wis-cnt176970 wis-cnt-srp-176970']/div/img")
    public WebElement banner;

    public void writeProductInfoToTxtFile(){

            try {
                file.createNewFile();
                FileWriter writer = new FileWriter( file.getAbsolutePath());
                writer.write("Product: " + productInfo.getText() + "\nPrice: "+productPrice.getText());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public void clearTxt(){


        file.delete();


    }
    public void addToCart(){
        BrowserUtils.waitFor(2);
        BrowserUtils.clickWithJS(addToCartBtn);
        BrowserUtils.waitFor(2);
    }


}
