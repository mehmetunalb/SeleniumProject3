package project3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.*;

public class Task1 {

    WebDriver driver;
    SoftAssert softAssert;
    Actions actions;
    Select select;

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        softAssert=new SoftAssert();
        actions=new Actions(driver);

    }

    @Test
    public void test1() throws InterruptedException {

        driver.get("https://demo.openemr.io/a/openemr/interface/login/login.php?site=default");
        WebElement admin= driver.findElement(By.xpath("//input[@type='text']"));
        actions.sendKeys(admin,"admin").perform();
        actions.sendKeys(Keys.TAB).sendKeys("pass")
                .sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).perform();

        WebElement searchBar= driver.findElement(By.id("anySearchBox"));
        actions.sendKeys(searchBar,"Belford"+Keys.ENTER).perform();
        Thread.sleep(2000);

        //By.cssSelector(".sorting_1 a")
        driver.switchTo().frame("fin");
        String actualName= driver.findElement(By.xpath("//a[contains(text(), 'Belford')]")).getText();
        actualName=actualName.substring(0,actualName.indexOf(","));
        String expectedName="Belford";
        Assert.assertEquals(actualName,expectedName);

        WebElement phoneNo= driver.findElement(By.className("sorting_2"));
        String actualPhone=phoneNo.getText();
        String expectedPhone="333-444-2222";
        Assert.assertEquals(actualPhone,expectedPhone);

        WebElement socialNo= driver.findElement(By.className("sorting_3"));
        String actualSocial=socialNo.getText();
        String expectedSocial="333222333";
        //Assert.assertEquals(actualSocial,expectedSocial);

        String externalIDHome=driver.findElement(By.className("sorting_3")).getText();
        WebElement patient=driver.findElement(By.xpath("//a[contains(text(), 'Belford')]"));
        patient.click();
        Thread.sleep(2000);
        driver.switchTo().parentFrame();
        Thread.sleep(2000);
        driver.switchTo().frame("pat");
        //WebElement externalIDFrame=driver.findElement(By.xpath("//span[@id='label_fname']"));
        //driver.switchTo().frame(externalIDFrame);

        String dashboardID=driver.findElement(By.xpath("//span[@id='label_fname']")).getText();
        //Assert.assertEquals(externalIDHome,dashboardID);

    }



    /*
    Navigate to "https://demo.openemr.io/openemr" Enter username "admin"
Enter password "pass"
Search the "Belford Phil" from the search box Click the edit button for "Messages"
Click add button
Select "Lab Result" for Type
Assign it to Stone, Fred
Type "This is for testing" to the message box Save Patient Message
Using SoftAssertions:
Validate new message displayed Validate active box is selected
Validate Type is "Lab Results" Validate Contect has today's date Validate status is "New"
Validate Updated By is equals to Billy Smith
     */
    @Test
    public void task2() throws InterruptedException {
        driver.get("https://demo.openemr.io/a/openemr/interface/login/login.php?site=default");
        WebElement admin = driver.findElement(By.id("authUser"));
        actions.sendKeys(admin, "admin").perform();
        actions.sendKeys(Keys.TAB).sendKeys("pass")
                .sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).perform();
        WebElement searchBox = driver.findElement(By.id("anySearchBox"));
        Thread.sleep(2000);
        actions.sendKeys(searchBox, "Belford" + Keys.ENTER).perform();
        Thread.sleep(2000);
        driver.switchTo().frame("fin");
        WebElement belfordPhil = driver.findElement(By.xpath("//a[contains(text(),'Belford')]"));
        actions.click(belfordPhil).perform();
        driver.switchTo().defaultContent();
        driver.switchTo().frame("pat");
        WebElement edit = driver.findElement(By.xpath("//a[@href='pnotes_full.php?form_active=1']"));
        actions.click(edit).perform();
        Thread.sleep(2000);
        WebElement addClick = driver.findElement(By.xpath("//td/following-sibling::td[a[span[.='Messages']]]/a"));
        actions.click(addClick).perform();
        Thread.sleep(2000);
//        driver.switchTo().defaultContent();
        //driver.switchTo().frame("modalframe");
        List<WebElement> clickAndSelects = driver.findElements(By.cssSelector(".section-header-dynamic .btn-primary"));
        WebElement clickAndSelect = clickAndSelects.get(2);
        System.out.println(clickAndSelect.getText());
        clickAndSelect.click();
        WebElement add= driver.findElement(By.xpath("//*[@href='pnotes_full_add.php?docid=0&orderid=0']"));
        add.click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame("modalframe");
        WebElement selectOption= driver.findElement(By.xpath("//*[@id='form_note_type']"));
        select = new Select(selectOption);
        select.selectByVisibleText("Lab Results");
        Thread.sleep(2000);
        WebElement assignPerson = driver.findElement(By.xpath("//select[@name='assigned_to']"));
        select = new Select(assignPerson);
        select.selectByIndex(4);
        Thread.sleep(2000);
        WebElement message = driver.findElement(By.xpath("//*[@id='note']"));
        actions.click(message).perform();
        message.sendKeys("This is for testing" + Keys.ENTER);
        WebElement save = driver.findElement(By.id("newnote"));
        actions.click(save).perform();
        driver.switchTo().defaultContent();
        driver.switchTo().frame("pat");
        /*WebElement newDisplay= driver.findElement(By.xpath("//td[@class='notecell' and @id='7'][1]"));
        String expectedMessage= newDisplay.getText();
        String actualMessage= "New";
        softAssert.assertEquals(expectedMessage,actualMessage);*/
    }

    @Test
    public void test3() throws InterruptedException {
        Actions actions=new Actions(driver);
        driver.get("https://demo.openemr.io/a/openemr/interface/login/login.php?site=default");
        WebElement admin= driver.findElement(By.xpath("//input[@type='text']"));
        actions.sendKeys(admin,"admin").perform();
        actions.sendKeys(Keys.TAB).sendKeys("pass")
                .sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).perform();
        WebElement searchBar= driver.findElement(By.id("anySearchBox"));
        String expectedName="Belford";
        actions.sendKeys(searchBar,expectedName+Keys.ENTER).perform();
        Thread.sleep(2000);
        //By.cssSelector(".sorting_1 a")
        driver.manage().window().maximize();
        driver.switchTo().frame("fin");
        WebElement patient = driver.findElement(By.xpath("//td/a[.='Belford, Phil']"));
        patient.click();
        Thread.sleep(2000);
        driver.switchTo().parentFrame();
        Thread.sleep(2000);
        driver.switchTo().frame("pat");
        WebElement expandAmendments= driver.findElement(By.xpath("//span[text()='Amendments']"));
        String amendmentsExpand=expandAmendments.getText();
        String actualTextExpand=amendmentsExpand.trim()+" "+amendmentsExpand.trim();
        softAssert.assertTrue(actualTextExpand.equals("Amendments expand"));
        expandAmendments.click();
        String actualAmendmentsCollapse=expandAmendments.getText();
        Thread.sleep(2500);
        WebElement collapseAmendments= driver.findElement(By.xpath("//span[.='collapse'][preceding-sibling::span[.='Amendments']]"));
        String amendmentsCollapse=collapseAmendments.getText();
        String actualText= actualAmendmentsCollapse.trim()+" "+amendmentsCollapse.trim();
        Assert.assertTrue(actualText.equals("Amendments collapse"));
        WebElement edit=driver.findElement(By.cssSelector("#container_div > div.main > div:nth-child(1) > div.col-md-8 > div > section:nth-child(8) > div.section-header-dynamic > table > tbody > tr > td:nth-child(1) > a > span"));
        edit.click();
        WebElement alert=driver.findElement(By.className("text-danger"));
        String color= alert.getCssValue("color");
        softAssert.assertTrue(alert.getText().trim().equals("                        No amendment requests available                    "+"".trim()));
        softAssert.assertTrue(color.equals("red"));
        driver.switchTo().defaultContent();
        WebElement close=driver.findElement(By.xpath("//button[text()='×']"));
        close.click();
    }


    @Test
    public void test4() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        driver.get("https://demo.openemr.io/openemr/interface/login/login.php?site=default");
        WebElement admin = driver.findElement(By.xpath("//input[@type='text']"));
        actions.sendKeys(admin, "admin").perform();
        actions.sendKeys(Keys.TAB).sendKeys("pass")
                .sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).perform();
        WebElement searchBar = driver.findElement(By.id("anySearchBox"));
        actions.sendKeys(searchBar, "Belford" + Keys.ENTER).perform();
        Thread.sleep(2000);
        driver.switchTo().frame("fin");
        WebElement belford = driver.findElement(By.xpath("//a[contains(text(), 'Belford')]"));
        belford.click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame("pat");

        Map<String,String> patients=new LinkedHashMap<>();
        List<WebElement> patientKeys= driver.findElements(By.xpath("//div[@class='font-weight-bold d-inline-block']"));
        List<WebElement> patientValues= driver.findElements(By.xpath("//div[@class='font-weight-bold d-inline-block']/following-sibling::div"));
        int i=0;
        for (WebElement element:patientKeys) {
            patients.put(element.getText(),patientValues.get(i).getText());
            i++;
        }


        WebElement link = driver.findElement(By.xpath("//a[contains(text(),'Click here to view and graph all vitals.')]"));
        link.click();


    }


}
