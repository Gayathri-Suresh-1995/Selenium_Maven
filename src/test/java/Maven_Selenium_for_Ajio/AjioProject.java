package Maven_Selenium_for_Ajio;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class AjioProject {

    static WebDriver driver;
    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;

    @BeforeSuite
    public void setUp(){
        htmlReporter = new ExtentHtmlReporter("G:\\Selenium_Maven\\src\\test\\java\\Maven_Selenium_for_Ajio\\extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @AfterSuite
    public void tearDown(){
          extent.flush();
    }

    @BeforeMethod
    public void launchAjio() throws Exception {
        // Launching website Ajio.
        System.out.println("Launching Ajio");
        System.setProperty("webdriver.chrome.driver", "G:\\Selenium Jars and Drivers\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.ajio.com/");
        driver.manage().window().maximize();
        Thread.sleep(1000);
    }

   @AfterMethod
    public void closeAjio(){
        System.out.println("closing Ajio");
        driver.close();
    }

    @Test(priority=1,enabled = true)
    public void searchJacket() throws Exception {
        //Search for Jacket
        ExtentTest test = extent.createTest("searchJacket", "Searching for a Product ");
        System.out.println("Search for Jacket");
        driver.findElement(By.name("searchVal")).sendKeys("Jackets" + Keys.ENTER);
        test.pass("Jacket Searched");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,50)","");
        takeScreenshot("Ajio_search_Jacket");
        test.log(Status.INFO, "This step shows usage of log(status, details)");
        test.info("This step shows usage of info(details)");
        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        test.addScreenCaptureFromPath("screenshot.png");
        Thread.sleep(2000);
    }

    @Test (priority=2,enabled = true)
    public void searchSmartPhone() throws Exception {
        //Search for SmartPhone
        ExtentTest test = extent.createTest("searchSmartPhone", "Searching for a Non available Product ");
        System.out.println("Search for SmartPhone");
        driver.findElement(By.name("searchVal")).sendKeys("Smart Phone" + Keys.ENTER);
        test.pass("Smart Phone Searched");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,50)","");
        takeScreenshot("Ajio_search_SmartPhone");
        test.log(Status.INFO, "This step shows usage of log(status, details)");
        test.info("This step shows usage of info(details)");
        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        test.addScreenCaptureFromPath("screenshot.png");
        Thread.sleep(2000);
    }


    @Test (priority=3, enabled = true)
    public void addToCart() throws Exception {
        //Search for Jacket
        ExtentTest test = extent.createTest("addToCart", "Searching for a Product, select size and add to cart");
        System.out.println("Add to cart");
        driver.findElement(By.name("searchVal")).sendKeys("Jackets" + Keys.ENTER);
        String parentHandle = driver.getWindowHandle();
        System.out.println("Parent Window Title" + parentHandle);
        driver.findElement(By.xpath("//*[@id=\"products\"]/div[3]/div[1]/div/div[3]/a/div/div[2]")).click();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles){
            if(!handle.equals(parentHandle)){
                driver.switchTo().window(handle);
                System.out.println("Clicking size");
                driver.findElement(By.xpath("//*[@id=\"appContainer\"]/div[2]/div/div/div[2]/div/div[3]/div/div[6]/div[3]/div")).click();
                Thread.sleep(1000);
                System.out.println(driver.findElement(By.xpath("//*[@id=\"appContainer\"]/div[2]/div/div/div[2]/div/div[3]/div/div[6]/div[3]/div")).isSelected());
                driver.findElement(By.xpath("//*[@id=\"appContainer\"]/div[2]/div/div/div[2]/div/div[3]/div/div[9]/div[1]/div[1]/div/span[1]")).click();
                Thread.sleep(2000);
                WebElement element = driver.findElement(By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[2]/div[2]/a/div"));
                Actions action = new Actions(driver);
                action.moveToElement(element).perform();
                Thread.sleep(2000);
                takeScreenshot("Ajio_add_to_cart");
                test.log(Status.INFO, "This step shows usage of log(status, details)");
                test.info("This step shows usage of info(details)");
                test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
                test.addScreenCaptureFromPath("screenshot.png");
                driver.close();
            }
            driver.switchTo().window(parentHandle);
        }
    }

    @Test (priority=4, enabled = true)
    public void verifyMenu() throws Exception {
        //Search Menu
        ExtentTest test = extent.createTest("verifyMenu", "Menu Displays Men Women And Kids");
        System.out.println("Verify Menu");
        takeScreenshot("Ajio_Verify_Menu");

        test.log(Status.INFO, "This step shows usage of log(status, details)");
        test.info("This step shows usage of info(details)");
        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        test.addScreenCaptureFromPath("screenshot.png");

        // Thread.sleep(2000);
    }


   @Test (priority=5, enabled = true)
    public void SortByLowToHigh() throws Exception {
        //Search for Shoes and sort price from low to High
        ExtentTest test = extent.createTest("SortByLowToHigh", "Sort Items from Low to High Price");
        driver.findElement(By.name("searchVal")).sendKeys("Shoes" + Keys.ENTER);
        WebElement element = driver.findElement(By.xpath("//*[@id=\"products\"]/div[2]/div/div[3]/div/select"));
        Select sel = new Select (element);
        sel.selectByVisibleText("Price (lowest first)");
        Thread.sleep(2000);
        takeScreenshot("Ajio_Low_to_High");
        test.log(Status.INFO, "This step shows usage of log(status, details)");
        test.info("This step shows usage of info(details)");
        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        test.addScreenCaptureFromPath("screenshot.png");
    }

   @Test (priority=6, enabled = true)
    public void SortByHighToLow() throws Exception {
        //Search for Shoes and sort price from High to low
        ExtentTest test = extent.createTest("SortByHighToLow", "Sort Items from High to low Price");
        driver.findElement(By.name("searchVal")).sendKeys("Shoes" + Keys.ENTER);
        WebElement element = driver.findElement(By.xpath("//*[@id=\"products\"]/div[2]/div/div[3]/div/select"));
        Select sel = new Select (element);
        sel.selectByVisibleText("Price (highest first)");
        Thread.sleep(2000);
        takeScreenshot("Ajio_High_To_Low");
        test.log(Status.INFO, "This step shows usage of log(status, details)");
        test.info("This step shows usage of info(details)");
        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        test.addScreenCaptureFromPath("screenshot.png");

    }

   @Test (priority=7, enabled = true)
    public void verifyMenuItemsforMen() throws Exception {
        ExtentTest test = extent.createTest("verifyMenuItemsforMen", "Verify Menu Items displayed in Men");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"appContainer\"]/div[1]/div/header/div[3]/div[1]/ul/li[1]/a"));
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        Thread.sleep(2000);
        takeScreenshot("Ajio_verify_menu_for_men");
        test.log(Status.INFO, "This step shows usage of log(status, details)");
        test.info("This step shows usage of info(details)");
        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        test.addScreenCaptureFromPath("screenshot.png");


    }

   @Test (priority=8,enabled = true)
    public void verifyFooter() throws Exception {
        ExtentTest test = extent.createTest("verifyFooter", "Verify if footer displays the text Who We Are, Join Our Team,Terms & Conditions");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        Thread.sleep(2000);
        takeScreenshot("Ajio_verify_Footer");
        test.log(Status.INFO, "This step shows usage of log(status, details)");
        test.info("This step shows usage of info(details)");
        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        test.addScreenCaptureFromPath("screenshot.png");

    }

    @Test (priority=9, enabled = true)
    public void verifyReturnPolicy() throws Exception {
        //Search for Jacket
        ExtentTest test = extent.createTest("verifyReturnPolicy", "Verify if return policy is being displayed");
        System.out.println("Verify Return Policy");
        driver.findElement(By.name("searchVal")).sendKeys("Jackets" + Keys.ENTER);
        String parentHandle = driver.getWindowHandle();
        System.out.println("Parent Window Title" + parentHandle);
        driver.findElement(By.xpath("//*[@id=\"products\"]/div[3]/div[1]/div/div[3]/a/div/div[2]")).click();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,500)","");
                String className = driver.findElement(By.xpath("//*[@id=\"product-details-tab-tab-2\"]")).getAttribute("class");
                if(className.contains("nav-item nav-link active")){
                    System.out.println("Return Expanded");
                    takeScreenshot("Ajio_Return_Policy");
                    test.log(Status.INFO, "This step shows usage of log(status, details)");
                    test.info("This step shows usage of info(details)");
                    test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
                    test.addScreenCaptureFromPath("screenshot.png");
                    driver.close();
                }
           }
            driver.switchTo().window(parentHandle);
        }
    }

    @Test (priority=11, enabled = true)
    public void verifyFAQ() throws Exception {
        //click customer care link

        driver.findElement(By.linkText("Customer Care")).click();
        String currentURL = driver.getCurrentUrl();
        System.out.println(currentURL);
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.scrollBy(0,300)", "");
        Thread.sleep(2000);
        ajioShippingTab();
        Thread.sleep(2000);
        ajioCancellationTab();

    }

    public void ajioShippingTab() throws Exception {
        ExtentTest test = extent.createTest("verifyShippingFAQ", "Verify Shipping FAQ is displayed");
        String className = driver.findElement(By.xpath("//*[@id=\"left-tabs-example\"]/div[1]/div/div[1]")).getAttribute("class");
        String expectedClassName = "nav-item";
        Assert.assertEquals(className,expectedClassName);
        takeScreenshot("Ajio_Shipping_FAQ");
        test.log(Status.INFO, "This step shows usage of log(status, details)");
        test.info("This step shows usage of info(details)");
        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        test.addScreenCaptureFromPath("screenshot.png");

    }

    public void ajioCancellationTab() throws Exception {
        ExtentTest test = extent.createTest("verifyCancellationFAQ", "Verify Cancellation FAQ is displayed");
        driver.findElement(By.xpath("//*[@id=\"left-tabs-example-tab-1\"]")).click();
        takeScreenshot("Ajio_Cancellation_FAQ");
        test.log(Status.INFO, "This step shows usage of log(status, details)");
        test.info("This step shows usage of info(details)");
        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        test.addScreenCaptureFromPath("screenshot.png");
    }


    @Test (priority=12, enabled = true)
    public void filterItems() throws Exception {
        //Search for Jackets
        System.out.println("Search for Jackets");
        driver.findElement(By.name("searchVal")).sendKeys("Jackets" + Keys.ENTER);
        takeScreenshot("Ajio_All_Jackets");
        System.out.println("Gender Expanded");
        String className = driver.findElement(By.xpath("//*[@id=\"facets\"]/div[2]/ul/li[1]/div/div[1]")).getAttribute("class");
        String expectedClassName = "facet-head facet-xpndicon";
        Assert.assertEquals(className,expectedClassName);
        filterMen();
        filterWomen();
    }

    public void filterMen() throws Exception {
        ExtentTest test = extent.createTest("filterMen", "Filter as per Gender");
        driver.findElement(By.xpath("//*[@id=\"facets\"]/div[2]/ul/li[1]/div/div[2]/ul/li[1]/div/div/label")).click();
        Thread.sleep(3000);
        //boolean x = driver.findElement(By.xpath("//*[@id=\"facets\"]/div[2]/ul/li[1]/div/div[2]/ul/li[1]/div/div/label")).isSelected();
        System.out.println(driver.findElement(By.xpath("//*[@id=\"facets\"]/div[2]/ul/li[1]/div/div[2]/ul/li[1]/div/div/label")).isSelected());
        takeScreenshot("Ajio_Jacket_Men");
        test.log(Status.INFO, "This step shows usage of log(status, details)");
        test.info("This step shows usage of info(details)");
        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        test.addScreenCaptureFromPath("screenshot.png");
        driver.manage().timeouts().pageLoadTimeout(50,TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"facets\"]/div[2]/ul/li[1]/div/div[2]/ul/li[1]/div/div/label")).click();
        Thread.sleep(3000);
    }

    public void filterWomen() throws Exception {
        ExtentTest test = extent.createTest("filterWomen", "Filter as per Gender");
        driver.findElement(By.xpath("//*[@id=\"facets\"]/div[2]/ul/li[1]/div/div[2]/ul/li[2]/div/div/label")).click();
        Thread.sleep(3000);
        System.out.println(driver.findElement(By.xpath("//*[@id=\"facets\"]/div[2]/ul/li[1]/div/div[2]/ul/li[2]/div/div/label")).isSelected());
        takeScreenshot("Ajio_Jacket_Women");
        test.log(Status.INFO, "This step shows usage of log(status, details)");
        test.info("This step shows usage of info(details)");
        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        test.addScreenCaptureFromPath("screenshot.png");
        driver.manage().timeouts().pageLoadTimeout(50,TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"facets\"]/div[2]/ul/li[1]/div/div[2]/ul/li[1]/div/div/label")).click();
        Thread.sleep(3000);
    }


   @Test (priority=13, enabled = true)
    public void searchPumaBrand() throws Exception {
        //Search for Puma Brand
        ExtentTest test = extent.createTest("searchPumaBrand", "Search for Puma Brand");
        System.out.println("Search for Puma Brand");
        driver.findElement(By.name("searchVal")).sendKeys("PUMA" + Keys.ENTER);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,10)","");
        takeScreenshot("Ajio_search_PUMA");
        test.log(Status.INFO, "This step shows usage of log(status, details)");
        test.info("This step shows usage of info(details)");
        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        test.addScreenCaptureFromPath("screenshot.png");
        Thread.sleep(2000);
    }

    @Test (priority=14, enabled = true)
    public void searchAdidasBrand() throws Exception {
        //Search for Adidas Brand
        ExtentTest test = extent.createTest("searchAdidasBrand", "Search for Adidas Branded items less than Rs.500");
        System.out.println("Search for Adidas Brand");
        driver.findElement(By.name("searchVal")).sendKeys("Adidas" + Keys.ENTER);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,150)", "");
        //click on Price
        driver.findElement(By.xpath("//*[@id=\"facets\"]/div[2]/ul/li[3]/div/div")).click();
        // when we click class name will have active appended
        String className = driver.findElement(By.xpath("//*[@id=\"facets\"]/div[2]/ul/li[3]/div/div")).getAttribute("class");
        if(className.contains("facet-head facet-xpndicon")){
            System.out.println("Price Expanded");
            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("window.scrollBy(0,300)", "");
            driver.findElement(By.xpath("//*[@id=\"facets\"]/div[2]/ul/li[3]/div/div[2]/ul/li[1]/div/div/label")).click();
            Thread.sleep(3000);
            System.out.println(driver.findElement(By.xpath("//*[@id=\"facets\"]/div[2]/ul/li[3]/div/div[2]/ul/li[1]/div/div/label")).isSelected());
            JavascriptExecutor js2 = (JavascriptExecutor) driver;
            js2.executeScript("window.scrollBy(0,250)", "");
            takeScreenshot("Ajio_search_Adidas");
            test.log(Status.INFO, "This step shows usage of log(status, details)");
            test.info("This step shows usage of info(details)");
            test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
            test.addScreenCaptureFromPath("screenshot.png");
        }
        else{
                System.out.println("Price cannot be clicked");
            }
    }


    @Test (priority=15, enabled = true)
    public void verifyURL() throws Exception {
        //click MEN link
        ExtentTest test = extent.createTest("verifyURL", "Verify Men URL");
        driver.findElement(By.linkText("MEN")).click();
        String MenURL = driver.getCurrentUrl();
        System.out.println(MenURL);
        takeScreenshot("Ajio_MEN_URL");
        Thread.sleep(1000);

        //click WOMEN link
        driver.findElement(By.linkText("WOMEN")).click();
        String WomenURL = driver.getCurrentUrl();
        System.out.println(WomenURL);
        takeScreenshot("Ajio_WOMEN_URL");
        Thread.sleep(1000);

        //click KID link
        driver.findElement(By.linkText("KIDS")).click();
        String KidsURL = driver.getCurrentUrl();
        System.out.println(KidsURL);
        takeScreenshot("Ajio_KIDS_URL");
        Thread.sleep(1000);

        test.log(Status.INFO, "This step shows usage of log(status, details)");
        test.info("This step shows usage of info(details)");
        test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
        test.addScreenCaptureFromPath("screenshot.png");
    }

   public static void takeScreenshot(String fileName) throws Exception {
        //1. Take screenshot and store it as a file format
        System.out.println("Taking Screenshot");
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //2. now copy screenshot to desired location using copy file method
        FileUtils.copyFile(file, new File("G:\\Selenium_Maven\\src\\test\\java\\Maven_Selenium_for_Ajio\\" + fileName + ".jpg"));
    }

}
