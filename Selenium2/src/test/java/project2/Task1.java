package project2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task1 {

    @Test
    public void test1() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
        Thread.sleep(2000);

        WebElement closePopUp=driver.findElement(By.xpath("//a[@class='at-cv-button at-cv-lightbox-yesno at-cm-no-button']"));
        closePopUp.click();
        String currentTitle=driver.getTitle();
        String expectedTitle="Selenium Easy Demo - Simple Form to Automate using Selenium";
        Assert.assertEquals(expectedTitle,currentTitle);

        WebElement enterMessage=driver.findElement(By.xpath("//input[@placeholder='Please enter your Message']"));
        enterMessage.sendKeys("First Test Case");
        WebElement showMessage=driver.findElement(By.xpath("//button[@onclick='showInput();']"));
        showMessage.click();

        String expectedMessage="First Test Case";
        WebElement showText=driver.findElement(By.xpath("//span[@id='display']"));
        String actualMessage=showText.getText();
        Assert.assertEquals(expectedMessage,actualMessage);

    }

    @Test
    public void test2(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");

        
    }

}
