package com.gittigidiyor.step_definitions;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.gittigidiyor.pages.CartPage;
import com.gittigidiyor.pages.Dashboard;
import com.gittigidiyor.pages.ProductPage;
import com.gittigidiyor.pages.SearchPage;
import com.gittigidiyor.utilities.BrowserUtils;
import com.gittigidiyor.utilities.ConfigurationReader;
import com.gittigidiyor.utilities.Driver;
import com.gittigidiyor.utilities.Log4j;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;

public class Task_Step_Def {

    Dashboard dashboard = new Dashboard();
    ProductPage productPage = new ProductPage();
    SearchPage searchPage = new SearchPage();
    BrowserUtils browserUtils = new BrowserUtils();
    CartPage cartPage = new CartPage();

    int GlobalPageNumber=0;


    @Given("www.gittigidiyor.com sitesi açılır.")
    public void www_gittigidiyor_com_sitesi_açılır() {
        Log4j.info("Sayfa açılır : " + ConfigurationReader.get("url"));

        productPage.clearTxt();
        Driver.get().get(ConfigurationReader.get("url"));


    }

    @When("Arama kutucuğuna {string} kelimesi girilir.")
    public void aramaKutucuğunaKelimesiGirilir(String item) {
        Log4j.info("Arama kutucuğuna " + item + " kelimesi girilir.");

        dashboard.searchBox.sendKeys(item);
        BrowserUtils.waitForClickablility(dashboard.searchButton,4).click();
        BrowserUtils.waitForClickablility(dashboard.closeCookies,4).click();

    }

    @And("Arama sonuçları sayfasından {int}.sayfa açılır")
    public void aramaSonuçlarıSayfasındanSayfaAçılır(int pageNumber) {

        GlobalPageNumber = pageNumber;
        browserUtils.scrollToElement(searchPage.searchPageElement(pageNumber));
        BrowserUtils.waitFor(4);
        searchPage.searchPageElement(pageNumber).click();

    }

    @Then("{int}.sayfanın açıldığı kontrol edilir")
    public void sayfanınAçıldığıKontrolEdilir(int arg0) {

        BrowserUtils.waitFor(2);
        Assert.assertTrue(Driver.get().getCurrentUrl().endsWith(String.valueOf(GlobalPageNumber)));

    }

    @Then("Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir.")
    public void sonuca_göre_sergilenen_ürünlerden_rastgele_bir_ürün_seçilir(){
        Log4j.info("Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir");

        BrowserUtils.waitFor(2);

        searchPage.chooseRandomProduct();

    }

    @Then("Seçilen ürünün ürün bilgisi ve tutar bilgisi txt dosyasına yazılır.")
    public void seçilen_ürünün_ürün_bilgisi_ve_tutar_bilgisi_txt_dosyasına_yazılır() throws IOException {
        Log4j.info("Seçilen ürünün ürün bilgisi ve tutar bilgisi txt dosyasına yazılır");

        Driver.get().navigate().refresh();
        BrowserUtils.waitForClickablility(dashboard.closeCookies,4).click();

        JavascriptExecutor jse = (JavascriptExecutor) Driver.get();

        jse.executeScript("window.scrollBy(0,250)");
        BrowserUtils.waitForVisibility(productPage.productPrice,11);
        BrowserUtils.waitFor(2);
        productPage.writeProductInfoToTxtFile();

        System.out.println("productPage.productPrice = " + productPage.productPrice.getText());
        System.out.println("productPage.productInfo = " + productPage.productInfo.getText());

    }

    @Then("Seçilen ürün sepete eklenir.")
    public void seçilen_ürün_sepete_eklenir() {
        Log4j.info("Seçilen ürün sepete eklenir");

        BrowserUtils.waitFor(3);
        productPage.addToCart();

    }

    @Then("Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılır.")
    public void ürün_sayfasındaki_fiyat_ile_sepette_yer_alan_ürün_fiyatının_doğruluğu_karşılaştırılır() throws FileNotFoundException {
        Log4j.info("Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılır");

        BrowserUtils.waitForClickablility(dashboard.myCart,5);
        BrowserUtils.clickWithJS(dashboard.myCart);
        BrowserUtils.waitFor(2);
        cartPage.verifyProductInfo();

    }

    @And("Adet arttırılarak ürün adedinin {int} olduğu doğrulanır")
    public void adetArttırılarakÜrünAdedininOlduğuDoğrulanır(int qty) {
        Log4j.info("Adet arttırılarak ürün adedinin "+ qty + " olduğu doğrulanır");

        BrowserUtils.waitFor(1);
        cartPage.increaseQty(qty);

        int actualQty = Integer.parseInt(cartPage.dropdownElement.getAttribute("value"));
        Assert.assertEquals(actualQty,qty);

    }

    @Then("Ürün sepetten silinerek sepetin boş olduğu kontrol edilir.")
    public void ürün_sepetten_silinerek_sepetin_boş_olduğu_kontrol_edilir() {
        Log4j.info("Ürün sepetten silinerek sepetin boş olduğu kontrol edilir");

        BrowserUtils.waitFor(2);
        cartPage.delete.click();
        BrowserUtils.waitForVisibility(cartPage.cartEmptyText,5);

        Assert.assertEquals("Sepetinizde ürün bulunmamaktadır.",cartPage.cartEmptyText.getText());
        BrowserUtils.waitFor(3);

    }



}
